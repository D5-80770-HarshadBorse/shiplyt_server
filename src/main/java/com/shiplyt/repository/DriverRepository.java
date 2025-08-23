package com.shiplyt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiplyt.model.entity.Driver;
import com.shiplyt.model.entity.User;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	Optional<Driver> findByUser(User user);

	List<Driver> findByStatus(Driver.Status status);
}
