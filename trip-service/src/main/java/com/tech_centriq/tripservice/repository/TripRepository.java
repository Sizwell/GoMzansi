package com.tech_centriq.tripservice.repository;

import com.tech_centriq.tripservice.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {

    boolean existsByTripCode(String tripCode);

    Optional<TripEntity> findByTripCode(String tripCode);

    List<TripEntity> findByIsActiveTrue();

//    TripEntity findById(Long id);
}
