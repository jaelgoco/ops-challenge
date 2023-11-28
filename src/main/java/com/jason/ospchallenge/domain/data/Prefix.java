package com.jason.ospchallenge.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prefix {
    @JsonProperty("ip_prefix")
    private String ipPrefix;
    @JsonProperty("region")
    private String region;
    @JsonProperty("service")
    private String service;
    @JsonProperty("network_border_group")
    private String networkBorderGroup;
}
