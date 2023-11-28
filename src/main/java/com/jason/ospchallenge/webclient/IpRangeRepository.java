package com.jason.ospchallenge.webclient;

import com.jason.ospchallenge.domain.data.IpRange;
import com.jason.ospchallenge.domain.data.Prefix;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IpRangeRepository {
    Flux<Prefix> getIpRanges();
}
