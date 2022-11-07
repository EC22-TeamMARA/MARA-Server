package com.mara.mara.setting.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("definition")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springOpenApi(){
        return new OpenAPI()
                .info(new Info().title("MARA API")
                                .description("MARA 프로젝트 API 명세서")
                                .version("v0.0.1"));

    }

}
