package com.mara.mara.dto.res;

import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSignUpDataResponse<T> extends BaseResponse {
    private DataForSignUp<T> data;

    public UserSignUpDataResponse(String msg,List<T> data){
        super(msg);
        this.data = new DataForSignUp<>(data);
    }

    @Getter
    @AllArgsConstructor
    static class DataForSignUp<T>{
        private List<T> dataList;
    }
}
