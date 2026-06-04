package com.tech_centriq.routesservice.routeStop.repository;

import com.tech_centriq.routesservice.routeStop.entity.RouteStopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStopEntity, Long> {

    List<RouteStopEntity> findByRouteCodeAndIsActiveTrueOrderByStopOrderAsc(String routeCode);

    boolean existsByRouteCodeAndStopOrder(String routeCode, Integer stopOrder);

    boolean existsByRouteCodeAndStopCode(String routeCode, String stopCode);
}
