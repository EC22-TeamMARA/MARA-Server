package com.mara.mara.service;

import com.mara.mara.data.RSData;
import com.mara.mara.data.UserLikeData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RSService {
    private WebClient webClient;

    @PostConstruct
    public void initWebClient(){
        webClient = WebClient.create("http://165.246.44.238:5001");
    }

    public RSData RSByCocktailSelect(Long userId){
        return verification(userId);
    }

    private RSData verification(Long userId){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        UserLikeData data = new UserLikeData(userId,0,1);
        formData.add("user_id",userId.toString());
        formData.add("_1",Integer.toString(1));
        formData.add("_2",Integer.toString(2));



        return webClient.post()
                .uri("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(RSData.class)
                .block();
    }
}
