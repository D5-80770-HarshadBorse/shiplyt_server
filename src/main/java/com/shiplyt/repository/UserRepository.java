package com.shiplyt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiplyt.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmailOrPhone(String email, String phone);
}
