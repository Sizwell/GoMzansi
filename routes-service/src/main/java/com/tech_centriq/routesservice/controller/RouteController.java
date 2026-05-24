package com.tech_centriq.routesservice.controller;

import com.tech_centriq.routesservice.dto.request.route.CreateRouteRequestDTO;
import com.tech_centriq.routesservice.dto.request.route.UpdateRouteRequestDTO;
import com.tech_centriq.routesservice.dto.request.route.UpdateRouteStatusRequestDTO;
import com.tech_centriq.routesservice.dto.response.route.RouteResponseDTO;
import com.tech_centriq.routesservice.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @Operation(summary = "Create Bus Route")
    @PostMapping
    public ResponseEntity<RouteResponseDTO> createRoute(@RequestBody CreateRouteRequestDTO createRouteRequestDTO) {

        return new ResponseEntity<>(
                routeService.createRoute(createRouteRequestDTO),
                HttpStatus.CREATED
        );

    }

    @Operation(summary = "View all Bus Routes")
    @GetMapping
    public ResponseEntity<List<RouteResponseDTO>> getAllRoutes(){

        return ResponseEntity.ok(
                routeService.getAllRoutes()
        );
    }

    @Operation(summary = "View Route by Id")
    @GetMapping("/{id}")
    public ResponseEntity<RouteResponseDTO> getRouteById(@PathVariable Long id) {

        return ResponseEntity.ok(
                routeService.getRouteById(id)
        );
    }

    @Operation(summary = "Update Bus Route")
    @PutMapping("/{id}")
    public ResponseEntity<RouteResponseDTO> updateRoute(@PathVariable Long id,
                                                        @RequestBody UpdateRouteRequestDTO updateRouteRequestDTO) {

        return ResponseEntity.ok(routeService.updateRoute(
                id,
                updateRouteRequestDTO
        ));
    }

    @Operation(summary = "Reactivate Bus Route")
    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<RouteResponseDTO> reactivateRoute(@PathVariable Long id) {

        return ResponseEntity.ok(
                routeService.reactivateRoute(id)
        );
    }

    @Operation(summary = "Update Route Status")
    @PatchMapping("/{id}/status")
    public ResponseEntity<RouteResponseDTO> updateRouteStatus(
            @PathVariable Long id,
            @RequestBody UpdateRouteStatusRequestDTO updateRouteStatusRequestDTO) {

        return ResponseEntity.ok(
                routeService.updateRouteStatus(
                        id, updateRouteStatusRequestDTO
                )
        );
    }

    @Operation(summary = "Delete Bus Route (Deactivate)")
    @DeleteMapping("/{id}")
    public ResponseEntity<RouteResponseDTO> deleteRoute(@PathVariable Long id) {

        routeService.deleteRouteById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
