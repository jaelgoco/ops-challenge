package com.jason.ospchallenge.service;

import com.jason.ospchallenge.controller.exception.InternalServerErrorException;
import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import com.jason.ospchallenge.model.Prefix;
import com.jason.ospchallenge.webclient.IpRangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static com.jason.ospchallenge.util.RegionValidatorUtil.validateRegion;

@Service
@RequiredArgsConstructor
public class IpRangeService {

    private final IpRangeClient ipRangeClient;

    public Flux<Prefix> retrieveIpRanges(String region) throws InternalServerErrorException, InvalidRegionException {
        validateRegion(region);

        Flux<Prefix> ipRange;

        try {
            ipRange = ipRangeClient.getIpRanges();
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }

        if(region.equalsIgnoreCase("all")) {
            return ipRange;
        }

        return ipRange
                .filter(prefix -> prefix.getRegion().startsWith(region.toLowerCase()));
    }
}
