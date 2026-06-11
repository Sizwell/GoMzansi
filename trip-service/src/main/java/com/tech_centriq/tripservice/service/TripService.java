package com.tech_centriq.tripservice.service;

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

    @Transactional
    public TripResponseDTO createTrip(CreateTripRequestDTO requestDTO) {

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

    public TripResponseDTO getTripById(Integer id) {

        TripEntity trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getIsActive()) {
            throw new RuntimeException("Trip is not active.");
        }

        return TripResponseDTO.responseDTO(trip);
    }

    @Transactional
    public TripResponseDTO updateTrip(Integer id, UpdateTripRequestDTO requestDTO) {

        TripEntity trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found")
                );

        if (!trip.getIsActive()) {
            throw new RuntimeException("Trip is not active.");
        }

        TripEntity tripEntity = TripEntity.builder()
                .routeCode(requestDTO.getRouteCode())
                .busNumber(requestDTO.getBusNumber())
                .driverId(requestDTO.getDriverId())
                .scheduledDepartureTime(requestDTO.getScheduledDepartureTime())
                .scheduledArrivalTime(requestDTO.getScheduledArrivalTime())
                .status(requestDTO.getTripStatus())
                .updatedAt(LocalDateTime.now())
                .build();

        TripEntity updatedTrip = tripRepository.save(tripEntity);
        return TripResponseDTO.responseDTO(updatedTrip);
    }

    @Transactional
    public void deleteTrip(Integer id) {

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
    public TripResponseDTO reactivateTrip(Integer id) {
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
}
