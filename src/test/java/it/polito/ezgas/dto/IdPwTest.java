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
    }
}