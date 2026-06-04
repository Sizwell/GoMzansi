package com.tech_centriq.routesservice.routeStop.controller;

import com.tech_centriq.routesservice.routeStop.dto.request.CreateRouteStopRequestDTO;
import com.tech_centriq.routesservice.routeStop.dto.request.UpdateRouteStopRequestDTO;
import com.tech_centriq.routesservice.routeStop.dto.response.RouteStopResponseDTO;
import com.tech_centriq.routesservice.routeStop.service.RouteStopService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route-stops")
@RequiredArgsConstructor
public class RouteStopController {

    private final RouteStopService routeStopService;

    @Operation(summary = "Add Stop to Route")
    @PostMapping
    public ResponseEntity<RouteStopResponseDTO> addStopToRoute(
            @RequestBody CreateRouteStopRequestDTO createRouteStopRequestDTO
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(routeStopService
                        .addStopToRoute(createRouteStopRequestDTO)
                );
    }

    @Operation(summary = "Get all Route Stops")
    @GetMapping("/route/{routeCode}")
    public ResponseEntity<List<RouteStopResponseDTO>> getStopsForRoute(@PathVariable String routeCode) {

        return ResponseEntity.ok(
                routeStopService.getRouteStops(routeCode)
        );
    }

    @Operation(summary = "Update Route Stop")
    @PutMapping("/{id}")
    public ResponseEntity<RouteStopResponseDTO> updateRouteStop(
            @PathVariable Long id,
            @RequestBody UpdateRouteStopRequestDTO requestDTO
    ) {

        return ResponseEntity.ok(
                routeStopService.updateStopToRoute(id, requestDTO)

        );
    }

    @Operation(summary = "Remove/delete bus Stop from Route")
    @DeleteMapping("/{id}")
    public ResponseEntity<RouteStopResponseDTO> deleteRouteStop(@PathVariable Long id) {

        routeStopService.deleteRouteStop(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Reactivate a Removed/deleted bus Stop")
    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<RouteStopResponseDTO> reactivateRouteStop(@PathVariable Long id) {

        return ResponseEntity.ok(
                routeStopService.reactivateRouteStop(id)
        );
    }

}
