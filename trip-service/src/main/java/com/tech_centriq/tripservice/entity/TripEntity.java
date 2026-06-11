package com.tech_centriq.tripservice.entity;

import com.tech_centriq.tripservice.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tripCode;

    @Column(nullable = false)
    private String routeCode;

    @Column(nullable = false)
    private String busNumber;

    @Column(nullable = false)
    private Long driverId;

    private LocalDateTime scheduledDepartureTime;

    private LocalDateTime scheduledArrivalTime;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
