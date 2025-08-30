package com.shiplyt.service;

import com.shiplyt.model.dto.UserUpdateRequest;
import com.shiplyt.model.entity.User;

public interface UserService {
	User getUserById(Long id);

	User updateUser(Long id, UserUpdateRequest request);
}
