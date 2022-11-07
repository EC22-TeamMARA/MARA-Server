package com.mara.mara.dto.res;

import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserSignUpDuplicateResponse extends BaseResponse {
    private DuplicateData data;

    public UserSignUpDuplicateResponse(String msg,boolean check){
        super(msg);
        this.data = new DuplicateData(check);
    }

    @Getter
    @AllArgsConstructor
    static class DuplicateData{
        boolean check;
    }
}
