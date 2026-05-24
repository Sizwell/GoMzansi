package com.tech_centriq.routesservice.dto.response.route;

import com.tech_centriq.routesservice.entity.RouteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponseDTO {

    private static RouteEntity routeEntity;

    private Long id;
    private String routeCode;
    private String routeName;
    private String startLocation;
    private String endLocation;
    private Double distanceInKM;
    private Integer estimatedDurationInMinutes;
    private String status;
    private Boolean isActive;

    public static RouteResponseDTO responseDTO(RouteEntity routeEntity) {

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setId(routeEntity.getId());
        dto.setRouteCode(routeEntity.getRouteCode());
        dto.setRouteName(routeEntity.getRouteName());
        dto.setStartLocation(routeEntity.getStartLocation());
        dto.setEndLocation(routeEntity.getEndLocation());
        dto.setDistanceInKM(routeEntity.getDistanceInKM());
        dto.setEstimatedDurationInMinutes(routeEntity.getEstimatedDurationInMinutes());

        RouteResponseDTO.routeEntity = routeEntity;
        dto.setStatus(routeEntity.getStatus().name());
        dto.setIsActive(routeEntity.getIsActive());

        return dto;
    }

}
