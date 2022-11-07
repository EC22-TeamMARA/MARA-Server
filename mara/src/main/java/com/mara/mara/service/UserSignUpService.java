package com.mara.mara.service;

import com.mara.mara.data.SuccessCode;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.repository.UserRepository;
import com.mara.mara.repository.UserSignUpRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSignUpService {
    private UserRepository userRepository;
    private UserSignUpRepository userSignUpRepository;

    @Transactional
    public SuccessCode signUp(UserSignUpSubmitRequestDTO submitDTO){
       userSignUpRepository.saveJoin(submitDTO);
        return SuccessCode.JOIN_SUCCESS;
    }

    @Transactional
    public boolean checkIdentifyIdDuplication(UserIdentifyIdDTO dto){
        return userRepository.existsByIdentifyId(dto.getNickname());
    }

    @Transactional
    public boolean checkNicknameDuplication(UserNicknameDTO dto){
        return userRepository.existsByNickname(dto.getNickname());
    }



}
