package it.polito.ezgas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest

public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testSaveUser() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		userRepository.save(user);
		
		User findUser = userRepository.findByEmail("admin@gmail.com");
		assertNotNull(findUser);

	}
	
	@Test
	public void testDeleteUser() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		userRepository.save(user);
		
		User findUser = userRepository.findByEmail("admin@gmail.com");
		assertNotNull(findUser);
		
		userRepository.delete(user);

		User findUser1 = userRepository.findByEmail("admin@gmail.com");
		assertNull(findUser1);
	}
	
	@Test
	public void testDeleteAll() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		userRepository.save(user);
		
		User user1 = new User("test", "test", "test@gmail.com", 1);
		userRepository.save(user1);
		
		List<User> list = userRepository.findAll();
		assertTrue(list.size() > 0);
		
		userRepository.deleteAll();

		List<User> list1 = userRepository.findAll();
		assertEquals(list1.size(), 0);
	}
	
	@Test
	public void testExistsByAdminTrue() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		user.setAdmin(true);
		userRepository.save(user);
		
		boolean exist = userRepository.existsByAdminTrue();
		assertTrue(exist);
	}
	
	@Test
	public void testFindAll() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		userRepository.save(user);
		
		User user1 = new User("test", "test", "test@gmail.com", 1);
		userRepository.save(user1);
		
		List<User> list = userRepository.findAll();
		assertTrue(list.size() > 0);
	}
	@Test
	public void testFindByUserId() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		user.setUserId(1);
		userRepository.save(user);
		
		User findUser = userRepository.findByUserId(1);
		assertNotNull(findUser);
	}
	@Test
	public void testFindByEmail() {

		User user = new User("admin", "admin", "admin@gmail.com", 1);
		userRepository.save(user);
		
		User findUser = userRepository.findByEmail("admin@gmail.com");
		assertNotNull(findUser);
	}

}
