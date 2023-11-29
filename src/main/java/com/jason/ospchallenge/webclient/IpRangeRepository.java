package com.jason.ospchallenge.webclient;

import com.jason.ospchallenge.model.Prefix;
import reactor.core.publisher.Flux;

public interface IpRangeRepository {
    Flux<Prefix> getIpRanges();
}
