package com.wangshijia.service.superAdmin;

import java.util.List;

import com.wangshijia.model.User;

public interface UserService {
	List<User> getUsers();
	void saveUsers(List<User> users);
}
