package com.mara.mara.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mara.mara.dto.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserSignUpDuplicateResponse extends BaseResponse {
    @JsonProperty
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
