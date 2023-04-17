package com.bridgelabz.googlekeep.service;

import java.util.List;

import com.bridgelabz.googlekeep.dto.UserDto;
import com.bridgelabz.googlekeep.model.User;

public interface IUserService {

	String registerUser(UserDto userDto);

	User getUserById(int id);

	String login(String email, String password);

	List<User> getAllUsers();

	String deleteUserById(int id);

}
