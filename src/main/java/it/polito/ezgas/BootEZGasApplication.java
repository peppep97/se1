package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;



@SpringBootApplication
public class BootEZGasApplication {
	
	@Autowired
	GasStationRepository repo;
	@Autowired
	UserRepository urepo;

	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		//if admin does not exist then create and save it
		if(!urepo.existsByAdminTrue()) {
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			urepo.save(user);
		}
		
		
		/*GasStation gs1 = new GasStation();
		gs1.setCarSharing("enjoy");
		gs1.setHasDiesel(true);
		repo.save(gs1);
		GasStation gs2 = new GasStation();
		gs2.setCarSharing("enjoy");
		gs2.setHasSuper(true);
		repo.save(gs2);
		GasStation gs3 = new GasStation();
		gs3.setCarSharing("car2go");
		gs3.setHasSuperPlus(true);
		repo.save(gs3);
		GasStation gs4 = new GasStation();
		gs4.setCarSharing("enjoy");
		gs4.setHasGas(true);
		repo.save(gs4);
		GasStation gs5 = new GasStation();
		gs5.setCarSharing("car2go");
		gs5.setHasMethane(true);
		repo.save(gs5);*/
		
		/*List<GasStation> gs1 = repo.findByHasDieselTrue();
		gs1.forEach(g -> g.setGasPrice(1.200));
		repo.save(gs1);
		List<GasStation> gs2 = repo.findByHasSuperTrue();
		gs2.forEach(g -> g.setGasPrice(1.200));
		repo.save(gs2);
		List<GasStation> gs3 = repo.findByHasSuperPlusTrue();
		gs3.forEach(g -> g.setGasPrice(1.200));
		repo.save(gs3);
		List<GasStation> gs4 = repo.findByHasGasTrue();
		gs4.forEach(g -> g.setGasPrice(1.200));
		repo.save(gs4);
		List<GasStation> gs5 = repo.findByHasMethaneTrue();
		gs5.forEach(g -> g.setGasPrice(1.200));
		repo.save(gs5);*/
		
		/*List<GasStation> gs1 = repo.findByHasDieselTrueAndCarSharing("enjoy");
		gs1.forEach(g -> g.setSuperPrice(1.750));
		repo.save(gs1);
		List<GasStation> gs2 = repo.findByHasSuperTrueAndCarSharing("enjoy");
		gs2.forEach(g -> g.setSuperPrice(1.750));
		repo.save(gs2);
		List<GasStation> gs3 = repo.findByHasSuperPlusTrueAndCarSharing("car2go");
		gs3.forEach(g -> g.setSuperPrice(1.750));
		repo.save(gs3);
		List<GasStation> gs4 = repo.findByHasGasTrueAndCarSharing("enjoy");
		gs4.forEach(g -> g.setSuperPrice(1.750));
		repo.save(gs4);
		List<GasStation> gs5 = repo.findByHasMethaneTrueAndCarSharing("car2go");
		gs5.forEach(g -> g.setSuperPrice(1.750));
		repo.save(gs5);*/
		
		/*List<GasStation> gs1 = repo.findByCarSharing("enjoy");
		gs1.forEach(g -> g.setSuperPlusPrice(1.8));
		repo.save(gs1);
		List<GasStation> gs2 = repo.findByCarSharing("car2go");
		gs2.forEach(g -> g.setSuperPlusPrice(2.1));
		repo.save(gs2);*/
		
	}

}
