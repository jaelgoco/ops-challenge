package com.jason.ospchallenge.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jason.ospchallenge.controller.exception.InternalServerErrorException;
import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import com.jason.ospchallenge.domain.data.Prefix;
import com.jason.ospchallenge.service.IpRangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IpRangeController.class)
class IpRangeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IpRangeService ipRangeService;

    @Test
    public void testGetPriceInfo() throws Exception {
        when(this.ipRangeService.retrieveIpRanges("US")).thenReturn(Flux.just(new Prefix("52.93.22.48/28", "us-west-1", "AMAZON", "us-west-1")));

        mockMvc.perform(get("/api/v1/ip-management/range?region=US"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/event-stream"))
                .andExpect(content().string("data:{\"ip_prefix\":\"52.93.22.48/28\",\"region\":\"us-west-1\",\"service\":\"AMAZON\",\"network_border_group\":\"us-west-1\"}\n" +
                        "\n"));
    }

    @Test
    public void testGetPriceInfo_InvalidRegionException() throws Exception {
        when(this.ipRangeService.retrieveIpRanges("USSD")).thenThrow(new InvalidRegionException("Invalid Region"));

        mockMvc.perform(get("/api/v1/ip-management/range?region=USSD"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetPriceInfo_InternalServerErrorException() throws Exception {
        when(this.ipRangeService.retrieveIpRanges("USSD")).thenThrow(new InternalServerErrorException("Internal server error"));

        mockMvc.perform(get("/api/v1/ip-management/range?region=USSD"))
                .andExpect(status().is5xxServerError());
    }

}