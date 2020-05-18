package it.polito.ezgas.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDtoTest {

    @Test
    public void testUserDto() {
        UserDto userDto = new UserDto();

        userDto.setReputation(2);
        assert userDto.getReputation() == 2;

        userDto.setAdmin(false);
        assert !userDto.getAdmin();

        userDto.setEmail("bipbop@v.c");
        assert userDto.getEmail().equals("bipbop@v.c");

        userDto.setPassword("xxxxxxxxxxx");
        assert userDto.getPassword().equals("xxxxxxxxxxx");

        userDto.setUserId(234);
        assert userDto.getUserId() == 234;

        userDto.setUserName("nessuno");
        assert userDto.getUserName().equals("nessuno");
    }

}