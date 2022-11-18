package com.mara.mara.controller;

import com.mara.mara.data.CFResultData;
import com.mara.mara.service.RSConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final RSConnectService rsService;

    @RequestMapping("/ApiTestt")
    private void restTemplateTest() throws Exception {
       CFResultData cfData = rsService.executeCFSystem(1025L);

    }
}
