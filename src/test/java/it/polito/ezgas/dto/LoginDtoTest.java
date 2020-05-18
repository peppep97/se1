package it.polito.ezgas.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginDtoTest {

    @Test
    public void testLoginDto() {
        LoginDto loginDto = new LoginDto();

        loginDto.setAdmin(true);
        assert loginDto.getAdmin();

        loginDto.setEmail("a@@@@@@comcom");
        assert loginDto.getEmail().equals("a@@@@@@comcom");

        loginDto.setReputation(-1);
        assert loginDto.getReputation() == -1;

        loginDto.setUserId(11);
        assert loginDto.getUserId() == 11;

        loginDto.setUserName("assertEquals");
        assert loginDto.getUserName().equals("assertEquals");

    }
}