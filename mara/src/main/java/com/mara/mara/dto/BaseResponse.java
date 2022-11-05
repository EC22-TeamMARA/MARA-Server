package com.mara.mara.dto;

import lombok.Getter;

@Getter
public class BaseResponse {
    private String msg;
    public BaseResponse(String msg){
        this.msg = msg;
    }


}
