package com.mara.mara.controller;

import com.mara.mara.constant.SuccessCode;
import com.mara.mara.data.RSData;
import com.mara.mara.dto.res.UserRSResponse;
import com.mara.mara.service.RSService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/submit")
@RequiredArgsConstructor
public class RSController {
    private final RSService rsService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserRSResponse> RecommendationSystem(@PathVariable Long userId){
        RSData data = rsService.RSByCocktailSelect(userId);

        SuccessCode code = SuccessCode.SUCCESS;
        UserRSResponse response = new UserRSResponse(code.getMsg(),data.getRecommendCocktailList());
        return ResponseEntity
                .ok()
                .body(response);
    }
}
