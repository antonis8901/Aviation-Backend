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
public class FlightData {

    @JsonProperty("flight_status")
    private String flightStatus;

    @JsonProperty("flight_date")
    private String flightDate;

    @JsonProperty("departure")
    private DepartureInfo departure;

    @JsonProperty("arrival")
    private ArrivalInfo arrival;

    @JsonProperty("airline")
    private AirlineInfo airline;

    @JsonProperty("flight")
    private FlightInfo flight;
}
