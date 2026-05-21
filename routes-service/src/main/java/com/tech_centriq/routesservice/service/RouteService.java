package com.tech_centriq.routesservice.service;

import com.tech_centriq.routesservice.dto.request.CreateRouteRequestDTO;
import com.tech_centriq.routesservice.dto.request.UpdateRouteRequestDTO;
import com.tech_centriq.routesservice.dto.request.UpdateRouteStatusRequestDTO;
import com.tech_centriq.routesservice.dto.response.RouteResponseDTO;
import com.tech_centriq.routesservice.entity.RouteEntity;
import com.tech_centriq.routesservice.enums.RouteStatus;
import com.tech_centriq.routesservice.repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RouteService {

    private final RouteRepository routeRepository;

    public RouteResponseDTO createRoute(CreateRouteRequestDTO createRouteRequestDTO) {

        String routeCode = generateRouteCode();
        RouteEntity routeEntity = new RouteEntity();

        if (routeRepository.existsByRouteCode(routeCode)) {
            throw new RuntimeException("Route already exists");
        }

        routeEntity.setRouteName(createRouteRequestDTO.getRouteName());
        routeEntity.setRouteCode(routeCode);

        routeEntity.setStartLocation(createRouteRequestDTO.getStartLocation());
        routeEntity.setEndLocation(createRouteRequestDTO.getEndLocation());

        routeEntity.setDistanceInKM(createRouteRequestDTO.getDistanceInKM());
        routeEntity.setEstimatedDurationInMinutes(createRouteRequestDTO.getEstimatedDurationInMinutes());

        routeEntity.setStatus(RouteStatus.ACTIVE);
        routeEntity.setIsActive(true);
        routeEntity.setCreatedAt(LocalDateTime.now());
        routeEntity.setUpdatedAt(LocalDateTime.now());

        return RouteResponseDTO.responseDTO(routeRepository.save(routeEntity));

    }

    public List<RouteResponseDTO> getAllRoutes() {
        return routeRepository.findByIsActiveTrue()
                .stream()
                .map(RouteResponseDTO::responseDTO)
                .toList();
    }

    public RouteResponseDTO getRouteById(Long id) {

        RouteEntity routeEntity = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (!routeEntity.getIsActive()) {
            throw new RuntimeException("Route is not active");
        }

        return RouteResponseDTO.responseDTO(routeEntity);
    }

    public RouteResponseDTO updateRoute(Long id, UpdateRouteRequestDTO updateRouteRequestDTO) {

        RouteEntity routeEntity = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (!routeEntity.getIsActive()) {
            throw new RuntimeException("Route is not active");
        }

        routeEntity.setRouteName(updateRouteRequestDTO.getRouteName());
        routeEntity.setStartLocation(updateRouteRequestDTO.getStartLocation());
        routeEntity.setEndLocation(updateRouteRequestDTO.getEndLocation());
        routeEntity.setDistanceInKM(updateRouteRequestDTO.getDistanceInKM());
        routeEntity.setEstimatedDurationInMinutes(updateRouteRequestDTO.getEstimatedDurationInMinutes());

        routeEntity.setUpdatedAt(LocalDateTime.now());

        return RouteResponseDTO.responseDTO(routeRepository.save(routeEntity));

    }

    @Transactional
    public RouteResponseDTO reactivateRoute(Long id) {

        RouteEntity routeEntity = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (routeEntity.getIsActive()) {
            throw new RuntimeException("Route is already Active");
        }
        routeEntity.setIsActive(true);
        routeEntity.setStatus(RouteStatus.ACTIVE);
        routeEntity.setUpdatedAt(LocalDateTime.now());

        return RouteResponseDTO.responseDTO(routeRepository.save(routeEntity));
    }

    @Transactional
    public RouteResponseDTO updateRouteStatus(Long id, UpdateRouteStatusRequestDTO updateRouteStatusRequestDTO) {

        RouteEntity routeEntity = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (!routeEntity.getIsActive()) {
            throw new RuntimeException("Route is not active");
        }

        routeEntity.setStatus(updateRouteStatusRequestDTO.getRouteStatus());

        routeEntity.setUpdatedAt(LocalDateTime.now());

        return RouteResponseDTO.responseDTO(routeRepository.save(routeEntity));
    }

    public void deleteRouteById(Long id) {

        RouteEntity routeEntity = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        if (!routeEntity.getIsActive()) {
            throw new RuntimeException("Route already Deactivated");
        }

        routeEntity.setStatus(RouteStatus.INACTIVE);
        routeEntity.setIsActive(false);
        routeEntity.setUpdatedAt(LocalDateTime.now());

        routeRepository.save(routeEntity);
    }

    private String generateRouteCode() {
        int nextNumber = 100;

        while (nextNumber <= 999) {
            String routeCode = String.format("R-%03d", nextNumber);

            boolean exists =  routeRepository.existsByRouteCode(routeCode);

            if (!exists) {
                return routeCode;
            }
            nextNumber++;
        }
        throw new RuntimeException("No available Route Codes");
    }

}
