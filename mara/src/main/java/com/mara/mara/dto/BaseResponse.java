package com.mara.mara.dto;

import com.mara.mara.constant.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class BaseResponse {
    private String msg;
    public BaseResponse(String msg){
        this.msg = msg;
    }

    public static ResponseEntity<BaseResponse> errorResponse(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new BaseResponse(errorCode.getMsg()));
    }

}
