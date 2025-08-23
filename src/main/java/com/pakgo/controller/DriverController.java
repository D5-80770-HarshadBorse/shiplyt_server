package com.pakgo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pakgo.model.entity.Driver;
import com.pakgo.service.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

	private final DriverService driverService;

	@GetMapping
	public ResponseEntity<List<Driver>> getAllDrivers() {
		return ResponseEntity.ok(driverService.getAllDrivers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
		return driverService.getDriverById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
		return ResponseEntity.ok(driverService.saveDriver(driver));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Driver> updateDriverStatus(@PathVariable Long id, @RequestParam Driver.Status status) {
		Driver updatedDriver = driverService.updateDriverStatus(id, status);
		return ResponseEntity.ok(updatedDriver);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
		driverService.deleteDriver(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<Driver>> getDriversByStatus(@PathVariable Driver.Status status) {
		return ResponseEntity.ok(driverService.getDriversByStatus(status));
	}
}
