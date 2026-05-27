package com.tech_centriq.routesservice.route.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Create Bus request")
public class CreateRouteRequestDTO {

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

}
