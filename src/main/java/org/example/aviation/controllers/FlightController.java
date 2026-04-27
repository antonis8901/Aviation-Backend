package org.example.aviation.controllers;


import lombok.RequiredArgsConstructor;
import org.example.aviation.domain.models.entities.Flight;
import org.example.aviation.domain.models.entities.SearchLog;
import org.example.aviation.domain.models.entities.User;
import org.example.aviation.domain.services.FlightService;
import org.example.aviation.domain.services.UserService;
import org.example.aviation.dto.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor  // Lombok
public class FlightController {

    private final UserService userService;
    private final FlightService flightService;

    @PostMapping("/search")
    public ResponseEntity<User> searchFlight(@RequestBody SearchRequest request) {
        User user = userService.createUserWithFlight(
                request.getUsername(),
                request.getFlightNumber()
        );
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logs")
    public ResponseEntity<List<SearchLog>> getAllLogs() {
        return ResponseEntity.ok(userService.getAllLogs());
    }


    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String flightNumber) {
        return flightService.getFlightByNumber(flightNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
