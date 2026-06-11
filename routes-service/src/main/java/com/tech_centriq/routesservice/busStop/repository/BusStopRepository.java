package com.tech_centriq.routesservice.busStop.repository;

import com.tech_centriq.routesservice.busStop.entity.BusStopEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusStopRepository extends JpaRepository<BusStopEntity, Long> {

    boolean existsByStopCode(String stopCode);

    List<BusStopEntity> findByIsActiveTrue();

    Long id(Long id);

    Optional<BusStopEntity> findByStopCode(String stopCode);
}
