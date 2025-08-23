package com.pakgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakgo.model.entity.DriverLocation;
import com.pakgo.model.entity.Driver;

import java.util.Optional;

public interface DriverLocationRepository extends JpaRepository<DriverLocation, Long> {
    Optional<DriverLocation> findByDriverProfile(Driver driverProfile);
}
