package com.jason.ospchallenge.controller;

import com.jason.ospchallenge.controller.exception.InternalServerErrorException;
import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import com.jason.ospchallenge.domain.data.Prefix;
import com.jason.ospchallenge.service.IpRangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("api/v1/ip-management")
@RequiredArgsConstructor
public class IpRangeController {

    private final IpRangeService ipRangeService;

    @GetMapping(value = "/range", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Prefix> getIpRanges(@RequestParam(value = "region") String region) throws InternalServerErrorException, InvalidRegionException {
         return ipRangeService.retrieveIpRanges(region);
    }

}
