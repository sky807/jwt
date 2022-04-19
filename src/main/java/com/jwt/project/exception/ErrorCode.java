package com.jwt.project.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * ErrorCode를 미리정의하여 Enum으로 설정한 부분
 *
 * @author 송하늘
 */
@Getter
@ToString
public enum ErrorCode {

    NOT_BLANK("E0001", "필수파라미터가 누락되었습니다."),
    HTTPS_ERROR("E0002", "통신과정에서 오류가 발생하였습니다."),
    SECURITY_0001("S0001", "권한 정보가 없는 토큰입니다."),
    USER_NOT_FOUND( "U0001", "정보와 일치하는 회원을 찾을 수 없습니다."),
    USER_ID_EXIST( "U0002", "이미 등록된 아이디가 있습니다."),
    LOGIN_ID_FAIL("L0001", "아이디가 일치하지 않습니다."),
    LOGIN_PASSWORD_FAIL( "L0002", "비밀번호가 일치하지 않습니다.");

    @Getter
    private String code;

    @Getter
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
