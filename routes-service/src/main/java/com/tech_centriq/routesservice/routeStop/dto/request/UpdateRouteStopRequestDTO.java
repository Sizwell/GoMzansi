package com.tech_centriq.routesservice.routeStop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRouteStopRequestDTO {

    @Schema(example = "4")
    private Integer stopOrder;

    @Schema(example = "22")
    private Integer estimatedArrivalOffsetMinutes;
}
