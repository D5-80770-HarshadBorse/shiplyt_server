package com.shiplyt.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
	private String token;
	private String name;
	private String phone;
	private String email;
}