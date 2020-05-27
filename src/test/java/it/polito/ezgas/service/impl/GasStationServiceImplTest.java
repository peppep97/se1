package it.polito.ezgas.service.impl;

import exception.*;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(GasStationServiceImpl.class)

public class GasStationServiceImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GasStationRepository gasStationRepository;
    @Autowired
    GasStationServiceImpl gasStationService;

    Integer aId, bId, userId;

    GasStation a = new GasStation("Agippo",
            "Via Agrippa",
            true,
            true,
            true,
            true,
            true,
            "Acar",
            45.0,
            7.0,
            1.0,
            1.2,
            1.3,
            0.9,
            0.8,
            0,
            null,
            40.0);
    GasStation b = new GasStation("BP",
            "Via Brenta",
            true,
            true,
            true,
            true,
            true,
            "Bcar",
            46.001,
            8.0,
            1.0,
            1.2,
            1.3,
            0.9,
            0.8,
            1,
            null,
            50.0);
    GasStation c = new GasStation("Agippo",
            "Via Cesare",
            true,
            true,
            true,
            true,
            true,
            "Ciccar",
            47.002,
            7.0,
            1.0,
            1.2,
            1.3,
            0.9,
            0.8,
            2,
            null,
            60.0);

    GasStationDto gasStationDto = new GasStationDto(14, "ENIO", "Via vittoria 10", true,
            false, true, true, true, "car_sharing", 45.002, 7.03, 1.0, 2.5,
            2.3, 11.3, 0.003, 12, null, 0.0);

    User user = new User("Alice", "Alice", "alice@ezgas.com", 10);

    @Before
    public void setUp() {
        aId = gasStationRepository.save(a).getGasStationId();
        bId = gasStationRepository.save(b).getGasStationId();
        gasStationRepository.save(c);

        userId = userRepository.save(user).getUserId();
    }

    @Test
    public void testGetGasStationById() throws InvalidGasStationException{

        GasStationDto gasStationDto = gasStationService.getGasStationById(bId);

        assertNotNull(gasStationDto);
        assertEquals(gasStationDto.getGasStationId(), bId);
    }

    @Test
    public void testSaveGasStation() throws PriceException, GPSDataException {

        // Inserting new gas station or updating an existing one
        GasStationDto gasStation = gasStationService.saveGasStation(gasStationDto);

        assertNotNull(gasStation);
    }

    @Test
    public void testGetAllGasStations() {
        List<GasStationDto> listGasStation = gasStationService.getAllGasStations();
        assertNotNull(listGasStation);
        assertTrue(listGasStation.size() > 0);
    }

    @Test
    public void deleteGasStation() throws InvalidGasStationException {

        Boolean res = gasStationService.deleteGasStation(aId);

        assertTrue(res);
    }

    @Test
    public void testGetGasStationsByGasolineType() throws InvalidGasTypeException{
        List<GasStationDto> gasStations;

        gasStations = gasStationService.getGasStationsByGasolineType("Diesel");
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsByGasolineType("Super");
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsByGasolineType("SuperPlus");
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsByGasolineType("Gas");
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsByGasolineType("Methane");
        assertEquals(3, gasStations.size());
    }

    @Test
    public void testGetGasStationsByProximity() throws GPSDataException{
        List<GasStationDto> gasStations = gasStationService.getGasStationsByProximity(45, 7);

        assertEquals(1, gasStations.size());
    }

    @Test
    public void testGetGasStationsWithCoordinates() throws InvalidGasTypeException, GPSDataException {
        List<GasStationDto> gasStations;
        double lat = 45, lon = 7;

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Diesel", null);
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Super", null);
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "SuperPlus", null);
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Gas", null);
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Methane", null);
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Diesel", "Bcar");
        assertEquals(0, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Super", "Bcar");
        assertEquals(0, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "SuperPlus", "Bcar");
        assertEquals(0, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Gas", "Bcar");
        assertEquals(0, gasStations.size());

        gasStations = gasStationService.getGasStationsWithCoordinates(lat, lon, "Methane", "Bcar");
        assertEquals(0, gasStations.size());
    }

    @Test
    public void testGetGasStationsWithoutCoordinates() throws InvalidGasTypeException, GPSDataException {
        List<GasStationDto> gasStations;
        double lat = 45, lon = 7;

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Diesel", null);
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Super", null);
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("SuperPlus", null);
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Gas", null);
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Methane", null);
        assertEquals(3, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Diesel", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Super", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("SuperPlus", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Gas", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = gasStationService.getGasStationsWithoutCoordinates("Methane", "Bcar");
        assertEquals(1, gasStations.size());
    }

    @Test
    public void testSetReport() throws InvalidGasStationException, PriceException, InvalidUserException {
        gasStationService.setReport(aId, 2, 2, 2, 2, 2, userId);

        GasStationDto g = gasStationService.getGasStationById(aId);

        assertEquals(2, g.getDieselPrice(), 0.001);
        assertEquals(2, g.getSuperPrice(), 0.001);
        assertEquals(2, g.getSuperPlusPrice(), 0.001);
        assertEquals(2, g.getGasPrice(), 0.001);
        assertEquals(2, g.getMethanePrice(), 0.001);
        assertEquals(userId, g.getReportUser());
    }

    @Test
    public void testGetGasStationByCarSharing(){
        List<GasStationDto> gasStations = gasStationService.getGasStationByCarSharing("Acar");

        assertEquals(1, gasStations.size());
    }
}
