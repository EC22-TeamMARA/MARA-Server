package com.mara.mara.dto.res;

import com.mara.mara.data.UserData;
import com.mara.mara.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserCreateAccountResponse extends BaseResponse {
    private UserAccountData data;

    public UserCreateAccountResponse(String msg, UserData userData){
        super(msg);
        this.data = new UserAccountData(userData.getId(),userData.getNickname());
    }

    @Getter
    @AllArgsConstructor
    private class UserAccountData{
        private Long id;
        private String nickname;
    }
}
