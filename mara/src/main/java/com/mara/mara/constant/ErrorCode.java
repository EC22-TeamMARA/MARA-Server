package com.mara.mara.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*400 */
    NO_EXIST_USER(BAD_REQUEST, "해당 유저가 존재하지 않습니다."),
    NO_EXIST_ID(BAD_REQUEST, "해당 아이디가 존재하지 않습니다."),
    NO_EXIST_NICKNAME(BAD_REQUEST, "해당 닉네임이 존재하지 않습니다."),
    NO_MATCH_PASSWORD(BAD_REQUEST,"비밀번호가 일치하지 않습니다."),

    /*409 - 중복 여부 확인*/
    JOIN_CONFLICT_USER(CONFLICT,"중복된 유저가 있습니다."),
    JOIN_CONFLICT_ID(CONFLICT,"중복된 아이디가 있습니다."),
    JOIN_CONFLICT_NICKNAME(CONFLICT,"중복된 닉네임이 있습니다."),

    /*500-시스템 오류*/
    ERROR(INTERNAL_SERVER_ERROR,"오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String msg;
}
