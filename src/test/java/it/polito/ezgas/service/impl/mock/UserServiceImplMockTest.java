package it.polito.ezgas.service.impl.mock;


import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplMockTest {
	
	@Autowired
	UserRepository mockedRepo;
	
	UserServiceImpl test;
	
	User a = new User("Alice", "Alice", "alice@ezgas.com", 0);
	User b = new User("Bob", "Bob", "bob@ezgas.com", 0);
	User c = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);
	List<User> list = new ArrayList<>();
	IdPw credential = new IdPw("bob@ezgas.com", "Bob");
	LoginDto login = new LoginDto(2, "Bob", "init_token", "bob@ezgas.com", 0);
	
	
	@Before
	public void setUp() {

		b.setUserId(2);

		list.add(a);
		list.add(b);
		list.add(c);
		mockedRepo = Mockito.mock(UserRepository.class);
		Mockito.when(mockedRepo.findAll()).thenReturn(list);
		Mockito.when(mockedRepo.findByUserId(Mockito.anyInt())).thenReturn(a);
		Mockito.when(mockedRepo.findByEmail(Mockito.anyString())).thenReturn(b);
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
		assertNotNull(u);
		assertTrue(compareUsers(u, c));
		
	}

	@Test
	public void testGetAllUsers() throws InvalidUserException {
		
		List<User> l = test.getAllUsers().stream().map(UserConverter::toUser).collect(Collectors.toList());
		assertNotNull(l);
		assertTrue(compareUsers(l.get(0), a));
		assertTrue(compareUsers(l.get(1), b));
		assertTrue(compareUsers(l.get(2), c));
		
	}

	@Test	
	public void testDeleteUsers() throws InvalidUserException {
		
		Boolean c = test.deleteUser(1);
		assertNotNull(c);
		assertFalse(c);	// Because the mockedRepo.exists() returns true and in the UserServiceImpl if it is true, the delete method returns false
		
	}

	@Test	
	public void testLogin() throws InvalidLoginDataException {
		
		LoginDto log = test.login(credential);
		assertNotNull(log);
		assertTrue(compareLogins(log, login));
		
	}

	@Test	
	public void testIncreaseUserReputation() throws InvalidUserException {
		
		a.setReputation(0);
		Integer i = test.increaseUserReputation(1);
		assertNotNull(i);
		assertEquals(Integer.valueOf(i), Integer.valueOf(1));
		
	}

	@Test	
	public void testDecreaseUserReputation() throws InvalidUserException {
		
		a.setReputation(0);
		Integer i = test.decreaseUserReputation(1);
		assertNotNull(i);
		assertEquals(Integer.valueOf(i), Integer.valueOf(-1));
		
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
		
		if(a.getUserId().compareTo( b.getUserId()) == 0 && a.getUserName().compareTo(b.getUserName()) == 0 && 
				a.getEmail().compareTo(b.getEmail()) == 0 && a.getToken().compareTo(b.getToken()) == 0 &&
				a.getReputation() == b.getReputation())
			return true;
		
		else
			return false;
	}

}
