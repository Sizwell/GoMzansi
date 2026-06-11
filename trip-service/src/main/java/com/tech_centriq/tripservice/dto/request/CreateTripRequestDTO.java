package com.tech_centriq.tripservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTripRequestDTO {

    @Schema(example = "R-001")
    private String routeCode;

    @Schema(example = "B-001")
    private String busNumber;

    @Schema(example = "17")
    private Long driverId;

    @Schema(example = "07:28")
    private LocalDateTime scheduledDepartureTime;

    @Schema(example = "08:15")
    private LocalDateTime scheduledArrivalTime;
}
