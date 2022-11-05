package com.mara.mara.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpSubmitRequestDTO {
    private String identifyId;
    private String nickname;
    private String password;
}
