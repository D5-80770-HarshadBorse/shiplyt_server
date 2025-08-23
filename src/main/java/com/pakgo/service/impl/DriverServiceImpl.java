package com.pakgo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pakgo.model.entity.Driver;
import com.pakgo.repository.DriverRepository;
import com.pakgo.service.DriverService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

	private final DriverRepository driverRepository;

	@Override
	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}

	@Override
	public Optional<Driver> getDriverById(Long id) {
		return driverRepository.findById(id);
	}

	@Override
	public Driver saveDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public void deleteDriver(Long id) {
		driverRepository.deleteById(id);
	}

	@Override
	public Driver updateDriverStatus(Long driverId, Driver.Status status) {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
		driver.setStatus(status);
		return driverRepository.save(driver);
	}

	@Override
	public List<Driver> getDriversByStatus(Driver.Status status) {
		return driverRepository.findByStatus(status);
	}
}
