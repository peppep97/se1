package it.polito.ezgas.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdPwTest {

    @Test
    public void testIdPw() {
        IdPw idPw = new IdPw();

        idPw.setPw("<>><><<<>");
        assertEquals("<>><><<<>", idPw.getPw());

        idPw.setUser("Mario");
        assertEquals("Mario", idPw.getUser());
        
        IdPw idPw1 = new IdPw("id", "pass");
        assertEquals("id", idPw1.getUser());
        assertEquals("pass", idPw1.getPw());
    }
}