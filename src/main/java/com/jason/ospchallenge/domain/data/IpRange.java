package com.jason.ospchallenge.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IpRange {
    @JsonProperty("syncToken")
    private String syncToken;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("prefixes")
    private List<Prefix> prefixes;
}
