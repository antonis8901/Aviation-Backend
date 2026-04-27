package org.example.aviation.domain.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity = mySql
    private Long id;

    private String flightNumber;
    private String airline;

    private String departureAirport;
    private String departureIata;
    private String arrivalAirport;
    private String arrivalIata;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private String status;
    private String terminal;
    private String gate;
}
