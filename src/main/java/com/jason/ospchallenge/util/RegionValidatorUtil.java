package com.jason.ospchallenge.util;

import com.jason.ospchallenge.controller.exception.InvalidRegionException;

public class RegionValidatorUtil {
    public static boolean validateRegion(String region) throws InvalidRegionException {
        try {
            Region.valueOf(region.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            throw new InvalidRegionException("Valid regions are EU, US, AP, CN, SA, AF, CA");
        }
    }
}
