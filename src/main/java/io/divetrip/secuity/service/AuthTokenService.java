package io.divetrip.secuity.service;

import io.divetrip.secuity.dto.request.AuthTokenRequest;
import io.divetrip.secuity.repository.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;

    public void createAuthToken(AuthTokenRequest authTokenRequest) {
        authTokenRepository.save(authTokenRequest);
    }

}
