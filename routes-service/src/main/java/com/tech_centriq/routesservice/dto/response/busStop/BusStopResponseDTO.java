package com.tech_centriq.routesservice.dto.response.busStop;

import com.tech_centriq.routesservice.entity.BusStopEntity;
import com.tech_centriq.routesservice.entity.RouteEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusStopResponseDTO {

    private static BusStopEntity busStopEntity;

    private Long id;
    private String stopCode;
    private String stopName;
    private String locationDescription;
    private Double latitude;
    private Double longitude;

    private String status;
    private boolean isActive;

    public static BusStopResponseDTO responseDTO(BusStopEntity busStopEntity) {

        BusStopResponseDTO responseDTO = new BusStopResponseDTO();

        responseDTO.setId(busStopEntity.getId());
        responseDTO.setStopCode(busStopEntity.getStopCode());
        responseDTO.setStopName(busStopEntity.getStopName());
        responseDTO.setLocationDescription(busStopEntity.getLocationDescription());

        responseDTO.setLatitude(busStopEntity.getLatitude());
        responseDTO.setLongitude(busStopEntity.getLongitude());


        BusStopResponseDTO.busStopEntity = busStopEntity;
        responseDTO.setStatus(busStopEntity.getStatus().name());
        responseDTO.setActive(busStopEntity.getIsActive());

        return responseDTO;
    }
}
