package com.mara.mara.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mara.mara.constant.ErrorCode;
import com.mara.mara.data.CFResultData;
import com.mara.mara.exception.CustomException;
import com.mara.mara.repository.RSrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RSConnectService {
    private final RSrepository rSrepository;

    @Value("${rs.url}")
    private String rsUrl;

    @Value("${rs.top.cocktail}")
    private String top3ListString;

    @Transactional
    public CFResultData executeCFSystem(Long userId){
        List<Integer> resultList;
        if(rSrepository.getLikeCocktailNum(userId)==0)
            resultList = listMapper(top3ListString);
        else{
            List<Integer> dataList = rSrepository.getLikeCocktailsAllByUserId(userId);
            dataList.add(0,userId.intValue());
            System.out.println(dataList.toString());
            try{
                String result = restTemplateTest(dataList);
                resultList = listMapper(result);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
                throw new CustomException(ErrorCode.ERROR);
            }
        }
        return new CFResultData(userId,resultList);
    }


    @Transactional
    public String restTemplateTest(List<Integer> dataList) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> map = new HashMap<>();
        List<Integer> cocktailList = dataList;
        System.out.println(cocktailList.toString());
        map.put("userList", cocktailList);
        String param = mapper.writeValueAsString(map);

        HttpEntity entity = new HttpEntity(param, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(rsUrl, HttpMethod.POST, entity, String.class);

        System.out.println("status code : " + responseEntity.getStatusCode());
        System.out.println("body : " + responseEntity.getBody());
        return responseEntity.getBody();
    }

    private List<Integer> listMapper(String data) { //objectMapper
        List<Integer> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(data, List.class);
        } catch (IOException e) {
        }
        return list;
    }

}
