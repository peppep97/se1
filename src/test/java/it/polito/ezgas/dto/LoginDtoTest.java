package it.polito.ezgas.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginDtoTest {

    @Test
    public void testLoginDto() {
        LoginDto loginDto = new LoginDto();

        loginDto.setAdmin(true);
        assertTrue(loginDto.getAdmin());

        loginDto.setEmail("a@@@@@@comcom");
        assertEquals("a@@@@@@comcom", loginDto.getEmail());

        loginDto.setReputation(-1);
        assertEquals(Integer.valueOf(-1), loginDto.getReputation());

        loginDto.setUserId(11);
        assertEquals(Integer.valueOf(11), loginDto.getUserId());

        loginDto.setUserName("assertEquals");
        assertEquals("assertEquals", loginDto.getUserName());

    }
}