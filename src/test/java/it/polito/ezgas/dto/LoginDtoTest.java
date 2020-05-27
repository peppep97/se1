package it.polito.ezgas.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        
        loginDto.setToken("test_token");
        assertEquals("test_token", loginDto.getToken());
        
        LoginDto loginDto1 = new LoginDto(1, "test", "token", "test@test.com", 1);
        assertEquals(Integer.valueOf(1), loginDto1.getUserId());
        assertEquals("test", loginDto1.getUserName());
        assertEquals("token", loginDto1.getToken());
        assertEquals("test@test.com", loginDto1.getEmail());
        assertEquals(Integer.valueOf(1), loginDto1.getReputation());

    }
}