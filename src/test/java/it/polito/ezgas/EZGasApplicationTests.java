package it.polito.ezgas;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {

	@Test
	public void contextLoads() {
	}

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

	@Test
	public void testGasStationDto() {
		GasStationDto gasStationDto = new GasStationDto();


	}

}
