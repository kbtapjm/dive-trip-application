package io.divetrip.secuity.repository;

import io.divetrip.secuity.dto.request.AuthTokenRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends CrudRepository<AuthTokenRequest, String> {
}
