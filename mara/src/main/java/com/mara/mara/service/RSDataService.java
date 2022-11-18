package com.mara.mara.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mara.mara.dto.req.UserCocktailSelectedDTO;
import com.mara.mara.dto.req.UserTagSelectedDTO;
import com.mara.mara.repository.RSrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RSDataService {
    private final RSrepository rSrepository;

    @Transactional
    public void saveCocktailIdList(UserCocktailSelectedDTO selectedDTO){
        for(Long i : selectedDTO.getCocktailIdSelectedList()){
            rSrepository.saveCocktailId(selectedDTO.getId(),i);
        }
    }

    @Transactional
    public void saveTagIdList(UserTagSelectedDTO selectedDTO){
        if(rSrepository.okForSaveTagId(selectedDTO.getId())){
            for(Long i : selectedDTO.getTagIdSelectedList()){
                rSrepository.saveTagId(selectedDTO.getId(),i);
            }
        }
    }
}
