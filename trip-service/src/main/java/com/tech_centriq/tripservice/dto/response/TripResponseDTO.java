package com.tech_centriq.tripservice.dto.response;

import com.tech_centriq.tripservice.entity.TripEntity;
import com.tech_centriq.tripservice.enums.TripStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TripResponseDTO {

    private Long id;
    private String tripCode;
    private String routeCode;
    private String busNumber;
    private Long driverId;
    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime scheduledArrivalTime;

    private TripStatus tripStatus;
    private Boolean isActive;

    public static TripResponseDTO responseDTO(TripEntity tripEntity) {

        return TripResponseDTO.builder()
                .id(tripEntity.getId())
                .tripCode(tripEntity.getTripCode())
                .routeCode(tripEntity.getRouteCode())
                .busNumber(tripEntity.getBusNumber())
                .driverId(tripEntity.getDriverId())
                .scheduledDepartureTime(tripEntity.getScheduledDepartureTime())
                .scheduledArrivalTime(tripEntity.getScheduledArrivalTime())
                .tripStatus(tripEntity.getStatus())
                .isActive(tripEntity.getIsActive())
                .build();
    }

}
