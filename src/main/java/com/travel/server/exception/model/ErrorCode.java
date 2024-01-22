package com.travel.server.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* Common */
    INTERNAL_SERVER_ERROR(500, "E001", "일시적인 문제가 발생하였습니다. 다시 시도해주세요."),
    NOT_REQUEST_FILED(400, "E002", "필드가 누락 되었습니다."),

    /* Member */
    MEMBER_USER_NOT_FOUND(403, "E101", "해당 유저가 존재하지 않습니다."),
    MEMBER_LOGIN_INFO_NOT_FOUND(403, "E102", "아이디와 비밀번호가 일치하지 않습니다."),
    MEMBER_DUPLICATE_LOGIN_ID(422, "E103", "중복된 아이디 입니다."),
    MEMBER_NOT_TOKEN(401, "E104", "로그인 후 이용 가능 합니다.");

    private final int status;
    private final String code;
    private final String message;
}
