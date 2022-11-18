package com.mara.mara.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RSServiceTest {
    @Autowired RSConnectService rsService;

    @Test
    public void 파이썬통신(){
        try{
            //rsService.restTemplateTest();
        }
        catch(Exception e){

        }
    }
}
