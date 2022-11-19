package com.mara.mara.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mara.mara.data.CFResultData;
import com.mara.mara.data.CocktailData;
import com.mara.mara.data.UserLikeTagData;
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
                System.out.println("i : " + i);
                rSrepository.saveTagId(selectedDTO.getId(),i);
            }
        }
    }

    @Transactional
    public CocktailData tagRSSystem(CFResultData resultData){
        CocktailData cocktailData= rSrepository.getCocktailDataByCocktailId(resultData.getResultList().get(0).longValue());
        for(Integer i : resultData.getResultList()){
            List<UserLikeTagData> list = rSrepository.getTop3TagByCocktail(resultData.getUserId(),i);
            if(list.isEmpty())
                continue;
            else{
                cocktailData = rSrepository.getCocktailDataByCocktailId(list.get(0).getCocktailId());
                break;
            }
        }
        rSrepository.saveRSResult(resultData.getUserId(),cocktailData.getCocktailId());
        return cocktailData;
    }



}
