package com.mara.mara.exception;

import com.mara.mara.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdviceController {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<BaseResponse> handleCustomException(CustomException e){
        return BaseResponse.errorResponse(e.getErrorCode());
    }

}
