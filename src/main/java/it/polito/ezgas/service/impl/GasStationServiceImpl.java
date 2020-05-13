package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.GasStationService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceImpl implements GasStationService {
	@Autowired
	GasStationRepository repo;
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		//id error handling
		if( gasStationId==null || gasStationId<0 ) {
			throw new InvalidGasStationException("Invalid Gas Station ID!");
		}
		//retrieve gas station
		GasStation gasStation = repo.findByGasStationId(gasStationId);
		if( gasStation == null ) {
			return null;
		} 	
		return GasStationConverter.toGasStationDto(gasStation);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		//price validation
		if(gasStationDto.getDieselPrice()<0 || gasStationDto.getSuperPrice()<0 ||
		  gasStationDto.getSuperPlusPrice()<0 || gasStationDto.getMethanePrice()<0 ) {
			throw new PriceException("Invalid price values!");
		}
		//GPS error handling
		if(gasStationDto.getLat()>90 || gasStationDto.getLat()<-90) {
			throw new GPSDataException("Latitude out of boundaries!");
		}
		if(gasStationDto.getLon()>180 || gasStationDto.getLon()<-180) {
			throw new GPSDataException("Longitude out of boundaries!");
		}
		//inserting new gas station or updating an existing one
		GasStation gasStation = repo.save(GasStationConverter.toGasStation(gasStationDto));
		return GasStationConverter.toGasStationDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		//retrieving all gas stations
		List<GasStation> gasStations = repo.findAll();
		if( gasStations == null ) {
			return null;
		}
		return gasStations.stream()
				.map( g -> GasStationConverter.toGasStationDto(g))	//converting each GasStation to GasStationDto
				.collect(Collectors.toList());
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if(gasStationId==null || gasStationId<0)
			throw new InvalidGasStationException("ERROR:GasStation ID ISN'T VALID!");
		if(!repo.exists(gasStationId))
			return false;
		repo.delete(gasStationId);
		if(repo.exists(gasStationId))
			return false;
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		String gasoline = gasolinetype.toLowerCase();
		//retrieving all gas stations
		List<GasStation> gasStations = repo.findAll();
		if( gasStations == null ) {
			return null;
		}
		//getGasStationsByGasolineType(gasolinetype);
		Stream<GasStation> filteredGasStations = filterGasStationByGasolineType (gasolinetype,gasStations);
		return filteredGasStations
				.map( g -> GasStationConverter.toGasStationDto(g))	//converting each GasStation to GasStationDto
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		//GPS error handling
		if( lat>90 || lat<-90 ) {
			throw new GPSDataException("Latitude out of boundaries!");
		}
		if( lon>180 || lon<-180 ) {
			throw new GPSDataException("Longitude out of boundaries!");
		}
		
		//retrieving all gas stations
		List<GasStation> gasStations = repo.findAll();
		if( gasStations == null ) {
			return new ArrayList<GasStationDto>();
		}
		
		// some info about lat, lon, kilometers and decimal degrees
		// 1km (lat) = 0.00904371732 dd
		// 1km (lon) = 0.00898311175 / cos(lat*pi/180) dd

		double teta = 0.00898311175 / Math.cos(lat*Math.PI/180);

		//filter gas station for the coordinates inside the limits of 1km
		Stream<GasStation> filteredGasStations = gasStations.stream()
				.filter(g -> Math.abs(g.getLat() - lat) < 0.00904371732 &&
							 Math.abs(g.getLon() - lon) < teta);
		if( filteredGasStations == null ) {
			return new ArrayList<GasStationDto>();
		}
		return filteredGasStations
				.map( g -> GasStationConverter.toGasStationDto(g))	//converting each GasStation to GasStationDto
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		//GPS error handling
		if( lat>90 || lat<-90 ) {
			throw new GPSDataException("Latitude out of boundaries!");
		}
		if( lon>180 || lon<-180 ) {
			throw new GPSDataException("Longitude out of boundaries!");
		}
		
		//retrieving all gas stations
		List<GasStation> gasStations = repo.findAll();
		if( gasStations == null ) {
			return new ArrayList<GasStationDto>();
		}

		// some info about lat, lon, kilometers and decimal degrees
		// 1km (lat) = 0.00904371732 dd
		// 1km (lon) = 0.00898311175 / cos(lat*pi/180) dd

		double teta = 0.00898311175 / Math.cos(lat*Math.PI/180);

		//filter gas station for the coordinates inside the limits of 1km
		Stream<GasStation> filteredGasStations = gasStations.stream()
				.filter(g -> Math.abs(g.getLat() - lat) < 0.00904371732 &&
						Math.abs(g.getLon() - lon) < teta);
		if( filteredGasStations == null ) {
			return new ArrayList<GasStationDto>();
		}
		//FilterGasstation by gasoline types
		filteredGasStations = filterGasStationByGasolineType (gasolinetype,filteredGasStations.collect(Collectors.toList()));
		
		if( carsharing != null ) {
			//filter by carsharing
			filteredGasStations = filteredGasStations.filter( g -> g.getCarSharing().compareTo(carsharing) == 0);
			if( filteredGasStations == null ) {
				return new ArrayList<GasStationDto>();
			}
		}
		
		return filteredGasStations
				.map( g -> GasStationConverter.toGasStationDto(g))	//converting each GasStation to GasStationDto
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		//retrieving all gas stations
		List<GasStation> gasStations = repo.findAll();
		if( gasStations == null ) {
			return new ArrayList<GasStationDto>();
		}
		
		Stream<GasStation> filteredGasStations = gasStations.stream();
		if( gasolinetype != null ) {
			//filter by gasoline
			if( gasolinetype.compareTo("diesel") == 0 ) {
				filteredGasStations = filteredGasStations.filter(g -> g.getHasDiesel())
												.sorted(Comparator.comparingDouble(GasStation::getDieselPrice));
			} else if( gasolinetype.compareTo("super") == 0 ) {
				filteredGasStations = filteredGasStations.filter(g -> g.getHasSuper())
												.sorted(Comparator.comparingDouble(GasStation::getSuperPrice));
			} else if( gasolinetype.compareTo("superplus") == 0 ) {
				filteredGasStations = filteredGasStations.filter(g -> g.getHasSuperPlus())
												.sorted(Comparator.comparingDouble(GasStation::getSuperPlusPrice));;
			} else if( gasolinetype.compareTo("methane") == 0 ) {
				filteredGasStations = filteredGasStations.filter(g -> g.getHasMethane())
												.sorted(Comparator.comparingDouble(GasStation::getMethanePrice));;
			} else {
				//gasoline type error handling
				throw new InvalidGasTypeException(gasolinetype + "is an invalid gas type!");
			}
			if( filteredGasStations == null ) {
				return new ArrayList<GasStationDto>();
			}
		}
		
		if( carsharing != null ) {
			//filter by carsharing
			filteredGasStations = filteredGasStations.filter( g -> g.getCarSharing().compareTo(carsharing) == 0);
			if( filteredGasStations == null ) {
				return new ArrayList<GasStationDto>();
			}
		}
		
		return filteredGasStations
				.map( g -> GasStationConverter.toGasStationDto(g))	//converting each GasStation to GasStationDto
				.collect(Collectors.toList());
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		//gas station id error handling
		if( gasStationId<0 ) {
			throw new InvalidGasStationException("Invalid Gas Station ID!");
		}
		//price error handling
		if(dieselPrice <0 || superPrice<0 || superPlusPrice<0 || methanePrice<0 ) {
			throw new PriceException("Invalid price values!");
		}
		//user id error handling
		if( userId<0 ) {
			throw new InvalidUserException("Invalid User ID!");
		}
		
		GasStationDto gasStationDto = getGasStationById(gasStationId);
		if( gasStationDto == null ) {
			return;
		}
		gasStationDto.setDieselPrice(dieselPrice);
		gasStationDto.setSuperPrice(superPrice);
		gasStationDto.setSuperPlusPrice(superPlusPrice);
		gasStationDto.setGasPrice(gasPrice);
		gasStationDto.setMethanePrice(methanePrice);
		gasStationDto.setReportUser(userId);
		// TODO which timestamp and what is dependability and we have to set UserDto?
		//gasStation.setReportTimestamp(reportTimestamp);
		//gasStation.setReportDependability(reportDependability);
		//UserDto user = getUserById(userId)
		//gasStation.setUserDto(user)
		
		//updating an existing one
		repo.save(GasStationConverter.toGasStation(gasStationDto));
		
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		//retrieving all gas stations
		List<GasStation> gasStations = repo.findAll();
		if( gasStations == null ) {
			return new ArrayList<GasStationDto>();
		}
		//filtering gas stations by car sharing
		Stream<GasStation> filteredGasStations = 
				gasStations.stream()
				.filter( g -> g.getCarSharing().compareTo(carSharing) == 0);
		if( filteredGasStations == null ) {
			return new ArrayList<GasStationDto>();
		}
		
		return filteredGasStations
				.map( g -> GasStationConverter.toGasStationDto(g))	//converting each GasStation to GasStationDto
				.collect(Collectors.toList());
	}
	
	@Override
	public Stream<GasStation> filterGasStationByGasolineType (String gasolinetype, List<GasStation> gasStations) throws InvalidGasTypeException{
		Stream<GasStation> filteredGasStations = null;
		if( gasolinetype != null ) {
			//filter by gasoline
			if( gasolinetype.compareTo("diesel") == 0 ) {
				filteredGasStations = gasStations.stream().filter(g -> g.getHasDiesel())
												.sorted(Comparator.comparingDouble(GasStation::getDieselPrice));
			} else if( gasolinetype.compareTo("super") == 0 ) {
				filteredGasStations = gasStations.stream().filter(g -> g.getHasSuper())
												.sorted(Comparator.comparingDouble(GasStation::getSuperPrice));
			} else if( gasolinetype.compareTo("superplus") == 0 ) {
				filteredGasStations = gasStations.stream().filter(g -> g.getHasSuperPlus())
												.sorted(Comparator.comparingDouble(GasStation::getSuperPlusPrice));;
			} else if( gasolinetype.compareTo("methane") == 0 ) {
				filteredGasStations = gasStations.stream().filter(g -> g.getHasMethane())
												.sorted(Comparator.comparingDouble(GasStation::getMethanePrice));;
			} else {
				//gasoline type error handling
				throw new InvalidGasTypeException(gasolinetype + "is an invalid gas type!");
			}
		}
		return filteredGasStations;
	}
	
	
	

}
