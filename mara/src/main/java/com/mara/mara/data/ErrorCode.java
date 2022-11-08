package com.mara.mara.data;

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

    /*409 - 회원가입 시 중복 여부 확인*/
    JOIN_CONFLICT_ID(CONFLICT,"중복된 아이디가 있습니다."),
    JOIN_CONFLICT_NICKNAME(CONFLICT,"중복된 닉네임이 있습니다.");;

    private final HttpStatus httpStatus;
    private final String msg;
}
