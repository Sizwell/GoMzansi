package com.tech_centriq.employeeservice.repository.driver;

import com.tech_centriq.employeeservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    List<Driver> findByIsActiveTrue();
    List<Driver> findByIsActiveFalse();
}
