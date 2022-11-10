package com.mara.mara.dto.res;

import com.mara.mara.dto.BaseResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSignUpDataResponse<T> extends BaseResponse {
    private List<T> data;

    public UserSignUpDataResponse(String msg,List<T> data){
        super(msg);
       this.data = data;
    }
}
