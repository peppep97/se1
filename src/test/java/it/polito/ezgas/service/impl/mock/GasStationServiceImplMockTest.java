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
    IdPw credential = new IdPw("bob@ezgas.com", "Bob");
    LoginDto login = new LoginDto(2, "Bob", "init_token", "bob@ezgas.com", 0);

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
            "25/mag/2020 - 01:01:01",
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
            "25/mag/2020 - 01:01:02",
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
            "25/mag/2020 - 01:01:03",
            60.0);

    List<GasStation> list = new ArrayList<>();


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
        mockedRepo = Mockito.mock(GasStationRepository.class);
        Mockito.when(mockedRepo.findAll()).thenReturn(list);
        Mockito.when(mockedRepo.findByGasStationId(Mockito.anyInt())).thenReturn(a);
        Mockito.doAnswer(i -> {a.setGasPrice(0.4); return c;}).when(mockedRepo).save(Mockito.any(GasStation.class));
        Mockito.when(mockedRepo.exists(Mockito.anyInt())).thenReturn(true);
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
    public void getGasStationsByGasolineType() {
    }

    @Test
    public void getGasStationsByProximity() throws GPSDataException {
        List<GasStationDto> l = test.getGasStationsByProximity(45.0, 7.0);
        assertEquals("Agippo", l.get(0).getGasStationName());
    }

    @Test
    public void getGasStationsWithCoordinates() {
    }

    @Test
    public void getGasStationsWithoutCoordinates() {
    }

    @Test
    public void setReport() throws PriceException, InvalidUserException, InvalidGasStationException {
        test.setReport(0, 1.1, 1.2, 1.3, 0.4, 0.5, 0);
        assertEquals(0.4, test.getGasStationById(0).getGasPrice(), 0.01);
    }

    @Test
    public void getGasStationByCarSharing() {
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
