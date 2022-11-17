package com.mara.mara.controller;

import com.mara.mara.constant.SuccessCode;
import com.mara.mara.data.CocktailData;
import com.mara.mara.data.TagData;
import com.mara.mara.data.UserData;
import com.mara.mara.dto.BaseResponse;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserCreateAccountDTO;
import com.mara.mara.dto.res.UserCreateAccountResponse;
import com.mara.mara.dto.res.UserSignUpDataResponse;
import com.mara.mara.dto.res.UserSignUpDuplicateResponse;
import com.mara.mara.service.UserSignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/signup")
@RequiredArgsConstructor
public class UserSignUpController {
    private final UserSignUpService userSignUpService;

    @Operation(summary = "계정생성 API (완료)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserCreateAccountResponse.class))}),
            @ApiResponse(responseCode = "409", description = "중복 계정 존재",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))})
    })
    @ResponseBody
    @PostMapping("/submit")
    public ResponseEntity<UserCreateAccountResponse> CreateNewAccount(@RequestBody UserCreateAccountDTO accountDTO){
        UserData data = userSignUpService.signUp(accountDTO);

        SuccessCode code = SuccessCode.JOIN_SUCCESS;
        UserCreateAccountResponse response = new UserCreateAccountResponse(code.getMsg(),data);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }

    @Operation(summary = "아이디 중복 확인 API (완료)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "True - 아이디 사용가능, False - 이미 아이디가 존재함",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserSignUpDuplicateResponse.class))})
    })
    @ResponseBody
    @PostMapping("/check/id")
    public ResponseEntity<UserSignUpDuplicateResponse> CheckDuplicateIdentifyId(@RequestBody UserIdentifyIdDTO dto){
        boolean idDuplicate = userSignUpService.checkIdentifyIdDuplication(dto);

        SuccessCode code = SuccessCode.SUCCESS;
        UserSignUpDuplicateResponse response = new UserSignUpDuplicateResponse(code.getMsg(),!idDuplicate);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }

    @Operation(summary = "닉네임 중복 확인 API (완료)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "True - 닉네임 사용가능, False - 이미 닉네임이 존재함",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserSignUpDuplicateResponse.class))})
    })
    @ResponseBody
    @PostMapping("/check/nickname")
    public ResponseEntity<UserSignUpDuplicateResponse> CheckDuplicateIdentifyId(@RequestBody UserNicknameDTO dto){
        boolean nicknameDuplicate = userSignUpService.checkNicknameDuplication(dto);

        SuccessCode code = SuccessCode.SUCCESS;
        UserSignUpDuplicateResponse response = new UserSignUpDuplicateResponse(code.getMsg(),!nicknameDuplicate);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }

    @Operation(summary = "칵테일 데이터 가져오기 API (완료)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserSignUpDataResponse.class))})
    })
    @ResponseBody
    @GetMapping("/data/cocktails")
    public ResponseEntity<UserSignUpDataResponse> GetCocktailsDataForSignUp(){
        List<CocktailData> list = userSignUpService.getCocktailDataForSignUp();

        SuccessCode code = SuccessCode.SUCCESS;
        UserSignUpDataResponse<CocktailData> response = new UserSignUpDataResponse<>(code.getMsg(),list);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }

    @Operation(summary = "태그 데이터 가져오기 API (완료)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserSignUpDataResponse.class))})
    })
    @ResponseBody
    @GetMapping("/data/tags")
    public ResponseEntity<UserSignUpDataResponse> GetTagsDataForSignUp(){
        List<TagData> list = userSignUpService.getTagDataForSignUp();

        SuccessCode code = SuccessCode.SUCCESS;
        UserSignUpDataResponse<TagData> response = new UserSignUpDataResponse<>(code.getMsg(),list);
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(response);
    }

}
