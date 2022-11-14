package com.mara.mara.service;

import com.mara.mara.constant.ErrorCode;
import com.mara.mara.data.UserData;
import com.mara.mara.dto.req.UserLoginDTO;
import com.mara.mara.exception.CustomException;
import com.mara.mara.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;

    @Transactional
    public UserData login(UserLoginDTO loginDTO){
        UserData userData = userRepository.getUserDataByIdentifyId(loginDTO.getIdentifyId());
        if(userData.getPassword().equals(loginDTO.getPassword()))
            return userData;
        else
            throw new CustomException(ErrorCode.NO_MATCH_PASSWORD);
    }
}
