package it.polito.ezgas.repository;

import org.springframework.data.repository.CrudRepository;
import it.polito.ezgas.entity.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	// Save a user 
	@SuppressWarnings("unchecked")
	User save(User user);
	// Delete a User
	void delete(User user);
	// Delete all users
	void deleteAll();
	// Return true if an admin exists 
	Boolean existsByAdminTrue();
	// Retrieve all users
	List<User> findAll();
	// Search user by id
	User findByUserId(Integer id);
	// Search user by email
	User findByEmail(String userEmail);
	
}
