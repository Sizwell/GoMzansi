package com.tech_centriq.routesservice.route.entity;

import com.tech_centriq.routesservice.route.enums.RouteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String routeCode;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false)
    private String startLocation;

    @Column(nullable = false)
    private String endLocation;

    private Double distanceInKM;

    private Integer estimatedDurationInMinutes;

    @Enumerated(EnumType.STRING)
    private RouteStatus status;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
