package com.tech_centriq.routesservice.route.repository;

import com.tech_centriq.routesservice.route.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

    List<RouteEntity> findByIsActiveTrue();

    boolean existsByRouteCode(String routeCode);

    boolean existsByStartLocationAndEndLocation(RouteEntity startLocation, RouteEntity endLocation);

    Optional<RouteEntity> findByRouteCode(String routeCode);
}
