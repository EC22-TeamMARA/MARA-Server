package com.mara.mara.service;

import com.mara.mara.data.RSData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

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
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        //ArrayList<int> array = new ArrayList<int>();
        //array.add(1); array.add(2); array.add(3);
        //formData.add("testData",array);
        //formData.add("test","test");

        return webClient.get()
                .uri("/")
                //.contentType(MediaType.APPLICATION_JSON)
                //.body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(RSData.class)
                .block();
    }
}
