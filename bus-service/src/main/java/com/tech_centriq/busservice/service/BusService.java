package com.tech_centriq.busservice.service;

import com.tech_centriq.busservice.dto.request.BusRequestDTO;
import com.tech_centriq.busservice.dto.request.UpdateBusRequestDTO;
import com.tech_centriq.busservice.dto.response.BusResponseDTO;
import com.tech_centriq.busservice.entity.BusEntity;
import com.tech_centriq.busservice.enums.BusStatus;
import com.tech_centriq.busservice.repository.BusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public BusResponseDTO createBus(BusRequestDTO requestDTO) {

        if (busRepository.existsByPlateNumber(requestDTO.getPlateNumber())) {
            throw new RuntimeException("Plate number already exists");
        }

        BusEntity bus = new  BusEntity();

        bus.setPlateNumber(requestDTO.getPlateNumber());
        bus.setCapacity(requestDTO.getCapacity());
        bus.setModel(requestDTO.getModel());

        bus.setBusNumber(generateBusNumber());
        bus.setActive(true);
        bus.setStatus(BusStatus.ACTIVE);

        bus.setCreatedAt(LocalDateTime.now());
        bus.setUpdatedAt(LocalDateTime.now());

        BusEntity savedBus = busRepository.save(bus);

        //Map Entity to Response DTO

        BusResponseDTO responseDTO = new BusResponseDTO();

        responseDTO.setId(savedBus.getId());
        responseDTO.setPlateNumber(savedBus.getPlateNumber().toUpperCase());
        responseDTO.setCapacity(savedBus.getCapacity());
        responseDTO.setModel(savedBus.getModel());

        responseDTO.setStatus(savedBus.getStatus().name());

        return responseDTO;
    }

    private String generateBusNumber() {

        int nextNumber = 1;

        while (nextNumber <= 999) {

            String formattedNumber =
                    String.format("B-%03d", nextNumber);

            boolean exists =
                    busRepository.existsByBusNumber(formattedNumber);

            if (!exists) {
                return formattedNumber;
            }

            nextNumber++;
        }

        throw new RuntimeException("No available bus numbers");
    }

    public List<BusResponseDTO> getAllBuses() {

        busRepository.findByIsActiveTrue();

        return busRepository.findByIsActiveTrue()
                .stream()
                .map(BusResponseDTO::responseDTO)
                .toList();
    }

    public BusResponseDTO getBusById(Long id) {

        BusEntity busEntity = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        if (!busEntity.isActive()) {
            throw new RuntimeException("Bus is not Active");
        }

        return BusResponseDTO.responseDTO(busEntity);
    }

    public BusResponseDTO updateBus(Long id, UpdateBusRequestDTO requestDTO) {

        BusEntity existingBus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        existingBus.setPlateNumber(requestDTO.getPlateNumber());
        existingBus.setCapacity(requestDTO.getCapacity());
        existingBus.setModel(requestDTO.getModel());
        existingBus.setStatus(requestDTO.getStatus());
        existingBus.setActive(requestDTO.getIsActive());

        existingBus.setUpdatedAt(LocalDateTime.now());

        BusEntity updatedBus = busRepository.save(existingBus);

        return BusResponseDTO.responseDTO(updatedBus);
    }

    @Transactional
    public BusResponseDTO reactivateBus(Long id) {

        BusEntity busEntity = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        if (busEntity.isActive()) {
            throw new RuntimeException("Bus is already Active");
        }

        busEntity.setActive(true);
        busEntity.setUpdatedAt(LocalDateTime.now());

        return BusResponseDTO.responseDTO(busRepository.save(busEntity));
    }

    @Transactional
    public void deleteBus(Long id) {

        BusEntity busEntity = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus " + id + " not found"));

        busEntity.setActive(false);

        busEntity.setUpdatedAt(LocalDateTime.now());

        busRepository.save(busEntity);
    }

}
