package com.shiplyt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.shiplyt.model.entity.User;
import com.shiplyt.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SecurityUtil {

	private final UserRepository userRepository;

	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
			return null;
		}
		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetails userDetails) {
			return userRepository.findByEmail(userDetails.getUsername())
					.orElseThrow(() -> new RuntimeException("User not found"));
		}
		return null;
	}

}
