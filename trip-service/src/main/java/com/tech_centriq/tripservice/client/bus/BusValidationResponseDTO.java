package com.tech_centriq.tripservice.client.bus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusValidationResponseDTO {

    private Long id;
    private String busNumber;
    private Boolean isActive;
    private String status;

}
