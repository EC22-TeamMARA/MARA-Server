package com.mara.mara.controller;

import com.mara.mara.data.SuccessCode;
import com.mara.mara.dto.BaseResponse;
import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/signup/v0")
@RequiredArgsConstructor
public class UserSignUpController {
    private final UserSignUpService userSignUpService;

    @ResponseBody
    @PostMapping("/submit")
    public ResponseEntity SignUpSubmit(@RequestBody UserSignUpSubmitRequestDTO submitdto){
        SuccessCode code = userSignUpService.signUp(submitdto);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(new BaseResponse(code.getMsg()));
    }

}
