package com.mara.mara.service;

import com.mara.mara.dto.req.UserCocktailSelectedDTO;
import com.mara.mara.dto.req.UserTagSelectedDTO;
import com.mara.mara.repository.RSrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RSService {
    private final RSrepository rSrepository;

    @Transactional
    public void saveCocktailIdList(UserCocktailSelectedDTO selectedDTO){
        for(Long i : selectedDTO.getCocktailIdSelectedList()){
            rSrepository.saveCocktailId(selectedDTO.getId(),i);
        }
    }

    @Transactional
    public void saveTagIdList(UserTagSelectedDTO selectedDTO){
        for(Long i : selectedDTO.getTagIdSelectedList()){
            rSrepository.saveTagId(selectedDTO.getId(),i);
        }
    }

}
