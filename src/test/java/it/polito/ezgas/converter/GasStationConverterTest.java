package it.polito.ezgas.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class GasStationConverterTest {

	@Test
	public void testToGasStation() {
		GasStationDto gasStationDto = new GasStationDto(14, "ENIO", "Via vittoria 10", true,
				false, true, true, true, false, "car_sharing", 45.002, 7.03, 1.0, 2.5,
				2.3, 11.3, 0.003, 3.2, 12, null, 0.0);

		//UserDto userDto = new UserDto(2, "test", "test", "test@test.com", 1, true);
		//gasStationDto.setUserDto(userDto);

		GasStation g = GasStationConverter.toGasStation(gasStationDto);

		assertEquals("ENIO", g.getGasStationName());
		assertEquals("Via vittoria 10", g.getGasStationAddress());
		assertEquals(45.002, g.getLat(), 0.0001);
		assertEquals(7.03, g.getLon(), 0.0001);
		assertEquals(Integer.valueOf(14), g.getGasStationId());
		assertNull(g.getReportTimestamp());
		assertTrue(g.getHasDiesel());
		assertFalse(g.getHasSuper());
		assertTrue(g.getHasSuperPlus());
		assertTrue(g.getHasGas());
		assertTrue(g.getHasMethane());
		assertEquals("car_sharing", g.getCarSharing());
		assertEquals(1.0, g.getDieselPrice(), 0.001);
		assertEquals(2.5, g.getSuperPrice(), 0.001);
		assertEquals(2.3, g.getSuperPlusPrice(), 0.001);
		assertEquals(11.3, g.getGasPrice(), 0.001);
		assertEquals(0.003, g.getMethanePrice(), 0.001);
		assertEquals(Integer.valueOf(12), g.getReportUser());
		assertEquals(0.0, g.getReportDependability(), 0.01);
	}

	@Test
	public void testToGasStationDto() {
		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true,
				false, true, true, true, true, "car_sharing", 45.002, 7.03, 1.0, 2.5,
				2.3, 11.3, 0.003, 2.99, 12, null, 0.0);

		gasStation.setGasStationId(100);

		GasStationDto g = GasStationConverter.toGasStationDto(gasStation);

		assertEquals("ENIO", g.getGasStationName());
		assertEquals("Via vittoria 10", g.getGasStationAddress());
		assertEquals(45.002, g.getLat(), 0.0001);
		assertEquals(7.03, g.getLon(), 0.0001);
		assertEquals(Integer.valueOf(100), g.getGasStationId());
		assertNull(g.getReportTimestamp());
		assertTrue(g.getHasDiesel());
		assertFalse(g.getHasSuper());
		assertTrue(g.getHasSuperPlus());
		assertTrue(g.getHasGas());
		assertTrue(g.getHasMethane());
		assertEquals("car_sharing", g.getCarSharing());
		assertEquals(1.0, g.getDieselPrice(), 0.001);
		assertEquals(2.5, g.getSuperPrice(), 0.001);
		assertEquals(2.3, g.getSuperPlusPrice(), 0.001);
		assertEquals(11.3, g.getGasPrice(), 0.001);
		assertEquals(0.003, g.getMethanePrice(), 0.001);
		assertEquals(Integer.valueOf(12), g.getReportUser());
		assertEquals(0.0, g.getReportDependability(), 0.01);
	}

}
