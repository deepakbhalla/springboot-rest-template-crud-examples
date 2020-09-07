package com.example.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurations {

    /**
     * Prepare rest template object with time out configurations.
     * 
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate() {

        HttpComponentsClientHttpRequestFactory clientRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientRequestFactory.setConnectTimeout(5000);
        clientRequestFactory.setReadTimeout(5000);

        return new RestTemplate(clientRequestFactory);
    }
}
