package org.example.aviation.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aviation.domain.models.entities.Flight;
import org.example.aviation.domain.repositories.FlightsRepository;
import org.example.aviation.dto.FlightApiResponse;

import org.example.aviation.dto.FlightData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // generates constructor for final fields
@Slf4j                    // Lombok
public class FlightService {

    private final FlightsRepository flightRepository;
    private final WebClient webClient;  // calls API

    @Value("${aviationstack.api.key}")
    private String apiKey;

    public Flight searchAndSaveFlight(String flightNumber) {
        log.info("Searching for flight: {}", flightNumber);

        Optional<Flight> existing = flightRepository.findByFlightNumber(flightNumber);
        if (existing.isPresent()) {
            log.info("Flight found in DB, returning cached result");
            return existing.get();
        }

        Flight flight = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/flights")
                        .queryParam("access_key", apiKey)
                        .queryParam("flight_iata", flightNumber)
                        .build())
                .retrieve()
                .bodyToMono(FlightApiResponse.class)
                .map(this::mapToFlight)
                .block();

        return flightRepository.save(flight);
    }

    public Optional<Flight> getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    private Flight mapToFlight(FlightApiResponse response) {

        FlightData data = response.getData().get(0);

        Flight flight = new Flight();
        flight.setFlightNumber(data.getFlight().getIata());
        flight.setAirline(data.getAirline().getName());
        flight.setStatus(data.getFlightStatus());

        flight.setDepartureAirport(data.getDeparture().getAirport());
        flight.setDepartureIata(data.getDeparture().getIata());
        flight.setTerminal(data.getDeparture().getTerminal());
        flight.setGate(data.getDeparture().getGate());
        flight.setDepartureTime(LocalDateTime.parse(
                data.getDeparture().getScheduled(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME
        ));

        flight.setArrivalAirport(data.getArrival().getAirport());
        flight.setArrivalIata(data.getArrival().getIata());
        flight.setArrivalTime(LocalDateTime.parse(
                data.getArrival().getScheduled(),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME
        ));

        return flight;
    }
}
