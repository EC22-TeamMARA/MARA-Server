package com.mara.mara.controller;

import com.mara.mara.service.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final RSService rsService;

    @RequestMapping("/ApiTestt")
    private void restTemplateTest() throws Exception {
       rsService.restTemplateTest();
    }
}
