package com.tech_centriq.routesservice.route.dto.response;

import com.tech_centriq.routesservice.route.entity.RouteEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

        return RouteResponseDTO.builder()
                .id(routeEntity.getId())
                .routeCode(routeEntity.getRouteCode())
                .routeName(routeEntity.getRouteName())
                .startLocation(routeEntity.getStartLocation())
                .endLocation(routeEntity.getStartLocation())
                .distanceInKM(routeEntity.getDistanceInKM())
                .estimatedDurationInMinutes(routeEntity.getEstimatedDurationInMinutes())
                .status(routeEntity.getStatus().name())
                .isActive(routeEntity.getIsActive())
                .build();

    }

}
