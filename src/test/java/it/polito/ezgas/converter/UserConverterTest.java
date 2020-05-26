package it.polito.ezgas.converter;

import org.junit.Test;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

import static org.junit.Assert.*;

public class UserConverterTest {

	@Test
	public void testToUser() {
		UserDto userDto = new UserDto(1, "test", "test", "test@test.com", 1);
		userDto.setAdmin(false);

		User u = UserConverter.toUser(userDto);

		assertNotNull(u);
		assertEquals(Integer.valueOf(1), u.getUserId());
		assertEquals("test", u.getUserName());
		assertEquals("test", u.getPassword());
		assertEquals("test@test.com", u.getEmail());
		assertEquals(Integer.valueOf(1),u.getReputation());
		assertFalse(u.getAdmin());
		
		UserDto userDto1 = null;
		assertNull(UserConverter.toUser(userDto1));
		
		UserDto userDto2 = new UserDto(1, "test", "test", "test@test.com", null);

		User u2 = UserConverter.toUser(userDto2);
		assertNotNull(u2);
		assertEquals(Integer.valueOf(0), u2.getReputation());
	}

	@Test
	public void testToUserDto() {
		User user = new User("admin", "admin", "admin@gmail.com", 1);
		user.setAdmin(false);
		
		UserDto u = UserConverter.toUserDto(user);
		assertNotNull(u);
    	assertEquals("admin", u.getUserName());
    	assertEquals("admin", u.getPassword());
    	assertEquals("admin@gmail.com", u.getEmail());
    	assertEquals(Integer.valueOf(1), u.getReputation());
    	assertFalse(u.getAdmin());
    	
    	User user1 = null;
    	assertNull(UserConverter.toUserDto(user1));
    	
    	User user2 = new User("test", "test", "test@test.com", null);

    	UserDto u2 = UserConverter.toUserDto(user2);
    	assertNotNull(u2);
		assertEquals(Integer.valueOf(0), u2.getReputation());
  
	}
	
	@Test
	public void testToLoginDto() {
		User user = new User("admin", "admin", "admin@gmail.com", 1);
		user.setAdmin(true);
		
		LoginDto u = UserConverter.toLoginDto(user);
		
    	assertEquals("admin", u.getUserName());
    	assertEquals("init_token", u.getToken());
    	assertEquals("admin@gmail.com", u.getEmail());
    	assertEquals(Integer.valueOf(1), u.getReputation());
    	assertTrue(u.getAdmin());
  
	}

}
