package it.polito.ezgas.repository;

import org.springframework.data.repository.CrudRepository;
import it.polito.ezgas.entity.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User save(User user);
	void delete(User user);
	void deleteAll();
	Boolean existsByAdminTrue();
	List<User> findAll();
	User findByUserId(Integer id); 
	User findByUserName(String userName);
	
}
