package it.polito.ezgas.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImplTest {
	
	@Autowired
	UserRepository repo;
	
	UserServiceImpl test;
	
	User a = new User("Alice", "Alice", "alice@ezgas.com", 0);
	User b = new User("Bob", "Bob", "bob@ezgas.com", 0);
	User c = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);
	List<User> list = new ArrayList<User>();
	IdPw credential = new IdPw(b.getEmail(), b.getPassword());
	LoginDto login = new LoginDto(b.getUserId(), b.getUserName(), "init_token", b.getEmail(), b.getReputation());
	
	@Before
	public void setUp() {
		
		list.add(a);
		list.add(b);
		list.add(c);
		repo.save(a);
		repo.save(b);
		repo.save(c);
		test = new UserServiceImpl(repo);
	}
	
	@After
	public void setDown() {
		
		list =  new ArrayList<User>();
		repo.delete(a);
		repo.delete(b);
		repo.delete(c);
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
		assertTrue(c);	// Because the mockedRepo.exists() returns true and in the UserServiceImpl if it is true, the delete method returns false
		
	}

	@Test	
	public void testLogin() throws  InvalidLoginDataException {
		
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
