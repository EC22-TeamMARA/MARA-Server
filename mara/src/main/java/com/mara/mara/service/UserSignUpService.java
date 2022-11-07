package com.mara.mara.service;

import com.mara.mara.data.SuccessCode;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserSignUpSubmitRequestDTO;
import com.mara.mara.repository.UserRepository;
import com.mara.mara.repository.UserSignUpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserSignUpService {
    private final UserRepository userRepository;
    private final UserSignUpRepository userSignUpRepository;

    @Transactional
    public SuccessCode signUp(UserSignUpSubmitRequestDTO submitDTO){
        userSignUpRepository.saveJoin(submitDTO);
        return SuccessCode.JOIN_SUCCESS;
    }

    @Transactional
    public boolean checkIdentifyIdDuplication(UserIdentifyIdDTO dto){
        String dup = userRepository.findByIdentifyId(dto.getNickname());
        if(dup==null)
            return false;
        return true;
    }

    @Transactional
    public boolean checkNicknameDuplication(UserNicknameDTO dto){
        String dup = userRepository.findByNickname(dto.getNickname());
        if(dup==null)
            return false;
        return true;
    }



}
