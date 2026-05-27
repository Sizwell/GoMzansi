package com.tech_centriq.routesservice.busStop.dto.request;

import com.tech_centriq.routesservice.busStop.enums.BusStopStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BusStopStatusRequestDTO {

    @Schema(example = "ACTIVE")
    private BusStopStatus status;
}
