package com.bridgelabz.googlekeep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.googlekeep.dto.ResponseDto;
import com.bridgelabz.googlekeep.dto.UserDto;
import com.bridgelabz.googlekeep.model.User;
import com.bridgelabz.googlekeep.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService service;

	@PostMapping("/register")
	ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserDto userDto) {
		String token = service.registerUser(userDto);
		ResponseDto responseDto = new ResponseDto("User registered successfully ", token);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping("/id/{id}")
	ResponseEntity<ResponseDto> getUserById(@PathVariable int id) {
		User userData = service.getUserById(id);
		ResponseDto responseDto = new ResponseDto("Requested user is : ", userData);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<ResponseDto> userLogin(@RequestParam String email, @RequestParam String password) {

		String token = service.login(email, password);

		ResponseDto responseDto = new ResponseDto("user logged in and token is", token);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/getall")
	ResponseEntity<ResponseDto> getAllUsers() {
		List<User> users = service.getAllUsers();
		ResponseDto responseDto = new ResponseDto("All users are ", users);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/id/{id}")
	ResponseEntity<ResponseDto> deleteUserById(@PathVariable int id) {
		String userModel = service.deleteUserById(id);
		ResponseDto responseDto = new ResponseDto("User deleted or not :", userModel);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
