package io.divetrip.application.secuity.service;

import io.divetrip.application.secuity.model.AuthToken;
import io.divetrip.application.secuity.repository.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;

    public void createAuthToken(AuthToken authToken) {
        authTokenRepository.save(authToken);
    }

    public Optional<AuthToken> getAuthTokenByRefreshToken(final String refreshToken) {
        return authTokenRepository.findByRefreshToken(refreshToken);
    }

}
