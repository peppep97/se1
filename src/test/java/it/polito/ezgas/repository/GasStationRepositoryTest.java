package it.polito.ezgas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import it.polito.ezgas.entity.GasStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest

public class GasStationRepositoryTest {

	@Autowired
	private GasStationRepository gasStationRepository;

	@Test
	public void testSaveGasStation() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		GasStation savedGasStation = gasStationRepository.save(gasStation);

		GasStation findGasStation = gasStationRepository.findByGasStationId(savedGasStation.getGasStationId());
		assertNotNull(findGasStation);

	}

	@Test
	public void testDeleteGasStation() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		GasStation savedGasStation = gasStationRepository.save(gasStation);
		
		GasStation findGasStation = gasStationRepository.findByGasStationId(savedGasStation.getGasStationId());
		assertNotNull(findGasStation);
		
		gasStationRepository.delete(findGasStation);

		GasStation findGasStation1 = gasStationRepository.findByGasStationId(savedGasStation.getGasStationId());
		assertNull(findGasStation1);
	}

	@Test
	public void testDeleteAll() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);

		GasStation gasStation1 = new GasStation("Q8", "Via vittoria 10", false, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation1);

		List<GasStation> list = gasStationRepository.findAll();
		assertTrue(list.size() > 0);

		gasStationRepository.deleteAll();

		List<GasStation> list1 = gasStationRepository.findAll();
		assertEquals(list1.size(), 0);
	}
	
	@Test
	public void testFindByGasStationId() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		GasStation savedGasStation = gasStationRepository.save(gasStation);

		GasStation findGasStation = gasStationRepository.findByGasStationId(savedGasStation.getGasStationId());
		assertNotNull(findGasStation);

	}

	@Test
	public void testFindAll() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);

		GasStation gasStation1 = new GasStation("Q8", "Via vittoria 10", false, false, true, true,true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation1);

		List<GasStation> list = gasStationRepository.findAll();
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testFindByHasDieselTrue() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, false, false, false,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasDieselTrue();
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasSuperTrue() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, true, false, false, false,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasSuperTrue();
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasSuperPlusTrue() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, false, true, false, false,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasSuperPlusTrue();
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasGasTrue() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, false, false, true, false,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasGasTrue();
		assertEquals(list.size(), 1);
	}

	@Test
	public void testFindByHasMethaneTrue() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, false, false, false, true,
				"boh", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasMethaneTrue();
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasDieselTrueAndCarSharing() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", true, false, false, false, false,
				"testsharing", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasDieselTrueAndCarSharing("testsharing");
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasSuperTrueAndCarSharing() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, true, false, false, false,
				"testsharing", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasSuperTrueAndCarSharing("testsharing");
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasSuperPlusTrueAndCarSharing() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, false, true, false, false,
				"testsharing", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasSuperPlusTrueAndCarSharing("testsharing");
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testFindByHasGasTrueAndCarSharing() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, false, false, true, false,
				"testsharing", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasGasTrueAndCarSharing("testsharing");
		assertEquals(list.size(), 1);
	}

	@Test
	public void testFindByHasMethaneTrueAndCarSharing() {

		GasStation gasStation = new GasStation("ENIO", "Via vittoria 10", false, false, false, false, true,
				"testsharing", 45.002, 7.03, 1.0, 2.5, 2.3, 11.3, 0.003, 12, "ora", 22.22);
		gasStationRepository.save(gasStation);
		
		List<GasStation> list = gasStationRepository.findByHasMethaneTrueAndCarSharing("testsharing");
		assertEquals(list.size(), 1);
	}

}
