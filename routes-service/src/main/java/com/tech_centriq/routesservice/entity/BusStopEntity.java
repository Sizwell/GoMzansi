package com.tech_centriq.routesservice.entity;

import com.tech_centriq.routesservice.enums.BusStopStatus;
import com.tech_centriq.routesservice.enums.RouteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bus_stops")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BusStopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String stopCode;

    @Column(nullable = false)
    private String stopName;

    private String locationDescription;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    private BusStopStatus status;

    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
