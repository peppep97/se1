package it.polito.ezgas.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDtoTest {

    @Test
    public void testUserDto() {
        UserDto userDto = new UserDto();

        userDto.setReputation(3);
        assertEquals(Integer.valueOf(3), userDto.getReputation());

        userDto.setAdmin(true);
        assertEquals(true, userDto.getAdmin());

        userDto.setEmail("mail_address");
        assertEquals("mail_address", userDto.getEmail());

        userDto.setPassword("long123");
        assertEquals("long123", userDto.getPassword());

        userDto.setUserId(3);
        assertEquals(Integer.valueOf(3), userDto.getUserId());

        userDto.setUserName("mario");
        assertEquals("mario", userDto.getUserName());
    }

}