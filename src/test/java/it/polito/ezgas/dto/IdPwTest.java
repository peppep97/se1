package it.polito.ezgas.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdPwTest {

    @Test
    public void testIdPw() {
        IdPw idPw = new IdPw();

        idPw.setPw("<>><><<<>");
        assert idPw.getPw().equals("<>><><<<>");

        idPw.setUser("Mario");
        assert idPw.getUser().equals("Mario");
    }
}