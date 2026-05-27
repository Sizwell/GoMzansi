package com.tech_centriq.routesservice.route.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateRouteRequestDTO {

    @Schema(example = "CBD Express")
    private String routeName;

    @Schema(example = "Cape Town CBD")
    private String startLocation;

    @Schema(example = "Kayelitsha")
    private String endLocation;

    @Schema(example = "28.5")
    private Double distanceInKM;

    @Schema(example = "45")
    private Integer estimatedDurationInMinutes;

//    @Schema(example = "MAINTENANCE")
//    private RouteStatus routeStatus;
//
//    @Schema(example = "true")
//    private Boolean isActive;
}
