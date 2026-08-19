package com.tech_centriq.tripservice.controller;

import com.tech_centriq.tripservice.dto.request.CreateTripRequestDTO;
import com.tech_centriq.tripservice.dto.request.UpdateTripRequestDTO;
import com.tech_centriq.tripservice.dto.response.TripResponseDTO;
import com.tech_centriq.tripservice.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @Operation(summary = "Create Trip")
    @PostMapping
    public ResponseEntity<TripResponseDTO> createTrip(@RequestBody CreateTripRequestDTO createTripRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripService.createTrip(createTripRequestDTO)
                );
    }

    @Operation(summary = "Get trip by Id")
    @GetMapping("/{id}")
    public ResponseEntity<TripResponseDTO> getTrip(@PathVariable Long id) {

        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @Operation(summary = "Get all Active Trips")
    @GetMapping
    public ResponseEntity<List<TripResponseDTO>> getActiveTrips() {

        return ResponseEntity.ok(tripService.getTrips());
    }

    @Operation(summary = "Update Active trip")
    @PutMapping("/{id}")
    public ResponseEntity<TripResponseDTO> updateTrip(
            @PathVariable Long id, @RequestBody UpdateTripRequestDTO updateTripRequestDTO) {

        return ResponseEntity.ok(tripService.updateTrip(id, updateTripRequestDTO));
    }

    @Operation(summary = "Deactivate Trip")
    @DeleteMapping("/{id}")
    public ResponseEntity<TripResponseDTO> deleteTrip(@PathVariable Long id) {

        tripService.deleteTrip(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Reactivate Trip")
    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<TripResponseDTO> reactivateTrip(@PathVariable Long id) {

        return ResponseEntity.ok(tripService.reactivateTrip(id));
    }

}
