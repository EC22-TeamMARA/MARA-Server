package com.mara.mara.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpSubmitRequestDTO {
    private String identifyId;
    private String nickname;
    private String password;
}
