package org.example.aviation.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aviation.domain.models.entities.Flight;
import org.example.aviation.domain.models.entities.SearchLog;
import org.example.aviation.domain.models.entities.User;
import org.example.aviation.domain.repositories.SearchesLogRepository;
import org.example.aviation.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UsersRepository userRepository;
    private final FlightService flightService;
    private final SearchesLogRepository searchLogRepository;

    public User createUserWithFlight(String username, String flightNumber) {

        SearchLog searchLog = new SearchLog();
        searchLog.setUsername(username);
        searchLog.setFlightNumber(flightNumber);
        searchLogRepository.save(searchLog);

        Flight flight = flightService.searchAndSaveFlight(flightNumber);

        User user = new User();
        user.setUsername(username);
        user.setFlight(flight);

        return userRepository.save(user);
    }

    public List<SearchLog> getAllLogs() {
        return searchLogRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

