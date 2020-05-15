package it.polito.ezgas.converter;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {

	public static User toUser(UserDto userDto) {
		User user = new User();		
		
		if(userDto==null)
			return null;
		
        user.setUserId(userDto.getUserId());
		user.setAdmin(userDto.getAdmin());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		
		if(userDto.getReputation()!=null)
			user.setReputation(userDto.getReputation());
		else
			user.setReputation(0);
		
		return user;
	}

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		
		if(user==null)
			return null;
		
		userDto.setUserId(user.getUserId());
		userDto.setAdmin(user.getAdmin());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		
		if(user.getReputation()!=null)
			userDto.setReputation(user.getReputation());
		else
			userDto.setReputation(0);
		
		return userDto;
	}

	public static LoginDto toLoginDto(User user) {
		LoginDto loginDto= new LoginDto (user.getUserId(), 
										user.getUserName(), 
										"init_token", 
										user.getEmail(), 
										user.getReputation());
		loginDto.setAdmin(user.getAdmin());
		return loginDto;
	}
}
