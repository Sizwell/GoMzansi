package com.tech_centriq.routesservice.service;

import com.tech_centriq.routesservice.dto.request.busStop.BusStopStatusRequestDTO;
import com.tech_centriq.routesservice.dto.request.busStop.CreateBusStopRequestDTO;
import com.tech_centriq.routesservice.dto.response.busStop.BusStopResponseDTO;
import com.tech_centriq.routesservice.entity.BusStopEntity;
import com.tech_centriq.routesservice.enums.BusStopStatus;
import com.tech_centriq.routesservice.repository.BusStopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusStopService {

    private final BusStopRepository busStopRepository;

    @Transactional
    public BusStopResponseDTO createBusStop(CreateBusStopRequestDTO createBusStopRequestDTO) {

        BusStopEntity busStopEntity = new BusStopEntity();
        String stopCode = generateBusStopCode();

        System.out.println("Creating bus stop with code " + stopCode);

        if (busStopRepository.existsByStopCode(busStopEntity.getStopCode())) {
            throw new RuntimeException("Stop code already exists");
        }

        busStopEntity.setStopCode(stopCode);
        busStopEntity.setStopName(createBusStopRequestDTO.getStopName());
        busStopEntity.setLatitude(createBusStopRequestDTO.getLatitude());
        busStopEntity.setLongitude(createBusStopRequestDTO.getLongitude());
        busStopEntity.setLocationDescription(createBusStopRequestDTO.getLocationDescription());

        busStopEntity.setStatus(BusStopStatus.ACTIVE);
        busStopEntity.setIsActive(true);

        busStopEntity.setCreatedAt(LocalDateTime.now());
        busStopEntity.setUpdatedAt(LocalDateTime.now());

        return BusStopResponseDTO.responseDTO(busStopRepository.save(busStopEntity));

    }

    private String generateBusStopCode() {

        int nextNumber = 1;

        while (nextNumber <= 999) {
            String stopCode = String.format("BS-%03d", nextNumber);

            boolean exists =  busStopRepository.existsByStopCode(stopCode);

            if (!exists) {
                return stopCode;
            }
            nextNumber++;
        }
        throw new RuntimeException("No available Stop Codes");
    }

    public List<BusStopResponseDTO> getBusStops() {

        return busStopRepository.findByIsActiveTrue()
                .stream()
                .map(BusStopResponseDTO::responseDTO)
                .toList();
    }

    public BusStopResponseDTO getBusStop(Long id) {

        BusStopEntity busStopEntity = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No stop found with id: " + id));

        if (!busStopEntity.getIsActive()) {
            throw new RuntimeException("Stop is not Active");
        }

        return BusStopResponseDTO.responseDTO(busStopEntity);
    }

    //We are using the CreateBusStopRequestDTO because it has the exact same fields we want to update.
    // We can later update the DTO name to something like BusStopRequestDTO
    @Transactional
    public BusStopResponseDTO updateBusStop(Long id, CreateBusStopRequestDTO createBusStopRequestDTO) {

        BusStopEntity busStopEntity = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No stop found with id: " + id));

        if (!busStopEntity.getIsActive()) {
            throw new RuntimeException("Stop is not Active");
        }

        busStopEntity.setStopName(createBusStopRequestDTO.getStopName());
        busStopEntity.setLatitude(createBusStopRequestDTO.getLatitude());
        busStopEntity.setLongitude(createBusStopRequestDTO.getLongitude());
        busStopEntity.setLocationDescription(createBusStopRequestDTO.getLocationDescription());

        busStopEntity.setUpdatedAt(LocalDateTime.now());

        return BusStopResponseDTO.responseDTO(
                busStopRepository.save(busStopEntity)
        );
    }

    @Transactional
    public BusStopResponseDTO reactivateBusStop(Long id) {

        BusStopEntity busStopEntity = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No stop found with id: " + id)
                );

        if (busStopEntity.getIsActive()) {
            throw new RuntimeException("Bus stop Active");
        }

        busStopEntity.setStatus(BusStopStatus.ACTIVE);
        busStopEntity.setIsActive(true);

        busStopEntity.setUpdatedAt(LocalDateTime.now());

        return BusStopResponseDTO.responseDTO(
                busStopRepository.save(busStopEntity)
        );
    }

    @Transactional
    public BusStopResponseDTO updateBusStopStatus(Long id, BusStopStatusRequestDTO busStopStatusRequestDTO) {

        BusStopEntity busStopEntity = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No stop found with id: " + id));

        busStopEntity.setStatus(busStopStatusRequestDTO.getStatus());

        if (busStopStatusRequestDTO.getStatus() == BusStopStatus.ACTIVE) {

            busStopEntity.setIsActive(true);

        } else {
            busStopEntity.setIsActive(false);
        }

        busStopEntity.setUpdatedAt(LocalDateTime.now());

        return BusStopResponseDTO.responseDTO(busStopRepository.save(busStopEntity));
    }

    @Transactional
    public void deleteBusStop(Long id) {
        BusStopEntity entity = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No stop found with id: " + id));

        if (!entity.getIsActive()) {
            throw new RuntimeException("Stop is not Active");
        }

        entity.setIsActive(false);
        entity.setStatus(BusStopStatus.CLOSED);

        entity.setUpdatedAt(LocalDateTime.now());

        busStopRepository.save(entity);
    }

}