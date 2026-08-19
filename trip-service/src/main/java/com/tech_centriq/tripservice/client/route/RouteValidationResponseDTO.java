package com.tech_centriq.tripservice.client.route;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteValidationResponseDTO {

    private Long id;
    private String routeCode;
    private Boolean isActive;
}
