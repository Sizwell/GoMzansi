package com.tech_centriq.busservice.controller;

import com.tech_centriq.busservice.dto.request.BusRequestDTO;
import com.tech_centriq.busservice.dto.request.UpdateBusRequestDTO;
import com.tech_centriq.busservice.dto.response.BusResponseDTO;
import com.tech_centriq.busservice.service.BusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bus Controller", description = "Bus Management APIs")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    @Operation(summary = "Create a new bus")
    @PostMapping
    public ResponseEntity<BusResponseDTO> createBus(@RequestBody BusRequestDTO busRequestDTO) {

        BusResponseDTO responseDTO = busService.createBus(busRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    @Operation(summary = "Get all busses")
    @GetMapping
    public ResponseEntity<List<BusResponseDTO>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @Operation(summary = "Get a Bus")
    @GetMapping("/{id}")
    public ResponseEntity<BusResponseDTO> getBusById(@PathVariable Long id) {

        return ResponseEntity.ok(
                busService.getBusById(id)
        );
    }

    @Operation(summary = "Update Bus")
    @PutMapping("/{id}")
    public ResponseEntity<BusResponseDTO> updateBusById(
            @PathVariable Long id,
            @RequestBody UpdateBusRequestDTO updateBusRequestDTO) {

        BusResponseDTO responseDTO = busService.updateBus(id, updateBusRequestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Update Bus Status")
    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<BusResponseDTO> reactivateBus(@PathVariable Long id) {

        return ResponseEntity.ok(
                busService.reactivateBus(id)
        );
    }

    @Operation(summary = "Delete Bus")
    @DeleteMapping("/{id}")
    public ResponseEntity<BusResponseDTO> deleteBusById(@PathVariable Long id) {
        busService.deleteBus(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
