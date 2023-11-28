package com.jason.ospchallenge.service;

import com.jason.ospchallenge.controller.exception.InternalServerErrorException;
import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import com.jason.ospchallenge.domain.data.Prefix;
import com.jason.ospchallenge.webclient.IpRangeClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IpRangeServiceTest {

    @InjectMocks
    IpRangeService ipRangeService;

    @Mock
    IpRangeClient ipRangeClient;

    @Test
    void retrieveIpRanges() throws InvalidRegionException, InternalServerErrorException {
        List<Prefix> prefixList = new ArrayList<>();
        prefixList.add(createPrefix("us"));
        prefixList.add(createPrefix("us"));
        prefixList.add(createPrefix("fa"));

        Flux<Prefix> fluxData = Flux.fromIterable(prefixList);

        Flux<Prefix> fluxExpected = fluxData.filter(prefix -> prefix.getRegion().startsWith("us"));

        when(ipRangeClient.getIpRanges()).thenReturn(fluxData);

        assertEquals(fluxExpected.blockFirst().getRegion(), ipRangeService.retrieveIpRanges("US").blockFirst().getRegion());
        assertEquals(fluxExpected.blockLast().getRegion(), ipRangeService.retrieveIpRanges("US").blockLast().getRegion());

    }

    @Test
    void retrieveIpRanges_InternalServerErrorException() throws InvalidRegionException, InternalServerErrorException {
        when(ipRangeClient.getIpRanges()).thenThrow(WebClientResponseException.class);

        assertThrows(InternalServerErrorException.class, () -> ipRangeService.retrieveIpRanges("US"));
    }

    private Prefix createPrefix(String region) {
        return new Prefix("52.93.22.48/28", region + "-west-1", "AMAZON", "us-west-1");
    }
}