package it.polito.ezgas.converter;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {

	public static User toUser(UserDto userDto) {
		User user = new User();				
        user.setUserId(userDto.getUserId());
		user.setAdmin(userDto.getAdmin());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setReputation(userDto.getReputation());
		
		return user;
	}

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setUserId(user.getUserId());
		userDto.setAdmin(user.getAdmin());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setReputation(user.getReputation());
		
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
