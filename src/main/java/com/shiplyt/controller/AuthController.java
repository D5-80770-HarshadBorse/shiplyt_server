package com.shiplyt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiplyt.exception.EmailAlreadyExistsException;
import com.shiplyt.exception.InvalidCredentialsException;
import com.shiplyt.model.dto.AuthResponse;
import com.shiplyt.model.dto.LoginRequest;
import com.shiplyt.model.dto.RegisterRequest;
import com.shiplyt.model.entity.Driver;
import com.shiplyt.model.entity.User;
import com.shiplyt.repository.DriverRepository;
import com.shiplyt.repository.UserRepository;
import com.shiplyt.security.CustomUserDetails;
import com.shiplyt.security.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserRepository userRepository;
	private final DriverRepository driverProfileRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authManager;

	@PostMapping("/register")
	public String register(@Valid @RequestBody RegisterRequest request) {
		if (userRepository.existsByEmailOrPhone(request.getEmail(), request.getPhone())) {
			throw new EmailAlreadyExistsException("Email or Phone already registered", HttpStatus.BAD_REQUEST);
		}

		User user = User.builder().name(request.getName()).email(request.getEmail()).phone(request.getPhone())
				.passwordHash(passwordEncoder.encode(request.getPassword())).role(User.Role.valueOf(request.getRole()))
				.build();

		userRepository.save(user);

		if (user.getRole() == User.Role.DRIVER) {
			Driver driverProfile = Driver.builder().licenseNumber(request.getLicenseNumber())
					.vehicleNumber(request.getVehicleNumber()).user(user).status(Driver.Status.OFFLINE).build();
			driverProfileRepository.save(driverProfile);
		}

		return "User registered successfully";
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {
		User user;
		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
			user = customUserDetails.getUser();
		} catch (Exception ex) {
			throw new InvalidCredentialsException("Invalid email or password", HttpStatus.BAD_REQUEST);
		}
		String token = jwtUtil.generateToken(request.getEmail());
		return AuthResponse.builder().token(token).email(user.getEmail()).name(user.getName()).phone(user.getPhone())
				.build();
	}
}
