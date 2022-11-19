package com.mara.mara.controller;

import com.mara.mara.constant.SuccessCode;
import com.mara.mara.data.UserData;
import com.mara.mara.dto.BaseResponse;
import com.mara.mara.dto.req.UserLoginDTO;
import com.mara.mara.dto.res.UserLoginResponse;
import com.mara.mara.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/login")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService loginService;

    @Operation(summary = "로그인 API ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginResponse.class))}),
            @ApiResponse(responseCode = "400", description = "로그인 실패 : 이유는 response msg 참고",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))})
    })
    @PostMapping("")
    public ResponseEntity<UserLoginResponse> Login(@RequestBody UserLoginDTO loginDTO){
        UserData userData = loginService.login(loginDTO);

        SuccessCode code = SuccessCode.LOGIN_SUCCESS;
        UserLoginResponse response = new UserLoginResponse(code.getMsg(),userData);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }

}
