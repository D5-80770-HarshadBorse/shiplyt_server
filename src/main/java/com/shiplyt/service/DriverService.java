package com.shiplyt.service;

import java.util.List;
import java.util.Optional;

import com.shiplyt.model.entity.Driver;

public interface DriverService {
	List<Driver> getAllDrivers();

	Optional<Driver> getDriverById(Long id);

	Driver saveDriver(Driver driver);

	void deleteDriver(Long id);

	Driver updateDriverStatus(Long driverId, Driver.Status status);

	List<Driver> getDriversByStatus(Driver.Status status);
}
