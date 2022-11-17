package com.mara.mara.service;

import com.mara.mara.constant.ErrorCode;
import com.mara.mara.constant.SuccessCode;
import com.mara.mara.data.CocktailData;
import com.mara.mara.data.TagData;
import com.mara.mara.data.UserData;
import com.mara.mara.dto.req.UserIdentifyIdDTO;
import com.mara.mara.dto.req.UserNicknameDTO;
import com.mara.mara.dto.req.UserCreateAccountDTO;
import com.mara.mara.exception.CustomException;
import com.mara.mara.repository.UserRepository;
import com.mara.mara.repository.UserSignUpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSignUpService {
    private final UserRepository userRepository;
    private final UserSignUpRepository userSignUpRepository;

    @Transactional
    public UserData signUp(UserCreateAccountDTO submitDTO){
        try{
            userSignUpRepository.saveJoin(submitDTO);
        }
        catch (Exception e){
            throw new CustomException(ErrorCode.JOIN_CONFLICT_USER);
        }
        UserData data = userRepository.getUserDataByIdentifyId(submitDTO.getIdentifyId());
        return data;
    }

    @Transactional
    public boolean checkIdentifyIdDuplication(UserIdentifyIdDTO dto){
        Optional<String> dup = userSignUpRepository.checkDuplicateIdentifyId(dto.getIdentifyId());
        return dup.isPresent();
    }

    @Transactional
    public boolean checkNicknameDuplication(UserNicknameDTO dto){
        Optional<String> dup = userSignUpRepository.checkDuplicateNickname(dto.getNickname());
        return dup.isPresent();
    }

    @Transactional
    public List<CocktailData> getCocktailDataForSignUp(){
        return userSignUpRepository.getCocktailDataForSignUp();
    }

    @Transactional
    public List<TagData> getTagDataForSignUp(){
        return userSignUpRepository.getTagDataForSignUp();
    }

}
