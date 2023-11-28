package com.jason.ospchallenge.domain.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    @JsonProperty
    private String code;
    @JsonProperty
    private String message;
}
