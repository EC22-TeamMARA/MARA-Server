package com.mara.mara.controller;

import com.mara.mara.data.SuccessCode;
import com.mara.mara.dto.BaseResponse;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.dto.res.UserSignUpDuplicateResponse;
import com.mara.mara.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/signup")
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

    @ResponseBody
    @PostMapping("/check/id")
    public ResponseEntity checkDuplicateIdentifyId(@RequestBody UserIdentifyIdDTO dto){
        boolean idDuplicate = userSignUpService.checkIdentifyIdDuplication(dto);

        UserSignUpDuplicateResponse response = new UserSignUpDuplicateResponse("",idDuplicate);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @ResponseBody
    @PostMapping("/check/nickname")
    public ResponseEntity checkDuplicateIdentifyId(@RequestBody UserNicknameDTO dto){
        boolean nicknameDuplicate = userSignUpService.checkNicknameDuplication(dto);
        UserSignUpDuplicateResponse response = new UserSignUpDuplicateResponse("",nicknameDuplicate);
        return ResponseEntity
                .ok()
                .body(response);
    }



}
