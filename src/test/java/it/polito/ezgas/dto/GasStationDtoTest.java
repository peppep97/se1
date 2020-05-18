package it.polito.ezgas.dto;

import it.polito.ezgas.entity.GasStation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GasStationDtoTest {

    @Test
    public void testGasStationDto() {
        GasStationDto gasStationDto = new GasStationDto();

        gasStationDto.setCarSharing("company_name");
        assertEquals("company_name", gasStationDto.getCarSharing());

        gasStationDto.setGasStationAddress("station_address");
        assertEquals("station_address", gasStationDto.getGasStationAddress());

        gasStationDto.setLat(40.2);
        assertEquals(40.2, gasStationDto.getLat(), 0.0001);

        gasStationDto.setLon(8.15);
        assertEquals(8.15, gasStationDto.getLon(), 0.0001);

        gasStationDto.setGasStationId(-1);
        assertEquals(Integer.valueOf(-1), gasStationDto.getGasStationId());

        gasStationDto.setReportTimestamp("timestamp");
        assertEquals("timestamp", gasStationDto.getReportTimestamp());

        gasStationDto.setHasDiesel(true);
        assertTrue(gasStationDto.getHasDiesel());

        gasStationDto.setHasSuperPlus(false);
        assertFalse(gasStationDto.getHasSuperPlus());

        gasStationDto.setGasPrice(1.567);
        assertEquals(1.567, gasStationDto.getGasPrice(), 0.001);

        gasStationDto.setSuperPrice(2);
        assertEquals(2, gasStationDto.getSuperPrice(), 0.001);

        gasStationDto.setReportUser(0);
        assertEquals(Integer.valueOf(0), gasStationDto.getReportUser());

        gasStationDto.setReportDependability(48.0);
        assertEquals(48.0, gasStationDto.getReportDependability(), 0.01);
    }
}