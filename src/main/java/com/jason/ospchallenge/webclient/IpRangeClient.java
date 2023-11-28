package com.jason.ospchallenge.webclient;

import com.jason.ospchallenge.domain.data.IpRange;
import com.jason.ospchallenge.domain.data.Prefix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class IpRangeClient implements IpRangeRepository{

    private final WebClient webClient;

    @Override
    public Flux<Prefix> getIpRanges() {
        return webClient.get()
                .uri("/ip-ranges.json")
                .retrieve()
                .bodyToMono(IpRange.class)
                .doOnSuccess(response -> log.info("Received response with syncToken: {}", response.getSyncToken()))
                .doOnError(exception -> log.warn("Failed to communicate with Amazon services"))
                .flatMapMany(response -> Flux.fromIterable(response.getPrefixes()));
    }
}
