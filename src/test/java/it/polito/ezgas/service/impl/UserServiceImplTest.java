package it.polito.ezgas.service.impl;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(UserServiceImpl.class)

public class UserServiceImplTest {
	
	@Autowired
	UserRepository repo;

	@Autowired
	UserServiceImpl userService;

	Integer bId;
	
	User a = new User("Alice", "Alice", "alice@ezgas.com", 0);
	User b = new User("Bob", "Bob", "bob@ezgas.com", 0);
	User c = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);
	IdPw credential = new IdPw(b.getEmail(), b.getPassword());
	LoginDto login = new LoginDto(null, b.getUserName(), "init_token", b.getEmail(), b.getReputation());
	boolean found;
	
	@Before
	public void setUp() {
		
		repo.save(a);
		bId = repo.save(b).getUserId();
		repo.save(c);

		login.setUserId(bId);
		userService = new UserServiceImpl(repo);
		found = false;
	}
	
	@Test
	public void testGetUserById() throws InvalidUserException {	
		
		User u = UserConverter.toUser(userService.getUserById(a.getUserId()));
		assertNotNull(u);
		assertTrue(compareUsers(u, a));
	}
	
	@Test
	public void testSaveUser() throws InvalidUserException {
		
		User u = UserConverter.toUser(userService.saveUser(UserConverter.toUserDto(c)));
		assertNotNull(u);
		assertTrue(compareUsers(u, c));
		
	}

	@Test
	public void testGetAllUsers() throws InvalidUserException {
		
		List<User> l = userService.getAllUsers().stream().map(UserConverter::toUser).collect(Collectors.toList());
		assertNotNull(l);
		assertTrue(compareList(l, a));
		assertTrue(compareList(l, b));
		assertTrue(compareList(l, c));
		
	}

	@Test	
	public void testDeleteUsers() throws InvalidUserException {
		
		Boolean c = userService.deleteUser(a.getUserId());
		assertNotNull(c);
		assertTrue(c);
		
	}

	@Test	
	public void testLogin() throws  InvalidLoginDataException {
		
		LoginDto log = userService.login(credential);
		assertNotNull(log);
		assertTrue(compareLogins(log, login));
		
	}

	@Test	
	public void testIncreaseUserReputation() throws InvalidUserException {
		
		a.setReputation(0);
		
		Integer i = userService.increaseUserReputation(a.getUserId());
		assertNotNull(i);
		assertEquals(Integer.valueOf(i), Integer.valueOf(1));
		
	}

	@Test	
	public void testDecreaseUserReputation() throws InvalidUserException {
		
		a.setReputation(0);
		
		Integer i = userService.decreaseUserReputation(a.getUserId());
		assertNotNull(i);
		assertEquals(Integer.valueOf(i), Integer.valueOf(-1));
		
	}
	
	
	private boolean compareUsers(User a, User b) {
		
		if(a.getUserId().compareTo( b.getUserId()) == 0 && a.getUserName().compareTo(b.getUserName()) == 0 && 
				a.getEmail().compareTo(b.getEmail()) == 0 && a.getPassword().compareTo(b.getPassword()) == 0 &&
				a.getReputation().compareTo( b.getReputation()) == 0)
			return true;
		
		else
			return false;
	}
	
	private boolean compareLogins(LoginDto a, LoginDto b) {
		
		if(a.getUserId().compareTo( b.getUserId()) == 0 && a.getUserName().compareTo(b.getUserName()) == 0 && 
				a.getEmail().compareTo(b.getEmail()) == 0 && a.getToken().compareTo(b.getToken()) == 0 &&
				a.getReputation().compareTo(b.getReputation()) == 0)
			return true;
		
		else
			return false;
	}
	
	private boolean compareList(List<User> l, User u) {
		
		l.forEach(a ->{ if(compareUsers(a, u))  found = true; });
		
		return found;
		
	}
	

}
