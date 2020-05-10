package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.swing.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.orm.hibernate5.SpringJtaSessionContext;

import it.polito.ezgas.entity.User;

@SpringBootApplication
public class BootEZGasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		/*
		 
		list all the users stored in the database and, if there is no an admin user create it
		 
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			
		and then save it in the db
	
			
		*/
		User user= new User("admin", "admin", "admin@ezgas.com", 5);
		user.setAdmin(true);

	}

}
