package it.polito.ezgas.service.impl.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceImpl;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImplMockTest {
	
	UserRepository mockedRepo;
	UserServiceImpl test;
	
	User a = new User("Alice", "Alice", "alice@ezgas.com", 0);
	User b = new User("Bob", "Bob", "bob@ezgas.com", 0);
	User c = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);
	List<User> list = new ArrayList<User>();
	IdPw credential = new IdPw("bob@ezgas.com", "Bob");
	LoginDto login = new LoginDto(2, "Bob", "init_token", "bob@ezgas.com", 0);
	
	
	@Before
	public void setUp() {

		list.add(a);
		list.add(b);
		list.add(c);
		mockedRepo = Mockito.mock(UserRepository.class);
		Mockito.when(mockedRepo.findAll()).thenReturn(list);
		Mockito.when(mockedRepo.findByUserId(Mockito.anyInt())).thenReturn(a);
		Mockito.when(mockedRepo.findByEmail("bob@ezgas.com")).thenReturn(b);
		Mockito.when(mockedRepo.save(Mockito.any(User.class))).thenReturn(c);
		Mockito.when(mockedRepo.exists(Mockito.anyInt())).thenReturn(true);
		test = new UserServiceImpl(mockedRepo);
		
	}
	
	@Test
	public void testGetUserById() throws InvalidUserException {	
		
		User u = UserConverter.toUser(test.getUserById(1));
		assertTrue(compareUsers(u, a));
		
	}
	
	@Test
	public void testSaveUser() throws InvalidUserException {
		
		User u = UserConverter.toUser(test.saveUser(UserConverter.toUserDto(c)));
		assertTrue(compareUsers(u, c));
		
	}

	@Test
	public void testGetAllUsers() throws InvalidUserException {
		
		List<User> l = test.getAllUsers().stream().map(UserConverter::toUser).collect(Collectors.toList());
		assertTrue(compareUsers(l.get(0), a));
		assertTrue(compareUsers(l.get(1), b));
		assertTrue(compareUsers(l.get(2), c));
		
	}

	@Test	
	public void testDeleteUsers() throws InvalidUserException {
		
		Boolean c = test.deleteUser(1);
		assertFalse(c);	// Because the mockedRepo.exists() returns true and in the UserServiceImpl if it is true, the delete method returns false
		
	}

	@Test	
	public void testLogin() throws InvalidUserException, InvalidLoginDataException {
		
		LoginDto log = test.login(credential);
		assertTrue(compareLogins(log, login));
		
	}

	@Test	
	public void testIncreaseUserReputation() throws InvalidUserException {
		
		a.setReputation(0);
		Integer i = test.increaseUserReputation(1);
		assertTrue(i.equals(Integer.valueOf(1)));
		
	}

	@Test	
	public void testDecreaseUserReputation() throws InvalidUserException {
		
		a.setReputation(0);
		Integer i = test.decreaseUserReputation(1);
		assertTrue(i.equals(Integer.valueOf(-1)));
		
	}
	
	
	private boolean compareUsers(User a, User b) {
		
		if(a.getUserId() == b.getUserId() && a.getUserName().compareTo(b.getUserName()) == 0 && 
				a.getEmail().compareTo(b.getEmail()) == 0 && a.getPassword().compareTo(b.getPassword()) == 0 &&
				a.getReputation() == b.getReputation())
			return true;
		
		else
			return false;
	}
	
private boolean compareLogins(LoginDto a, LoginDto b) {
		
		if(a.getUserName().compareTo(b.getUserName()) == 0 && 
				a.getEmail().compareTo(b.getEmail()) == 0 && a.getToken().compareTo(b.getToken()) == 0 &&
				a.getReputation() == b.getReputation())
			return true;
		
		else
			return false;
	}
	

}
