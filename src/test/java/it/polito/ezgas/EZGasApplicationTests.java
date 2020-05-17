package it.polito.ezgas;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jnlp.ServiceManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {

	// It's bad practice to test Service using real Repository implementation (?)

	@Autowired
	private GasStationService gasStationService;

	@Autowired
	private GasStationRepository gasStationRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGas() throws PriceException, GPSDataException {

		gasStationRepository.deleteAll();

		GasStationDto given = new GasStationDto();
		given.setGasStationName("nome");
		given.setHasGas(true);
		given.setLat(45.3);
		given.setLon(7.2);
		given.setGasStationAddress("chissadove");

		GasStationDto found = gasStationService.saveGasStation(given);
		assert found.getGasStationName().equals(given.getGasStationName());

	}

	@Test
	public void testDieselSortPrice() throws PriceException, GPSDataException, InvalidGasTypeException {

		gasStationRepository.deleteAll();

		GasStationDto cheap = new GasStationDto();
		cheap.setGasStationName("dagigi");
		cheap.setHasDiesel(true);
		cheap.setLat(44.4);
		cheap.setLon(8.1);
		cheap.setGasStationAddress("torinoo, itaglia");
		cheap.setDieselPrice(1.0);

		gasStationService.saveGasStation(cheap);

		GasStationDto notSoCheap = new GasStationDto();
		notSoCheap.setGasStationName("Agrip");
		notSoCheap.setHasDiesel(true);
		notSoCheap.setLat(44.3);
		notSoCheap.setLon(8.1);
		notSoCheap.setGasStationAddress("corso francia, torino");
		notSoCheap.setDieselPrice(2.0);

		gasStationService.saveGasStation(notSoCheap);

		List<GasStationDto> stations = gasStationService.getGasStationsByGasolineType("Diesel");
		assert stations.get(0).getDieselPrice() < stations.get(1).getDieselPrice();

	}

}
