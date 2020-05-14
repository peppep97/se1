package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.repository.GasStationRepository;



@SpringBootApplication
public class BootEZGasApplication {
	
	@Autowired
	GasStationRepository gsrepository;
	@Autowired
	UserRepository urepository;

	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		//if admin does not exist then create and save it
		if(!urepository.existsByAdminTrue()) {
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			urepository.save(user);
		}
		
	}

}
