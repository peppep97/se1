package it.polito.ezgas.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class GasStationTest {

    @Test
    public void testGasStation() {

        GasStation gasStation = new GasStation();

        gasStation.setCarSharing("company_name");
        assertEquals("company_name", gasStation.getCarSharing());

        gasStation.setGasStationAddress("station_address");
        assertEquals("station_address", gasStation.getGasStationAddress());

        gasStation.setLat(40.2);
        assertEquals(40.2, gasStation.getLat(), 0.0001);

        gasStation.setLon(8.15);
        assertEquals(8.15, gasStation.getLon(), 0.0001);

        gasStation.setGasStationId(-1);
        assertEquals(Integer.valueOf(-1), gasStation.getGasStationId());

        gasStation.setReportTimestamp("timestamp");
        assertEquals("timestamp", gasStation.getReportTimestamp());

        gasStation.setHasDiesel(true);
        assertTrue(gasStation.getHasDiesel());

        gasStation.setHasSuperPlus(false);
        assertFalse(gasStation.getHasSuperPlus());

        gasStation.setGasPrice(1.567);
        assertEquals(1.567, gasStation.getGasPrice(), 0.001);

        gasStation.setSuperPrice(2);
        assertEquals(2, gasStation.getSuperPrice(), 0.001);

        gasStation.setReportUser(0);
        assertEquals(Integer.valueOf(0), gasStation.getReportUser());

        gasStation.setReportDependability(48.0);
        assertEquals(48.0, gasStation.getReportDependability(), 0.01);

    }
}