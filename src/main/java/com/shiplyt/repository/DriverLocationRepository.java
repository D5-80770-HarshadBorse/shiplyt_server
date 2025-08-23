package com.shiplyt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiplyt.model.entity.Driver;
import com.shiplyt.model.entity.DriverLocation;

import java.util.Optional;

public interface DriverLocationRepository extends JpaRepository<DriverLocation, Long> {
    Optional<DriverLocation> findByDriverProfile(Driver driverProfile);
}
