package com.bridgelabz.googlekeep.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bridgelabz.googlekeep.dto.UserDto;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	
	public User(UserDto userDto)
	{
		this.firstName=userDto.getFirstName();
		this.lastName=userDto.getLastName();
		this.email=userDto.getEmail();		
		this.password=userDto.getPassword();
		this.address=userDto.getAddress();
		
	}
	

}
