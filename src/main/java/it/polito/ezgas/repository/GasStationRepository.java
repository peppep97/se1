package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.polito.ezgas.entity.GasStation; 

public interface GasStationRepository extends CrudRepository<GasStation, Integer> {
	
	GasStation save(GasStation gasStation);
	void delete(GasStation gasStation);
	void deleteAll();
	GasStation findByGasStationId(Integer id);
	List<GasStation> findAll();
	List<GasStation> findByHasDieselTrue();
	List<GasStation> findByHasSuperTrue();
	List<GasStation> findByHasSuperPlusTrue();
	List<GasStation> findByHasGasTrue();
	List<GasStation> findByHasMethaneTrue();
	List<GasStation> findByHasDieselTrueAndCarSharing(String carSharing);
	List<GasStation> findByHasSuperTrueAndCarSharing(String carSharing);
	List<GasStation> findByHasSuperPlusTrueAndCarSharing(String carSharing);
	List<GasStation> findByHasGasTrueAndCarSharing(String carSharing);
	List<GasStation> findByHasMethaneTrueAndCarSharing(String carSharing);
	List<GasStation> findByCarSharing(String carSharing);
	
}
