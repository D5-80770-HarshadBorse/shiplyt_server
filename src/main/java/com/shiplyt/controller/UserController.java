package com.shiplyt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiplyt.model.dto.AuthResponse;
import com.shiplyt.model.dto.UserUpdateRequest;
import com.shiplyt.model.entity.User;
import com.shiplyt.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<AuthResponse> getUser(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return ResponseEntity
				.ok(AuthResponse.builder().name(user.getName()).email(user.getEmail()).phone(user.getPhone()).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthResponse> updateUser(@PathVariable Long id,
			@Valid @RequestBody UserUpdateRequest request) {
		User user = userService.updateUser(id, request);
		return ResponseEntity
				.ok(AuthResponse.builder().name(user.getName()).email(user.getEmail()).phone(user.getPhone()).build());
	}
}
