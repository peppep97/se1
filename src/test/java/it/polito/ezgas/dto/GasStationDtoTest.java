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
        assert gasStationDto.getCarSharing().equals("company_name");

        gasStationDto.setGasStationAddress("station_address");
        assert gasStationDto.getGasStationAddress().equals("station_address");

        gasStationDto.setLat(40.2);
        assert gasStationDto.getLat() == 40.2;

        gasStationDto.setLon(8.15);
        assert gasStationDto.getLon() == 8.15;

        gasStationDto.setGasStationId(-1);
        assert gasStationDto.getGasStationId() == -1;

        gasStationDto.setReportTimestamp("timestamp");
        assert gasStationDto.getReportTimestamp().equals("timestamp");

        gasStationDto.setHasDiesel(true);
        assert gasStationDto.getHasDiesel();

        gasStationDto.setHasSuperPlus(false);
        assert !gasStationDto.getHasSuperPlus();

        gasStationDto.setGasPrice(1.567);
        assert gasStationDto.getGasPrice() == 1.567;

        gasStationDto.setSuperPrice(2);
        assert gasStationDto.getSuperPrice() == 2;

        gasStationDto.setReportUser(0);
        assert gasStationDto.getReportUser() == 0;

        gasStationDto.setReportDependability(48.0);
        assert gasStationDto.getReportDependability() == 48.0;
    }
}