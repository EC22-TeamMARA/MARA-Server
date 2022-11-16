package com.mara.mara.dto.res;

import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class UserRSResponse extends BaseResponse {
    private RSResult data;

    public UserRSResponse(String msg, ArrayList<int> data ){
        super(msg);
        this.data = new RSResult(data);
    }

    @Getter
    @AllArgsConstructor
    static class RSResult{
        private ArrayList<int> recommendCocktails;
    }
}
