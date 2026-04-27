package org.example.aviation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("iata")
    private String iata;
}
