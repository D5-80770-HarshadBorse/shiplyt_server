package com.pakgo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakgo.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmailOrPhone(String email, String phone);
}
