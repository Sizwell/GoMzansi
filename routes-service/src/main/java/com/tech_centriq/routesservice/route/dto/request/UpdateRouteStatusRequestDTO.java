package com.tech_centriq.routesservice.route.dto.request;

import com.tech_centriq.routesservice.route.enums.RouteStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateRouteStatusRequestDTO {

    @Schema(example = "MAINTENANCE")
    private RouteStatus routeStatus;
}
