package io.divetrip.secuity.component;

import io.divetrip.enumeration.DiveTripError;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
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
    private final long tokenValidityInMilliseconds;
    private Key key;

    public JwtTokenProvider(
            @Value("${props.jwt.secret}") String secret,
            @Value("${props.jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds;
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

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date expiration = new Date(now + this.tokenValidityInMilliseconds);
        log.debug("===> expiration: {}", expiration);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities) // 정보 저장
                .signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘과 , signature 에 들어갈 secret값 세팅
                .setExpiration(expiration) // set Expire Time 해당 옵션 안넣으면 expire안함
                .compact();
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
