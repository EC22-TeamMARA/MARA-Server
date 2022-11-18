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
public class RSService {
    private final RSrepository rSrepository;

    @Value("${rs.url}")
    private String rsUrl;

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
    public void restTemplateTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> map = new HashMap<>();
        List<Integer> cocktailList =  Arrays.asList(139,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0);
        map.put("userList", cocktailList);
        String param = mapper.writeValueAsString(map);

        HttpEntity entity = new HttpEntity(param, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(rsUrl, HttpMethod.POST, entity, String.class);

        System.out.println("status code : " + responseEntity.getStatusCode());
        System.out.println("body : " + responseEntity.getBody());
    }

}
