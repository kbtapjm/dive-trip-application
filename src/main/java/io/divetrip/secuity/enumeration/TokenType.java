package io.divetrip.secuity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    // https://overcome-the-limits.tistory.com/741
    BASIC("Basic", "사용자 아이디와 암호를 Base64로 인코딩한 값을 토큰으로 사용한다. (RFC 7617)"),
    BEARER("Bearer", "JWT 혹은 OAuth에 대한 토큰을 사용한다. (RFC 6750)"),
    DIGEST("Digest", "서버에서 난수 데이터 문자열을 클라이언트에 보낸다. 클라이언트는 사용자 정보와 nonce를 포함하는 해시값을 사용하여 응답한다 (RFC 7616)"),
    HOBA("HOBA", "전자 서명 기반 인증 (RFC 7486)"),
    MUTUAL("Mutual", "암호를 이용한 클라이언트-서버 상호 인증 (draft-ietf-httpauth-mutual)");

    private final String type;
    private final String description;

}
