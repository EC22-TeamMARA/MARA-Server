package com.mara.mara.service;

import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.dto.res.UserSignUpSubmitResponseDTO;
import com.mara.mara.repository.UserSignUpRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSignUpService {
    private UserSignUpRepository userSignUpRepository;

    @Transactional
    public int signUp(UserSignUpSubmitRequestDTO submitDTO){
        return userSignUpRepository.saveJoin((submitDTO));
    }


}
