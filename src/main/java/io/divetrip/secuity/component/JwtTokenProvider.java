package io.divetrip.secuity.component;

import io.divetrip.enumeration.DiveTripError;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long accessExpirationTime;
    private final long refreshExpirationTime;
    private Key key;

    public JwtTokenProvider(
            @Value("${props.jwt.secret}") String secret,
            @Value("${props.jwt.token.access-expiration-time}") long accessExpirationTime,
            @Value("${props.jwt.token.refresh-expiration-time}") long refreshExpirationTime) {
        this.secret = secret;
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | DecodingException e ) {
            log.error(DiveTripError.INVALID_TOKEN_VALUE.getMessage());
            throw new JwtException(DiveTripError.INVALID_TOKEN_VALUE.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(DiveTripError.EXPIRED_TOKEN_VALUE.getMessage());
            throw new JwtException(DiveTripError.EXPIRED_TOKEN_VALUE.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error(DiveTripError.UNSUPPORTED_TOKEN_VALUE.getMessage());
            throw new JwtException(DiveTripError.UNSUPPORTED_TOKEN_VALUE.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(DiveTripError.UNKNOWN_TOKEN_VALUE.getMessage());
            throw new JwtException(DiveTripError.UNKNOWN_TOKEN_VALUE.getMessage());
        }
    }

    public String createAccessToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret 값 세팅
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.accessExpirationTime)) // 값을 넣지 않으면 만료 되지 않음
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.refreshExpirationTime))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return StringUtils.EMPTY;
        }

        return bearerToken.substring(7);
    }

    public Claims extractJwtClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), StringUtils.EMPTY, authorities), token, authorities);
    }

}
