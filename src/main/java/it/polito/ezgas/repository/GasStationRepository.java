package it.polito.ezgas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.polito.ezgas.entity.GasStation;

public interface GasStationRepository extends JpaRepository<GasStation, Integer>{

}
