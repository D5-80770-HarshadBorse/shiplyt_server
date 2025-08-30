package com.shiplyt.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shiplyt.exception.UserNotFoundException;
import com.shiplyt.model.dto.UserUpdateRequest;
import com.shiplyt.model.entity.User;
import com.shiplyt.repository.UserRepository;
import com.shiplyt.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + id, HttpStatus.BAD_REQUEST));
	}

	@Override
	public User updateUser(Long id, UserUpdateRequest request) {
		User user = getUserById(id);

		Optional.ofNullable(request.getName()).filter(name -> !name.trim().isEmpty()).ifPresent(user::setName);

		Optional.ofNullable(request.getPhone()).filter(phone -> !phone.trim().isEmpty()).ifPresent(user::setPhone);

		Optional.ofNullable(request.getEmail()).filter(email -> !email.trim().isEmpty()).ifPresent(user::setEmail);

		Optional.ofNullable(request.getPassword()).filter(pwd -> !pwd.trim().isEmpty())
				.ifPresent(pwd -> user.setPasswordHash(passwordEncoder.encode(pwd)));

		return userRepository.save(user);
	}

}
