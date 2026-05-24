package com.tech_centriq.routesservice.dto.request.busStop;

import com.tech_centriq.routesservice.dto.response.busStop.BusStopResponseDTO;
import com.tech_centriq.routesservice.enums.BusStopStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BusStopStatusRequestDTO {

    @Schema(example = "ACTIVE")
    private BusStopStatus status;
}
