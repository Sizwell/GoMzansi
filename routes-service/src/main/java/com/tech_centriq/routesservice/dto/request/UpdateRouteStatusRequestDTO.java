package com.tech_centriq.routesservice.dto.request;

import com.tech_centriq.routesservice.enums.RouteStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateRouteStatusRequestDTO {

    @Schema(example = "MAINTENANCE")
    private RouteStatus routeStatus;
}
