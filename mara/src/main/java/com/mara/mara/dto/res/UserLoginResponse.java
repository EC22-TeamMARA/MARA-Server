package com.mara.mara.dto.res;

import com.mara.mara.data.UserData;
import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserLoginResponse extends BaseResponse {
    private LoginResponseData data;

    public UserLoginResponse(String msg, UserData userData){
        super(msg);
        this.data = new LoginResponseData(userData.getId(),userData.getNickname());
    }

    @Getter
    @AllArgsConstructor
    private class LoginResponseData{
        private Long id;
        private String nickname;
    }
}
