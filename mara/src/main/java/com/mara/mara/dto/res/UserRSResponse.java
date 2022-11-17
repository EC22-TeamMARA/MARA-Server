package com.mara.mara.dto.res;

import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserRSResponse extends BaseResponse {
    private RSResult data;

    public UserRSResponse(String msg, List<Integer> data ){
        super(msg);
        this.data = new RSResult(data);
    }

    @Getter
    @AllArgsConstructor
    static class RSResult{
        private List<Integer> recommendCocktails;
    }
}
