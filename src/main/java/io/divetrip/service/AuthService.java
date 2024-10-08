package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.request.AuthRequest;
import io.divetrip.dto.response.AuthResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.AuthRequestMapper;
import io.divetrip.secuity.component.JwtTokenProvider;
import io.divetrip.secuity.dto.request.AuthTokenRequest;
import io.divetrip.secuity.enumeration.TokenType;
import io.divetrip.secuity.service.AuthTokenService;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final DiverRepository diverRepository;
    private final AuthRequestMapper authRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthTokenService authTokenService;

    public AuthResponse.Token authenticate(final AuthRequest.Login dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService 의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // SecurityContextHolder에 authentication 정보를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

        // set refreshToken token
        Claims claims = jwtTokenProvider.extractJwtClaims(accessToken);
        long expirationTime = claims.getExpiration().getTime();

        Date expirationDate = new Date(expirationTime);
        LocalDateTime expirationLocalDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        AuthTokenRequest authTokenRequest = AuthTokenRequest.builder()
                .jwtToken(accessToken)
                .email(dto.getEmail())
                .expiration(Duration.between(LocalDateTime.now(), expirationLocalDateTime).getSeconds())
                .build();

        authTokenService.createAuthToken(authTokenRequest);

        return AuthResponse.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER.getType())
                .build();
    }

    @Transactional
    public String signup(final AuthRequest.Signup dto) {
        if (diverRepository.existsByEmail(dto.getEmail())) {
            throw DiveTripError.EMAIL_DUPLICATED.exception(dto.getEmail());
        }

        Diver diver = diverRepository.save(authRequestMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword())));

        return diver.getDiverId().toString();
    }

}
