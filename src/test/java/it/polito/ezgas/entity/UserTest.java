package it.polito.ezgas.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUser() {

        User user = new User();

        user.setReputation(3);
        assert user.getReputation() == 3;

        user.setAdmin(true);
        assert user.getAdmin();

        user.setEmail("mail_address");
        assert user.getEmail().equals("mail_address");

        user.setPassword("long123");
        assert user.getPassword().equals("long123");

        user.setUserId(3);
        assert user.getUserId() == 3;

        user.setUserName("mario");
        assert user.getUserName().equals("mario");

    }
}