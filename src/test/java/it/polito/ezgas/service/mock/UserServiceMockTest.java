package it.polito.ezgas.service.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class UserServiceMockTest {
	
	UserRepository mockedRepo;
	
	@Before
	public void setUp() {
		mockedRepo = Mockito.mock(UserRepository.class);
		Mockito.when(mockedRepo.findAll()).thenReturn(new ArrayList<User>());
	}

}
