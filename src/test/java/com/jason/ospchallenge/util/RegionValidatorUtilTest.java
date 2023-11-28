package com.jason.ospchallenge.util;

import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import org.junit.jupiter.api.Test;

import static com.jason.ospchallenge.util.RegionValidatorUtil.validateRegion;
import static org.junit.jupiter.api.Assertions.*;


class RegionValidatorUtilTest {

    @Test
    void validateRegionTest() throws InvalidRegionException {
        assertTrue(validateRegion("EU"));
        assertTrue(validateRegion("US"));
        assertTrue(validateRegion("AP"));
        assertTrue(validateRegion("CN"));
        assertTrue(validateRegion("SA"));
        assertTrue(validateRegion("AF"));
        assertTrue(validateRegion("CA"));

        assertThrows(InvalidRegionException.class, () -> validateRegion("CNG"));
    }
}