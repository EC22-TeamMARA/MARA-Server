package com.mara.mara.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    JOIN_CONFLICT_ID(CONFLICT,"중복된 아이디가 있습니다."),
    JOIN_CONFLICT_NICKNAME(CONFLICT,"중복된 닉네임이 있습니다.");;

    private final HttpStatus httpStatus;
    private final String msg;
}
