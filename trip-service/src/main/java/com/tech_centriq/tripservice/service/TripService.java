package com.tech_centriq.tripservice.service;

import com.tech_centriq.tripservice.client.bus.BusClient;
import com.tech_centriq.tripservice.client.bus.BusValidationResponseDTO;
import com.tech_centriq.tripservice.client.driver.DriverClient;
import com.tech_centriq.tripservice.client.driver.DriverValidationResponseDTO;
import com.tech_centriq.tripservice.client.route.RouteClient;
import com.tech_centriq.tripservice.client.route.RouteValidationResponseDTO;
import com.tech_centriq.tripservice.dto.request.CreateTripRequestDTO;
import com.tech_centriq.tripservice.dto.request.UpdateTripRequestDTO;
import com.tech_centriq.tripservice.dto.response.TripResponseDTO;
import com.tech_centriq.tripservice.entity.TripEntity;
import com.tech_centriq.tripservice.enums.TripStatus;
import com.tech_centriq.tripservice.repository.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final  BusClient busClient;
    private final RouteClient routeClient;
    private final DriverClient driverClient;

    @Transactional
    public TripResponseDTO createTrip(CreateTripRequestDTO requestDTO) {

        validateTripReferences(requestDTO.getRouteCode(), requestDTO.getBusNumber(), requestDTO.getDriverId());

        TripEntity trip = TripEntity.builder()
                .tripCode(generateTripCode())
                .routeCode(requestDTO.getRouteCode())
                .busNumber(requestDTO.getBusNumber())
                .driverId(requestDTO.getDriverId())
                .scheduledDepartureTime(requestDTO.getScheduledDepartureTime())
                .scheduledArrivalTime(requestDTO.getScheduledArrivalTime())
                .status(TripStatus.SCHEDULED)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        TripEntity savedTrip = tripRepository.save(trip);

        return TripResponseDTO.responseDTO(savedTrip);
    }

    public List<TripResponseDTO> getTrips() {

        return tripRepository.findByIsActiveTrue()
                .stream()
                .map(TripResponseDTO::responseDTO)
                .toList();

    }

    public TripResponseDTO getTripById(Long id) {

        TripEntity trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getIsActive()) {
            throw new RuntimeException("Trip is not active.");
        }

        return TripResponseDTO.responseDTO(trip);
    }

    @Transactional
    public TripResponseDTO updateTrip(Long id, UpdateTripRequestDTO requestDTO) {

        TripEntity trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found")
                );

        if (!trip.getIsActive()) {
            throw new RuntimeException("Trip is not active.");
        }

        validateTripReferences(requestDTO.getRouteCode(), requestDTO.getBusNumber(), requestDTO.getDriverId());

        trip.setRouteCode(requestDTO.getRouteCode());
        trip.setBusNumber(requestDTO.getBusNumber());
        trip.setDriverId(requestDTO.getDriverId());
        trip.setScheduledDepartureTime(requestDTO.getScheduledDepartureTime());
        trip.setScheduledArrivalTime(requestDTO.getScheduledArrivalTime());
        trip.setStatus(TripStatus.SCHEDULED);

        trip.setUpdatedAt(LocalDateTime.now());

        TripEntity savedTrip = tripRepository.save(trip);
        return TripResponseDTO.responseDTO(savedTrip);

    }

    @Transactional
    public void deleteTrip(Long id) {

        TripEntity trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getIsActive()) {
            throw new RuntimeException("Trip is not active.");
        }

        trip.setIsActive(false);
        trip.setStatus(TripStatus.CANCELLED);
        trip.setUpdatedAt(LocalDateTime.now());

        tripRepository.save(trip);

    }

    @Transactional
    public TripResponseDTO reactivateTrip(Long id) {
        TripEntity trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (trip.getIsActive()) {
            throw new RuntimeException("Trip already active.");
        }

        trip.setIsActive(true);
        trip.setStatus(TripStatus.SCHEDULED);
        trip.setUpdatedAt(LocalDateTime.now());

        tripRepository.save(trip);

        return TripResponseDTO.responseDTO(trip);
    }

    private String generateTripCode() {

        int nextNumber = 1;

        while(nextNumber <= 999) {
            String tripCode = String.format("T-%03d", nextNumber);

            boolean exists = tripRepository.existsByTripCode(tripCode);

            if(!exists) {
                return tripCode;
            }
            nextNumber++;
        }
        throw new RuntimeException("Trip code not found");
    }

    private void validateTripReferences(String routeCode, String busNumber, Long driverId) {

        validateRoute(routeCode);
        validateBus(busNumber);
        validateDriver(driverId);
    }

    private void validateRoute(String routeCode) {

        RouteValidationResponseDTO routeValidationResponseDTO = routeClient.getRoute(routeCode);

        if (routeValidationResponseDTO == null) {
            throw new RuntimeException("Route not found");
        }

        if (!Boolean.TRUE.equals(routeValidationResponseDTO.getIsActive())) {
            throw new RuntimeException("Route is not active.");
        }

    }

    private void validateBus(String busNumber) {
        BusValidationResponseDTO  busValidationResponseDTO = busClient.getBus(busNumber);

        if (busValidationResponseDTO == null) {
            throw new RuntimeException("Bus not found");
        }

        if (!Boolean.TRUE.equals(busValidationResponseDTO.getIsActive())) {
            throw new RuntimeException("Bus is not active.");
        }
    }

    private void validateDriver(Long driverId) {

        DriverValidationResponseDTO driverValidationResponseDTO = driverClient.getDriver(driverId);

        if (driverValidationResponseDTO == null) {
            throw new RuntimeException("Driver not found");
        }

        if (!Boolean.TRUE.equals(driverValidationResponseDTO.getIsActive())) {
            throw new RuntimeException("Driver is not active.");
        }

    }
}
