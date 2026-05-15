package com.tech_centriq.busservice.dto.response;

import com.tech_centriq.busservice.entity.BusEntity;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BusResponseDTO {

    private Long id;
    private String plateNumber;
    private String busNumber;
    private int capacity;
    private String model;
    private String status;

    public static BusResponseDTO responseDTO(BusEntity busEntity) {

        BusResponseDTO responseDTO = new BusResponseDTO();

        responseDTO.setId(busEntity.getId());
        responseDTO.setPlateNumber(busEntity.getPlateNumber());
        responseDTO.setBusNumber(busEntity.getBusNumber());
        responseDTO.setCapacity(busEntity.getCapacity());
        responseDTO.setModel(busEntity.getModel());
        responseDTO.setStatus(busEntity.getStatus().name());

        return responseDTO;
    }
}
