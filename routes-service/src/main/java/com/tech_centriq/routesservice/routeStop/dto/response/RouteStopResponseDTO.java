package com.tech_centriq.routesservice.routeStop.dto.response;

import com.tech_centriq.routesservice.routeStop.entity.RouteStopEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RouteStopResponseDTO {

    private Long id;
    private String routeCode;
    private String stopCode;
    private Integer stopOrder;
    private Integer estimatedArrivalOffsetMinutes;

    public static RouteStopResponseDTO responseDTO(RouteStopEntity routeStopEntity) {

        return RouteStopResponseDTO.builder()
                .id(routeStopEntity.getId())
                .routeCode(routeStopEntity.getRouteCode())
                .stopCode(routeStopEntity.getStopCode())
                .stopOrder(routeStopEntity.getStopOrder())
                .estimatedArrivalOffsetMinutes(routeStopEntity.getEstimatedArrivalOffsetMinutes())
                .build();
    }
}
