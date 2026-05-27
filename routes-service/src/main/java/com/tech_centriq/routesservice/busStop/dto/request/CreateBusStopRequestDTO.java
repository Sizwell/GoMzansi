package com.tech_centriq.routesservice.busStop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateBusStopRequestDTO {

    @Schema(example = "Sesame")
    private String stopName;

    @Schema(example = "Sesame Bus Stop")
    private String locationDescription;

    @Schema(example = "-34.02199270234108")
    private Double latitude;

    @Schema(example = "18.607857673825155")
    private Double longitude;

}
