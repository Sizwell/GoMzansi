package com.tech_centriq.routesservice.routeStop.service;

import com.tech_centriq.routesservice.busStop.repository.BusStopRepository;
import com.tech_centriq.routesservice.route.repository.RouteRepository;
import com.tech_centriq.routesservice.routeStop.dto.request.CreateRouteStopRequestDTO;
import com.tech_centriq.routesservice.routeStop.dto.request.UpdateRouteStopRequestDTO;
import com.tech_centriq.routesservice.routeStop.dto.response.RouteStopResponseDTO;
import com.tech_centriq.routesservice.routeStop.entity.RouteStopEntity;
import com.tech_centriq.routesservice.routeStop.repository.RouteStopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final RouteRepository routeRepository;
    private final BusStopRepository busStopRepository;

    @Transactional
    public RouteStopResponseDTO addStopToRoute(CreateRouteStopRequestDTO createRouteStopRequestDTO) {

        routeRepository.findByRouteCode(createRouteStopRequestDTO.getRouteCode())
                .orElseThrow(() -> new RuntimeException("Route code not found")
                );

        busStopRepository.findByStopCode(createRouteStopRequestDTO.getStopCode())
                .orElseThrow(() -> new RuntimeException("Stop code not found")
                );

        boolean stopAlreadyExists = routeStopRepository
                .existsByRouteCodeAndStopCode(createRouteStopRequestDTO.getRouteCode(),
                        createRouteStopRequestDTO.getStopCode()
                );

        if (stopAlreadyExists) {
            throw new RuntimeException("Stop already exists");
        }

        RouteStopEntity routeStop = RouteStopEntity.builder()
                .routeCode(createRouteStopRequestDTO.getRouteCode())
                .stopCode(createRouteStopRequestDTO.getStopCode())
                .stopOrder(createRouteStopRequestDTO.getStopOrder())

                .estimatedArrivalOffsetMinutes(createRouteStopRequestDTO
                        .getEstimatedArrivalOffsetMinutes()
                )
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        RouteStopEntity savedRouteStop = routeStopRepository.save(routeStop);

        return RouteStopResponseDTO.responseDTO(savedRouteStop);

    }

    public List<RouteStopResponseDTO> getRouteStops(String routeCode) {

        return routeStopRepository
                .findByRouteCodeAndIsActiveTrueOrderByStopOrderAsc(routeCode)
                .stream()
                .map(RouteStopResponseDTO::responseDTO)
                .toList();
    }

    @Transactional
    public RouteStopResponseDTO updateStopToRoute(Long id, UpdateRouteStopRequestDTO requestDTO) {

        RouteStopEntity routeStopEntity = routeStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route code not found"));

        boolean stopOrderExists = routeStopRepository.existsByRouteCodeAndStopOrder(
                routeStopEntity.getRouteCode(),
                requestDTO.getStopOrder()
        );

        if (stopOrderExists && !routeStopEntity.getStopOrder()
                .equals(requestDTO.getStopOrder())) {

            throw new RuntimeException("Stop Order already exists");
        }

        routeStopEntity.setStopOrder(requestDTO.getStopOrder());

        routeStopEntity.setEstimatedArrivalOffsetMinutes(requestDTO.getEstimatedArrivalOffsetMinutes());

        routeStopEntity.setUpdatedAt(LocalDateTime.now());

        RouteStopEntity savedRouteStop = routeStopRepository.save(routeStopEntity);

        return RouteStopResponseDTO.responseDTO(savedRouteStop);

    }

    @Transactional
    public void deleteRouteStop(Long id) {

        RouteStopEntity entity = routeStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route code not found")
                );

        entity.setIsActive(false);
        entity.setUpdatedAt(LocalDateTime.now());

        routeStopRepository.save(entity);
    }

    @Transactional
    public RouteStopResponseDTO reactivateRouteStop(Long id) {

        RouteStopEntity entity = routeStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route stop not found")
                );

        if (entity.getIsActive()) {
            throw new RuntimeException("Route already active");
        }

        entity.setIsActive(true);
        entity.setUpdatedAt(LocalDateTime.now());

        RouteStopEntity savedRouteStop = routeStopRepository.save(entity);

        return RouteStopResponseDTO.responseDTO(savedRouteStop);
    }

}
