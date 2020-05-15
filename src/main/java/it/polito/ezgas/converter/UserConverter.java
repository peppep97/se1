package it.polito.ezgas.converter;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {
	
	// Covert from userdto to user
	public static User toUser(UserDto userDto){
		
		User user = new User();		
		// Check if userdto is empty
		if(userDto == null)
			return null;
		// Convert attriubutes
        user.setUserId(userDto.getUserId());
		user.setAdmin(userDto.getAdmin());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		// Check if userdto has a valid reputation
		if(userDto.getReputation() != null)
			user.setReputation(userDto.getReputation());
		else
			user.setReputation(0);
		
		return user;
	}
	
	// Convert from user to userdto
	public static UserDto toUserDto(User user){
		
		UserDto userDto = new UserDto();
		// Check if user is empty
		if(user == null)
			return null;
		// Convert attriubutes
		userDto.setUserId(user.getUserId());
		userDto.setAdmin(user.getAdmin());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		// Check if user has a valid reputation
		if(user.getReputation() != null)
			userDto.setReputation(user.getReputation());
		else
			userDto.setReputation(0);
		
		return userDto;
	}

	// Retrieve a logindto
	public static LoginDto toLoginDto(User user){
		
		LoginDto loginDto = new LoginDto (user.getUserId(), user.getUserName(), "init_token", user.getEmail(), user.getReputation());
		loginDto.setAdmin(user.getAdmin());
		
		return loginDto;
	}
}
