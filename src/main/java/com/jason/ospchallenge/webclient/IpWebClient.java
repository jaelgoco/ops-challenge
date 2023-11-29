package com.jason.ospchallenge.webclient;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class IpWebClient {

    @Value("${osp.web.client.base.url}")
    private final String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 10)) // Sets the buffer size to 10 MB
                .baseUrl(baseUrl)
                .build();
    }
}
