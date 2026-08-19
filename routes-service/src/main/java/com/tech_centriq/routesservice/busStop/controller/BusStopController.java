package com.tech_centriq.routesservice.busStop.controller;

import com.tech_centriq.routesservice.busStop.dto.request.BusStopStatusRequestDTO;
import com.tech_centriq.routesservice.busStop.dto.request.CreateBusStopRequestDTO;
import com.tech_centriq.routesservice.busStop.dto.response.BusStopResponseDTO;
import com.tech_centriq.routesservice.busStop.service.BusStopService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bus-stops")
public class BusStopController {

    private final BusStopService busStopService;

    @Operation(summary = "Add Bus Stop")
    @PostMapping
    public ResponseEntity<BusStopResponseDTO> createBusStop(@RequestBody CreateBusStopRequestDTO createBusStopRequestDTO) {

        return new ResponseEntity<>(
                busStopService.createBusStop(createBusStopRequestDTO),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "View 1 Bus Stop")
    @GetMapping("/{id}")
    public ResponseEntity<BusStopResponseDTO> getBusStop(@PathVariable Long id) {

        return ResponseEntity.ok(
                busStopService.getBusStop(id)
        );
    }

    @Operation(summary = "View a list of Bus Stops")
    @GetMapping
    public ResponseEntity <List<BusStopResponseDTO>> getBusStops() {

        return ResponseEntity.ok(
                busStopService.getBusStops()
        );

    }

    @Operation(summary = "Update Bus Stop")
    @PutMapping("/{id}")
    public ResponseEntity<BusStopResponseDTO> updateBusStop(
            @PathVariable Long id,
            @RequestBody CreateBusStopRequestDTO createBusStopRequestDTO) {

        return ResponseEntity.ok(
                busStopService.updateBusStop(
                        id,
                        createBusStopRequestDTO
                )
        );
    }

    @Operation(summary = "Update Bus Stop Status")
    @PatchMapping("/{id}")
    public ResponseEntity<BusStopResponseDTO> updateBusStopStatus(
            @PathVariable Long id,
            @RequestBody BusStopStatusRequestDTO busStopStatusRequestDTO) {

        return ResponseEntity.ok(
                busStopService.updateBusStopStatus(
                        id,
                        busStopStatusRequestDTO
                )
        );
    }

    @Operation(summary = "Reactivate a deleted Bus Stop")
    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<BusStopResponseDTO> reactivateBusStopStatus(@PathVariable Long id) {

        return ResponseEntity.ok(
                busStopService.reactivateBusStop(id)
        );
    }

    @Operation(summary = "Delete Bus Stop")
    @DeleteMapping("/{id}")
    public ResponseEntity<BusStopResponseDTO> deleteBusStop(@PathVariable Long id) {

        busStopService.deleteBusStop(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
