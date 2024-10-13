package io.divetrip.secuity.repository;

import io.divetrip.secuity.model.AuthToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthTokenRepository extends CrudRepository<AuthToken, String> {

    Optional<AuthToken> findByRefreshToken(String refreshToken);

}
