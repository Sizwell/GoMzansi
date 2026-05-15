package com.tech_centriq.busservice.entity;

import com.tech_centriq.busservice.enums.BusStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "buses")
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String plateNumber;

    @Column(unique = true, nullable = false)
    private String busNumber;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "model", nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    private BusStatus status;

    private boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
