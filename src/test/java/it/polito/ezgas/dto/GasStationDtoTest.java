package it.polito.ezgas.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

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

        gasStationDto.setSuperPrice(2.1);
        assertEquals(2.1, gasStationDto.getSuperPrice(), 0.001);

        gasStationDto.setReportUser(0);
        assertEquals(Integer.valueOf(0), gasStationDto.getReportUser());

        gasStationDto.setReportDependability(48.0);
        assertEquals(48.0, gasStationDto.getReportDependability(), 0.01);

        GasStationDto gasStationDto1 = new GasStationDto(14,
                "ENIO",
                "Via vittoria 10",
                true,
                false,
                true,
                true,
                true,
                false,
                "boh",
                45.002,
                7.03,
                1.0,
                2.5,
                2.3,
                11.3,
                0.003,
                2.78,
                12,
                "ora",
                22.22);

        gasStationDto1.setGasStationName("EnerCorp");
        assertEquals("EnerCorp", gasStationDto1.getGasStationName());

        gasStationDto1.setHasSuper(false);
        assertFalse(gasStationDto1.getHasSuper());

        gasStationDto1.setHasGas(true);
        assertTrue(gasStationDto1.getHasGas());

        gasStationDto1.setDieselPrice(3.4);
        assertEquals(3.4, gasStationDto1.getDieselPrice(), 0.001);

        gasStationDto1.setSuperPlusPrice(9.0);
        assertEquals(9.0, gasStationDto1.getSuperPlusPrice(), 0.001);

        gasStationDto1.setHasMethane(true);
        assertTrue(gasStationDto1.getHasMethane());

        gasStationDto1.setMethanePrice(4.20);
        assertEquals(4.20, gasStationDto1.getMethanePrice(), 0.001);
    }
}
