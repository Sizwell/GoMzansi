package com.tech_centriq.tripservice.dto.request;

import com.tech_centriq.tripservice.enums.TripStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateTripRequestDTO {

    private String routeCode;
    private String busNumber;
    private Long driverId;
    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime scheduledArrivalTime;
    private TripStatus tripStatus;
}
