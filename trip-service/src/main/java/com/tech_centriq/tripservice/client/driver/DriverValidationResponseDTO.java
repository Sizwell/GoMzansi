package com.tech_centriq.tripservice.client.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverValidationResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
