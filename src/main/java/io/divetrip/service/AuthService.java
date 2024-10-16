package io.divetrip.service;

import io.divetrip.domain.entity.Diver;
import io.divetrip.domain.repository.DiverRepository;
import io.divetrip.dto.request.AuthRequest;
import io.divetrip.dto.response.AuthResponse;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.mapper.request.AuthRequestMapper;
import io.divetrip.secuity.component.JwtTokenProvider;
import io.divetrip.secuity.enumeration.TokenType;
import io.divetrip.secuity.service.AuthTokenService;
import io.jsonwebtoken.Claims;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthResponse.Token authenticate(final AuthRequest.Login dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService 의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // SecurityContextHolder에 authentication 정보를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = jwtTokenProvider.createAccessToken(authentication.getName(), authorities);
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication.getName(), authorities);

        // set refreshToken token
        Claims claims = jwtTokenProvider.extractJwtClaims(accessToken);
        long expirationTime = claims.getExpiration().getTime();

        Date expirationDate = new Date(expirationTime);
        LocalDateTime expirationLocalDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        log.debug("===> Current Time: {}, Expiration Time: {}, expiration: {}"
                , LocalDateTime.now(), expirationLocalDateTime, Duration.between(LocalDateTime.now(), expirationLocalDateTime).getSeconds());

        // set refresh token to redis
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> authTokenMap = new HashMap<>();
        authTokenMap.put("email", dto.getEmail());
        authTokenMap.put("accessToken", accessToken);
        hashOperations.putAll(refreshToken, authTokenMap);

        redisTemplate.expire(refreshToken, Duration.between(LocalDateTime.now(), expirationLocalDateTime).getSeconds(), TimeUnit.SECONDS);

        return AuthResponse.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER.getType())
                .build();
    }

    public AuthResponse.Token refresh(final AuthRequest.Refresh dto) {
        /* validation refresh token */
        boolean valid = jwtTokenProvider.validateToken(dto.getRefreshToken());
        if (!valid) {
            throw DiveTripError.INVALID_TOKEN_VALUE.exception();
        }

        /* get refresh token by */
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String email = (String) hashOperations.get(dto.getRefreshToken(), "email");
        if (StringUtils.isEmpty(email)) {
            throw DiveTripError.UNKNOWN_TOKEN_VALUE.exception();
        }
        
        // TODO: 리프레쉬 토큰에 대한 검증 및 로직 필요시 추가

        /* get diver by email */
        Diver diver = diverRepository.findByEmail(email).get();
        String authorities = diver.getDiverRoles().stream()
                .map(m -> {
                    return m.getRole().getRoleCode();
                })
                .collect(Collectors.joining(","));

        String accessToken = jwtTokenProvider.createAccessToken(email, authorities);

        return AuthResponse.Token.builder()
                .accessToken(accessToken)
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
