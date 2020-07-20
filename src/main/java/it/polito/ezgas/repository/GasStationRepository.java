package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.polito.ezgas.entity.GasStation; 

public interface GasStationRepository extends CrudRepository<GasStation, Integer> {
	
	// Save a gas station
	@SuppressWarnings("unchecked")
	GasStation save(GasStation gasStation);
	// Delete a gas station 
	void delete(GasStation gasStation);
	// Delete all gas stations
	void deleteAll();
	// Search gas station by id
	GasStation findByGasStationId(Integer id);
	// Retrieve all gas stations
	List<GasStation> findAll();
	// Retrieve all gas stations with diesel 
	List<GasStation> findByHasDieselTrue();
	// Retrieve all gas stations with super
	List<GasStation> findByHasSuperTrue();
	// Retrieve all gas stations with super plus
	List<GasStation> findByHasSuperPlusTrue();
	// Retrieve all gas stations with gas
	List<GasStation> findByHasGasTrue();
	// Retrieve all gas stations with methane
	List<GasStation> findByHasMethaneTrue();
	// Retrieve all gas stations with premium diesel
	List<GasStation> findByHasPremiumDieselTrue();
	// Retrieve all gas stations with diesel and with the specified car sharing 
	List<GasStation> findByHasDieselTrueAndCarSharing(String carSharing);
	// Retrieve all gas stations with super and with the specified car sharing
	List<GasStation> findByHasSuperTrueAndCarSharing(String carSharing);
	// Retrieve all gas stations with super plus and with the specified car sharing
	List<GasStation> findByHasSuperPlusTrueAndCarSharing(String carSharing);
	// Retrieve all gas stations with gas and with the specified car sharing
	List<GasStation> findByHasGasTrueAndCarSharing(String carSharing);
	// Retrieve all gas stations with methane and with the specified car sharing
	List<GasStation> findByHasMethaneTrueAndCarSharing(String carSharing);
	// Retrieve all gas stations with premium diesel and with the specified car sharing
	List<GasStation> findByHasPremiumDieselTrueAndCarSharing(String carSharing);
	// Retrieve all gas stations with the specified car sharing 
	List<GasStation> findByCarSharing(String carSharing);
	
}
