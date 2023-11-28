package com.jason.ospchallenge.integration;

import com.jason.ospchallenge.service.IpRangeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IpRangeIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeAll
    public void setup() {
        webTestClient = webTestClient.mutate()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 10))
                .build();
    }

    @Test
    public void testGetSingleEmployee_CN(){
        webTestClient
                .get().uri("api/v1/ip-management/range?region=cn")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void testGetSingleEmployee_AF(){
        webTestClient
                .get().uri("api/v1/ip-management/range?region=af")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void testGetSingleEmployee_CA(){
        webTestClient
                .get().uri("api/v1/ip-management/range?region=CA")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }
}
