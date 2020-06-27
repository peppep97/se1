package it.polito.ezgas.service.impl.mock;

import exception.*;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceImpl;
import it.polito.ezgas.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GasStationServiceImplMockTest {

    GasStationRepository mockedRepo;
    UserRepository mockedUserRepo;

    GasStationServiceImpl test;

    User user = new User("Alice", "Alice", "alice@ezgas.com", 0);
    User user1 = new User("Bob", "Bob", "bob@ezgas.com", 0);
    User user2 = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);
    List<User> userList = new ArrayList<>();

    GasStation a = new GasStation("Agippo",
            "Via Agrippa",
            true,
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
            1.3,
            0,
            "05-25-2020",
            40.0);
    GasStation b = new GasStation("BP",
            "Via Brenta",
            true,
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
            1.4,
            1,
            "05-25-2020",
            50.0);
    GasStation c = new GasStation("Agippo",
            "Via Cesare",
            true,
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
            1.5,
            2,
            "05-25-2020",
            60.0);

    List<GasStation> list = new ArrayList<>();
    List<GasStation> list1 = new ArrayList<>();

    @Before
    public void setUp() {

        a.setUser(user);
        b.setUser(user1);
        c.setUser(user2);

        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        mockedUserRepo = Mockito.mock(UserRepository.class);
        Mockito.when(mockedUserRepo.findAll()).thenReturn(userList);
        Mockito.when(mockedUserRepo.findByUserId(0)).thenReturn(user);
        Mockito.when(mockedUserRepo.findByUserId(1)).thenReturn(user1);
        Mockito.when(mockedUserRepo.findByUserId(2)).thenReturn(user2);
        Mockito.when(mockedUserRepo.findByEmail(Mockito.anyString())).thenReturn(user1);
        Mockito.when(mockedUserRepo.save(Mockito.any(User.class))).thenReturn(user2);
        Mockito.when(mockedUserRepo.exists(Mockito.anyInt())).thenReturn(true);

        list.add(a);
        list.add(b);
        list.add(c);

        list1.add(a);

        mockedRepo = Mockito.mock(GasStationRepository.class);
        Mockito.when(mockedRepo.findAll()).thenReturn(list);
        Mockito.when(mockedRepo.findByGasStationId(Mockito.anyInt())).thenReturn(a);
        Mockito.doAnswer(i -> {a.setGasPrice(0.4); return c;}).when(mockedRepo).save(Mockito.any(GasStation.class));
        Mockito.when(mockedRepo.exists(Mockito.anyInt())).thenReturn(true);
        Mockito.when(mockedRepo.findByCarSharing(Mockito.anyString())).thenReturn(list);
        Mockito.when(mockedRepo.findByHasDieselTrue()).thenReturn(list);
        Mockito.when(mockedRepo.findByHasSuperTrue()).thenReturn(list);
        Mockito.when(mockedRepo.findByHasSuperPlusTrue()).thenReturn(list);
        Mockito.when(mockedRepo.findByHasGasTrue()).thenReturn(list);
        Mockito.when(mockedRepo.findByHasMethaneTrue()).thenReturn(list);

        Mockito.when(mockedRepo.findByHasDieselTrueAndCarSharing(Mockito.anyString())).thenReturn(list1);
        Mockito.when(mockedRepo.findByHasSuperTrueAndCarSharing(Mockito.anyString())).thenReturn(list1);
        Mockito.when(mockedRepo.findByHasSuperPlusTrueAndCarSharing(Mockito.anyString())).thenReturn(list1);
        Mockito.when(mockedRepo.findByHasGasTrueAndCarSharing(Mockito.anyString())).thenReturn(list1);
        Mockito.when(mockedRepo.findByHasMethaneTrueAndCarSharing(Mockito.anyString())).thenReturn(list1);
        test = new GasStationServiceImpl(mockedRepo, mockedUserRepo);

    }

    @Test
    public void getGasStationById() throws InvalidGasStationException {
        GasStation g = GasStationConverter.toGasStation(test.getGasStationById(1));
        assertTrue(compareGasStations(g, a));
    }

    @Test
    public void saveGasStation() throws PriceException, GPSDataException {
        GasStation g = GasStationConverter.toGasStation(test.saveGasStation(GasStationConverter.toGasStationDto(c)));
        assertTrue(compareGasStations(g, c));
    }

    @Test
    public void getAllGasStations() {
        List<GasStation> l = test.getAllGasStations().stream().map(GasStationConverter::toGasStation).collect(Collectors.toList());
        assertTrue(compareGasStations(l.get(0), a));
        assertTrue(compareGasStations(l.get(1), b));
        assertTrue(compareGasStations(l.get(2), c));
    }

    @Test
    public void deleteGasStation() throws InvalidGasStationException {
        Boolean c = test.deleteGasStation(1);
        assertFalse(c);
    }

    @Test
    public void getGasStationsByGasolineType() throws InvalidGasTypeException{

        List<GasStationDto> gasStations;

        gasStations = test.getGasStationsByGasolineType("Diesel");
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsByGasolineType("Super");
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsByGasolineType("SuperPlus");
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsByGasolineType("Gas");
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsByGasolineType("Methane");
        assertEquals(3, gasStations.size());
    }

    @Test
    public void getGasStationsByProximity() throws GPSDataException {
        List<GasStationDto> l = test.getGasStationsByProximity(45.0, 7.0);
        assertEquals("Agippo", l.get(0).getGasStationName());
    }

    @Test
    public void getGasStationsWithCoordinates() throws InvalidGasTypeException, GPSDataException{

        List<GasStationDto> gasStations;
        double lat = 45, lon = 7;

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Diesel", null);
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Super", null);
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "SuperPlus", null);
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Gas", null);
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Methane", null);
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Diesel", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Super", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "SuperPlus", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Gas", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithCoordinates(lat, lon, 5, "Methane", "Bcar");
        assertEquals(1, gasStations.size());
    }

    @Test
    public void getGasStationsWithoutCoordinates() throws InvalidGasTypeException{

        List<GasStationDto> gasStations;

        gasStations = test.getGasStationsWithoutCoordinates("Diesel", null);
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Super", null);
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("SuperPlus", null);
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Gas", null);
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Methane", null);
        assertEquals(3, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Diesel", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Super", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("SuperPlus", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Gas", "Bcar");
        assertEquals(1, gasStations.size());

        gasStations = test.getGasStationsWithoutCoordinates("Methane", "Bcar");
        assertEquals(1, gasStations.size());
    }

    @Test
    public void setReport() throws PriceException, InvalidUserException, InvalidGasStationException {
        test.setReport(0, 1.1, 1.2, 1.3, 0.4, 0.5, 1.5, 0);
        assertEquals(0.4, test.getGasStationById(0).getGasPrice(), 0.01);
    }

    @Test
    public void getGasStationByCarSharing() {
        List<GasStationDto> l = test.getGasStationByCarSharing("Acar");

        assertEquals("Acar", l.get(0).getCarSharing());
    }

    private boolean compareGasStations(GasStation a, GasStation b) {

        if(a.getGasStationId() == b.getGasStationId() && a.getGasStationName().compareTo(b.getGasStationName()) == 0 &&
                a.getGasStationAddress().compareTo(b.getGasStationAddress()) == 0 && a.getCarSharing().compareTo(b.getCarSharing()) == 0 &&
                a.getLon() == b.getLon())
            return true;

        else
            return false;
    }

}
