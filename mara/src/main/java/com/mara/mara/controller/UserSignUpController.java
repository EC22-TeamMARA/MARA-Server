package com.mara.mara.controller;

import com.mara.mara.data.SuccessCode;
import com.mara.mara.dto.BaseResponse;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.dto.res.UserSignUpDuplicateResponse;
import com.mara.mara.service.UserSignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/signup")
@RequiredArgsConstructor
public class UserSignUpController {
    private final UserSignUpService userSignUpService;

    @Operation(summary = "회원가입 제출")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))})
    })
    @ResponseBody
    @PostMapping("/submit")
    public ResponseEntity SignUpSubmit(@RequestBody UserSignUpSubmitRequestDTO submitdto){
        SuccessCode code = userSignUpService.signUp(submitdto);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(new BaseResponse(code.getMsg()));
    }

    @Operation(summary = "아이디 중복 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "아이디 사용가능",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "409", description = "중복된 아이디 존재",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))})
    })
    @ResponseBody
    @PostMapping("/check/id")
    public ResponseEntity checkDuplicateIdentifyId(@RequestBody UserIdentifyIdDTO dto){
        boolean idDuplicate = userSignUpService.checkIdentifyIdDuplication(dto);

        UserSignUpDuplicateResponse response = new UserSignUpDuplicateResponse("",idDuplicate);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Operation(summary = "닉네임 중복 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "닉네임 사용가능",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "409", description = "중복된 닉네임 존재",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))})
    })
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
