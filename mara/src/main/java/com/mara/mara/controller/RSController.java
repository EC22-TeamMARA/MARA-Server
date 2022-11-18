package com.mara.mara.controller;

import com.mara.mara.constant.SuccessCode;
import com.mara.mara.dto.BaseResponse;
import com.mara.mara.dto.req.UserCocktailSelectedDTO;
import com.mara.mara.dto.req.UserTagSelectedDTO;
import com.mara.mara.service.RSDataService;
import com.mara.mara.service.WebClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/rs")
@RequiredArgsConstructor
public class RSController {
    private final RSDataService rsService;
    private final WebClientService webClientService;

    @Operation(summary = "좋아하는 칵테일 저장 API (완료)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))})
    })
    @PostMapping("/cocktails")
    public ResponseEntity<BaseResponse> cocktailSys(@RequestBody UserCocktailSelectedDTO selectedDTO){
        rsService.saveCocktailIdList(selectedDTO);

        SuccessCode code = SuccessCode.SUCCESS;
        BaseResponse response = new BaseResponse(code.getMsg());
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Operation(summary = "선호하는 키워드 저장 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseResponse.class))})
    })
    @PostMapping("/tags")
    public ResponseEntity<BaseResponse> tagSys(@RequestBody UserTagSelectedDTO selectedDTO){
        rsService.saveTagIdList(selectedDTO);

        SuccessCode code = SuccessCode.SUCCESS;
        BaseResponse response = new BaseResponse(code.getMsg());
        return ResponseEntity
                .ok()
                .body(response);
    }
}
