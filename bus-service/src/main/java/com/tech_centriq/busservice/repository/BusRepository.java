package com.tech_centriq.busservice.repository;

import com.tech_centriq.busservice.entity.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<BusEntity, Long> {

    Optional<BusEntity> findByPlateNumber(String plateNumber);

    Optional<BusEntity> findByBusNumber(String busNumber);

    boolean existsByPlateNumber(String plateNumber);

    boolean existsByBusNumber(String busNumber);

    List<BusEntity> findByIsActiveTrue();
}
