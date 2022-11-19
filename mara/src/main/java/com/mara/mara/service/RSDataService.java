package com.mara.mara.service;

import com.mara.mara.data.CFResultData;
import com.mara.mara.data.CocktailData;
import com.mara.mara.data.UserLikeTagData;
import com.mara.mara.dto.req.UserCocktailSelectedDTO;
import com.mara.mara.dto.req.UserTagSelectedDTO;
import com.mara.mara.repository.RSrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
