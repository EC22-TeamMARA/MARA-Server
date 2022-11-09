package com.mara.mara.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    SUCCESS(OK,"성공"),
    JOIN_SUCCESS(OK,"회원가입 성공");

    private final HttpStatus httpStatus;
    private final String msg;



}