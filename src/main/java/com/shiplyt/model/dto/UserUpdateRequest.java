package com.shiplyt.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@Email(message = "Invalid email format")
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain 10 to 15 digits")
	private String phone;

	@Size(min = 6, max = 100, message = "Password must be at least 6 characters")
	private String password;
}