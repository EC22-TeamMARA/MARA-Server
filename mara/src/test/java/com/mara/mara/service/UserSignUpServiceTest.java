package com.mara.mara.service;

import com.mara.mara.constant.SuccessCode;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserCreateAccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSignUpServiceTest {
    @Autowired
    UserSignUpService signUpService;

    @Test
    public void 회원가입(){
        UserCreateAccountDTO dto = new UserCreateAccountDTO("yooki","우갱","1111");
        //SuccessCode code = signUpService.signUp(dto);
        //System.out.println(code.getMsg());
    }

    @Test
    public void 닉네임중복o(){
        UserNicknameDTO dto = new UserNicknameDTO("우갱");
        boolean check = signUpService.checkNicknameDuplication(dto);
        System.out.println(check);
    }
    @Test
    public void 닉네임중복x(){
        UserNicknameDTO dto = new UserNicknameDTO("하이");
        boolean check = signUpService.checkNicknameDuplication(dto);
        System.out.println(check);
    }

    @Test
    public void 아이디중복o(){
        UserIdentifyIdDTO dto = new UserIdentifyIdDTO("yooki");
        boolean check = signUpService.checkIdentifyIdDuplication(dto);
        System.out.println(check);
    }

    @Test
    public void 아이디중복x(){
        UserIdentifyIdDTO dto = new UserIdentifyIdDTO("1234");
        boolean check = signUpService.checkIdentifyIdDuplication(dto);
        System.out.println(check);
    }

}
