package it.polito.ezgas.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testUser() {

        User user = new User();

        user.setReputation(3);
        assertEquals(Integer.valueOf(3), user.getReputation());

        user.setAdmin(true);
        assertEquals(true, user.getAdmin());

        user.setEmail("mail_address");
        assertEquals("mail_address", user.getEmail());

        user.setPassword("long123");
        assertEquals("long123", user.getPassword());

        user.setUserId(3);
        assertEquals(Integer.valueOf(3), user.getUserId());

        user.setUserName("mario");
        assertEquals("mario", user.getUserName());
        
    	User user1 = new User("admin", "admin", "admin@gmail.com", 1);
    	assertEquals("admin", user1.getUserName());
    	assertEquals("admin", user1.getPassword());
    	assertEquals("admin@gmail.com", user1.getEmail());
    	assertEquals(Integer.valueOf(1), user1.getReputation());

    }
}