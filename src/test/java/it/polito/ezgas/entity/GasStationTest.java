package it.polito.ezgas.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class GasStationTest {

    @Test
    public void testGasStation() {

        GasStation gasStation = new GasStation();

        gasStation.setCarSharing("company_name");
        assert gasStation.getCarSharing().equals("company_name");

        gasStation.setGasStationAddress("station_address");
        assert gasStation.getGasStationAddress().equals("station_address");

        gasStation.setLat(40.2);
        assert gasStation.getLat() == 40.2;

        gasStation.setLon(8.15);
        assert gasStation.getLon() == 8.15;

        gasStation.setGasStationId(-1);
        assert gasStation.getGasStationId() == -1;

        gasStation.setReportTimestamp("timestamp");
        assert gasStation.getReportTimestamp().equals("timestamp");

        gasStation.setHasDiesel(true);
        assert gasStation.getHasDiesel();

        gasStation.setHasSuperPlus(false);
        assert !gasStation.getHasSuperPlus();

        gasStation.setGasPrice(1.567);
        assert gasStation.getGasPrice() == 1.567;

        gasStation.setSuperPrice(2);
        assert gasStation.getSuperPrice() == 2;

        gasStation.setReportUser(0);
        assert gasStation.getReportUser() == 0;

        gasStation.setReportDependability(48.0);
        assert gasStation.getReportDependability() == 48.0;

    }
}