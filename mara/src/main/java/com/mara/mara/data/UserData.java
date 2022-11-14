package com.mara.mara.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private Long id;
    private String identifyId;
    private String nickname;
    private String password;
}
