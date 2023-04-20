package com.bridgelabz.googlekeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.googlekeep.dto.UserDto;
import com.bridgelabz.googlekeep.exception.GoogleKeepException;
import com.bridgelabz.googlekeep.model.User;
import com.bridgelabz.googlekeep.repository.UserRepository;
import com.bridgelabz.googlekeep.util.EmailSenderService;
import com.bridgelabz.googlekeep.util.TokenUtil;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository repository;
	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	EmailSenderService emailSenderService;

	@Override
	public String registerUser(UserDto userDto) {
		User newUser=new User(userDto);
		repository.save(newUser);
		String token=tokenUtil.createToken(newUser.getUserId());
		//emailSenderService.sendEmail(newUser.getEmail(), "User added ....","User name is :"+newUser.getFirstName()+" Token is :" +token);
		return token;
	}

	@Override
	public User getUserById(int id) {
		return repository.findById(id).
				orElseThrow(()->new GoogleKeepException("Invalid ID"));
		
	}

	@Override
	public String login(String email, String password)throws GoogleKeepException {
		User userModels=repository.findByEmail(email);
		if(userModels!=null)
		{
		if(password.equals(userModels.getPassword()))
		{
			String token=tokenUtil.createToken(userModels.getUserId());
			return token;
		}
		
		throw new GoogleKeepException("Invalid password");
		}
		
		throw new GoogleKeepException("Invalid email ");
	
	}

	@Override
	public List<User> getAllUsers() {
		
		return repository.findAll();
	}

	@Override
	public String deleteUserById(int id) {
		Optional<User> userModel=repository.findById(id);
		if(userModel!=null)
		{
			repository.deleteById(id);
			return "User deleted";
		}
		
		return "User not present so not deleted";
	}

	public boolean isValidUser(String token) throws GoogleKeepException
	{
		int userId=tokenUtil.decodeToken(token);
		Optional<User> user=repository.findById(userId);
		if(user.isPresent())
		{
			return true;
		}
		else {
			throw new GoogleKeepException("User is invalid");
		}
	}
}
