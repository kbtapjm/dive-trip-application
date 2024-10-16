package io.divetrip.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @Builder
    public static class Token {
        private String accessToken;
        private String refreshToken;
        private String tokenType;
    }

}
