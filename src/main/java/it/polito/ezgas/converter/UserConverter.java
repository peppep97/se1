package it.polito.ezgas.converter;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {

	public static User toUser(UserDto userDto) {
		User user = new User(userDto.getUserName(), 
								userDto.getPassword(), 
								userDto.getEmail(), 
								userDto.getReputation()); ;
        user.setUserId(userDto.getUserId());
		user.setAdmin(userDto.getAdmin());
		return user;
	}

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto(user.getUserId(), 
										user.getUserName(), 
										user.getPassword(), 
										user.getEmail(), 
										user.getReputation(),
										user.getAdmin());
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
