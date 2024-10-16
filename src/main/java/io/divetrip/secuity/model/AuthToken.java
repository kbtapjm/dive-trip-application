package io.divetrip.secuity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor
@Builder
@RedisHash(value = "AuthToken")
public class AuthToken {

    @Id
    private String refreshToken;

    @Indexed
    private String email;

    @Indexed
    private String accessToken;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long expiration;

}
