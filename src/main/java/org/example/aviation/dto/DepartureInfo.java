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
public class DepartureInfo {

    @JsonProperty("airport")
    private String airport;

    @JsonProperty("iata")
    private String iata;

    @JsonProperty("terminal")
    private String terminal;

    @JsonProperty("gate")
    private String gate;

    @JsonProperty("scheduled")
    private String scheduled;
}
