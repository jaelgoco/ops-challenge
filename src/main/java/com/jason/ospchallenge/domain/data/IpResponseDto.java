package com.jason.ospchallenge.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IpResponseDto {
    @JsonProperty("ranges")
    private List<IpRangeResponseDto> ipRangeResponseList;
}
