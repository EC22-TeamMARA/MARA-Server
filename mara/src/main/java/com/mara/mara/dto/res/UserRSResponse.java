package com.mara.mara.dto.res;

import com.mara.mara.data.CocktailData;
import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserRSResponse extends BaseResponse {
    private CocktailData data;

    public UserRSResponse(String msg, CocktailData data ){
        super(msg);
        this.data = data;
    }
}
