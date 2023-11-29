package com.jason.ospchallenge.controller;

import com.jason.ospchallenge.controller.exception.InternalServerErrorException;
import com.jason.ospchallenge.controller.exception.InvalidRegionException;
import com.jason.ospchallenge.model.ErrorDto;
import com.jason.ospchallenge.model.Prefix;
import com.jason.ospchallenge.service.IpRangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(description = "IP Range API", name = "IP API")
public class IpRangeController {

    private final IpRangeService ipRangeService;

    @GetMapping(value = "/range", produces = TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Get IP Range for Region")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the price",
                    content = { @Content(mediaType = "text/event-stream",
                            schema = @Schema(implementation = Prefix.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid region",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class)) })})
    public Flux<Prefix> getIpRanges(@RequestParam(value = "region") String region) throws InternalServerErrorException, InvalidRegionException {
         return ipRangeService.retrieveIpRanges(region);
    }

}
