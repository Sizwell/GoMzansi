package com.tech_centriq.routesservice.routeStop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRouteStopRequestDTO {

    @Schema(example = "R-018")
    private String routeCode;

    @Schema(example = "BS-777")
    private String stopCode;

    @Schema(example = "2")
    private Integer stopOrder;

    @Schema(example = "35")
    private Integer estimatedArrivalOffsetMinutes;

}
