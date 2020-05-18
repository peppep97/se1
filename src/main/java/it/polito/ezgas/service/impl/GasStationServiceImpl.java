package it.polito.ezgas.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;
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
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceImpl implements GasStationService {
	
	// Repository of gas stations
	@Autowired
	GasStationRepository gasStationRepository;
	
	// Repository of users
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		
		// Check if gas station id is not valid
		if(gasStationId == null || gasStationId < 0) {
			throw new InvalidGasStationException("Invalid GasStation Id!");
		}
		
		// Search for gas station into the repository
		GasStation gasStation = gasStationRepository.findByGasStationId(gasStationId);

		return GasStationConverter.toGasStationDto(gasStation);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		
		// Check if coordinates are not valid
		if(gasStationDto.getLat() > 90 || gasStationDto.getLat() < -90) {
			throw new GPSDataException("Latitude out of boundaries!");
		}
		if(gasStationDto.getLon() > 180 || gasStationDto.getLon() < -180) {
			throw new GPSDataException("Longitude out of boundaries!");
		}
		
		// Inserting new gas station or updating an existing one
		GasStation gasStation = gasStationRepository.save(GasStationConverter.toGasStation(gasStationDto));

		return GasStationConverter.toGasStationDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {

		// Retrieve all gas stations from repository
		List<GasStation> listGasStation = gasStationRepository.findAll();
		if(listGasStation == null) {
			return null;
		}
		
		// Converting each GasStation to GasStationDto
		return listGasStation.stream().map(GasStationConverter::toGasStationDto).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {

		// Check if gas station id is not valid
		if(gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("Invalid GasStation Id!");
		GasStation gasStation = gasStationRepository.findByGasStationId(gasStationId);

		if(gasStation == null)
			return false;
		gasStationRepository.delete(gasStation);

		return !gasStationRepository.exists(gasStationId);
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		
		// Retrieve all gas stations
		List<GasStation> gasStations;
		
		// Filter gas stations by type of gasoline
		// and sort them out by ascending order
		switch(gasolinetype){
			case "Diesel":
				gasStations = gasStationRepository.findByHasDieselTrue();
				gasStations.sort(Comparator.comparingDouble(GasStation::getDieselPrice));
			break;
			case "Super":
				gasStations = gasStationRepository.findByHasSuperTrue();
				gasStations.sort(Comparator.comparingDouble(GasStation::getSuperPrice));
			break;
			case "SuperPlus":
				gasStations = gasStationRepository.findByHasSuperPlusTrue();
				gasStations.sort(Comparator.comparingDouble(GasStation::getSuperPlusPrice));
			break;
			case "Gas":
				gasStations = gasStationRepository.findByHasGasTrue();
				gasStations.sort(Comparator.comparingDouble(GasStation::getGasPrice));
			break;
			case "Methane":
				gasStations = gasStationRepository.findByHasMethaneTrue();
				gasStations.sort(Comparator.comparingDouble(GasStation::getMethanePrice));
			break;
			case "null": case "Select gasoline type":
				gasStations = gasStationRepository.findAll();
			break;
			default:
				throw new InvalidGasTypeException(gasolinetype + " ISN'T A VALID GAS TYPE!");				
		}
		
		// Converting each GasStation to GasStationDto
		return gasStations.stream().map(GasStationConverter::toGasStationDto).collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		
		// Check if coordinates are not valid
		if(lat > 90 || lat < -90) {
			throw new GPSDataException("Latitude out of boundaries!");
		}
		if(lon > 180 || lon < -180) {
			throw new GPSDataException("Longitude out of boundaries!");
		}
		
		// Retrieve all gas stations from repository
		List<GasStation> gasStations = gasStationRepository.findAll();
		if(gasStations == null) {
			return new ArrayList<>();
		}

		// some info about lat, lon, kilometers and decimal degrees
		// 1km (lat) = 0.00904371732 dd
		// 1km (lon) = 0.00898311175 / cos(lat*pi/180) dd
		double teta = 0.0089 / Math.cos(lat*Math.PI/180);

		// Filter gas station for the coordinates inside the limits of 1km
		Stream<GasStation> filteredGasStations = gasStations.stream()
				.filter(g -> Math.abs(g.getLat() - lat) < 0.009)
				.filter(g -> Math.abs(g.getLon() - lon) < teta);

		// Converting each GasStation to GasStationDto
		return filteredGasStations.map(GasStationConverter::toGasStationDto).collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		
		// Check if coordinates are not valid
		if(lat > 90 || lat < -90) {
			throw new GPSDataException("Latitude out of boundaries!");
		}
		if(lon > 180 || lon < -180) {
			throw new GPSDataException("Longitude out of boundaries!");
		}
		System.out.println(carsharing + " " + gasolinetype);
		List<GasStation> gasStations;
		
		// Filter Gas station by gasoline types
		if((carsharing == null) || (carsharing.compareTo("null") == 0)) {
			switch(gasolinetype){
				case "Diesel":
					gasStations = gasStationRepository.findByHasDieselTrue();
				break;
				case "Super":
					gasStations = gasStationRepository.findByHasSuperTrue();
				break;
				case "SuperPlus":
					gasStations = gasStationRepository.findByHasSuperPlusTrue();
				break;
				case "Gas":
					gasStations = gasStationRepository.findByHasGasTrue();
				break;
				case "Methane":
					gasStations = gasStationRepository.findByHasMethaneTrue();
				break;
				case "null": case "Select gasoline type":
					gasStations = gasStationRepository.findAll();
				break;
				default:
					throw new InvalidGasTypeException(gasolinetype + " ISN'T A VALID GAS TYPE!");				
			}
		}
		else{
			switch(gasolinetype){
				case "Diesel":
					gasStations = gasStationRepository.findByHasDieselTrueAndCarSharing(carsharing);
				break;
				case "Super":
					gasStations = gasStationRepository.findByHasSuperTrueAndCarSharing(carsharing);
				break;
				case "SuperPlus":
					gasStations = gasStationRepository.findByHasSuperPlusTrueAndCarSharing(carsharing);
				break;
				case "Gas":
					gasStations = gasStationRepository.findByHasGasTrueAndCarSharing(carsharing);
				break;
				case "Methane":
					gasStations = gasStationRepository.findByHasMethaneTrueAndCarSharing(carsharing);
				break;
				case "null": case "Select gasoline type":
					gasStations = gasStationRepository.findByCarSharing(carsharing);
				break;
				default:
					throw new InvalidGasTypeException(gasolinetype + " ISN'T A VALID GAS TYPE!");				
			}
		}

		double teta = 0.0089 / Math.cos(lat*Math.PI/180);
		
		// Filter gas station for the coordinates inside the limits of 1km
		Stream<GasStation> filteredGasStations = gasStations.stream()
				.filter(g -> Math.abs(g.getLat() - lat) < 0.009)
				.filter(g -> Math.abs(g.getLon() - lon) < teta);

		// Converting each GasStation to GasStationDto
		return filteredGasStations.map(GasStationConverter::toGasStationDto).collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		
		// Retrieve all gas stations from repository
		List<GasStation> gasStations = gasStationRepository.findAll();
		if(gasStations == null) {
			return new ArrayList<>();
		}
		
		// Filter gas stations by gasoline type
		if(carsharing != null) {
			switch(gasolinetype){
				case "Diesel":
					gasStations = gasStationRepository.findByHasDieselTrueAndCarSharing(carsharing);
				break;
				case "Super":
					gasStations = gasStationRepository.findByHasSuperTrueAndCarSharing(carsharing);
				break;
				case "SuperPlus":
					gasStations = gasStationRepository.findByHasSuperPlusTrueAndCarSharing(carsharing);
				break;
				case "Gas":
					gasStations = gasStationRepository.findByHasGasTrueAndCarSharing(carsharing);
				break;
				case "Methane":
					gasStations = gasStationRepository.findByHasMethaneTrueAndCarSharing(carsharing);
				break;
				case "null": case "Select gasoline type":
					gasStations = gasStationRepository.findAll();
				break;
				default:
					throw new InvalidGasTypeException(gasolinetype + " ISN'T A VALID GAS TYPE!");				
			}
		}
		else {
			switch(gasolinetype){
				case "Diesel":
					gasStations = gasStationRepository.findByHasDieselTrue();
				break;
				case "Super":
					gasStations = gasStationRepository.findByHasSuperTrue();
				break;
				case "SuperPlus":
					gasStations = gasStationRepository.findByHasSuperPlusTrue();
				break;
				case "Gas":
					gasStations = gasStationRepository.findByHasGasTrue();
				break;
				case "Methane":
					gasStations = gasStationRepository.findByHasMethaneTrue();
				break;
				case "null": case "Select gasoline type":
					gasStations = gasStationRepository.findAll();
				break;
				default:
					throw new InvalidGasTypeException(gasolinetype + " ISN'T A VALID GAS TYPE!");				
			}
		}
		
		// Converting each GasStation to GasStationDto
		return gasStations.stream().map(GasStationConverter::toGasStationDto).collect(Collectors.toList());
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		
		// Check if gas station is not valid
		if(gasStationId < 0) {
			throw new InvalidGasStationException("ERROR: GAS STATION ID ISN'T VALID!");
		}
		GasStationDto gasStationDto = getGasStationById(gasStationId);
		if(gasStationDto == null) {
			return;
		}
		
		// Check if prices are not valid
		if((gasStationDto.getHasDiesel() && dieselPrice < 0) || (gasStationDto.getHasSuper() && superPrice < 0) || 
				(gasStationDto.getHasSuperPlus() && superPlusPrice < 0) || (gasStationDto.getHasGas() && gasPrice < 0) || 
				(gasStationDto.getHasMethane() && methanePrice < 0)) {
			throw new PriceException("ERROR: PRICE VALUES AREN'T VALID!");
		}
		
		// Check if user is not valid
		if(userId < 0) {
			throw new InvalidUserException("ERROR: USER ID ISN'T VALID!");
		}
		
		// Round all prices to 3 decimal
		dieselPrice = Double.parseDouble(String.format("%.3f%n", dieselPrice).replace(',', '.'));
		superPrice = Double.parseDouble(String.format("%.3f%n", superPrice).replace(',', '.'));
		superPlusPrice = Double.parseDouble(String.format("%.3f%n", superPlusPrice).replace(',', '.'));
		gasPrice = Double.parseDouble(String.format("%.3f%n", gasPrice).replace(',', '.'));
		methanePrice = Double.parseDouble(String.format("%.3f%n", methanePrice).replace(',', '.'));

		// Update prices only if the gas station has that type of gasoline
		if(gasStationDto.getHasDiesel())
			gasStationDto.setDieselPrice(dieselPrice);
		if(gasStationDto.getHasSuper())
			gasStationDto.setSuperPrice(superPrice);
		if(gasStationDto.getHasSuperPlus())
			gasStationDto.setSuperPlusPrice(superPlusPrice);
		if(gasStationDto.getHasGas())
			gasStationDto.setGasPrice(gasPrice);
		if(gasStationDto.getHasMethane())
			gasStationDto.setMethanePrice(methanePrice);
		
		// Update information about user...
		gasStationDto.setReportUser(userId);
		UserDto userDto = UserConverter.toUserDto(userRepository.findByUserId(userId));
		gasStationDto.setUserDto(userDto);
		
		// ...timestamp...
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy - HH:mm:ss", Locale.ITALY);
		Date now = new Date();
		gasStationDto.setReportTimestamp(sdf.format(now));
		// Try to calculate the dependability with this line below
				//gasStationDto.setReportTimestamp("13/mag/2020 - 09:51:27");
		
		//...and dependability
		// First time dependability depends only on user reputation -> obsolescence = 1
		gasStationDto.setReportDependability(50 * (userDto.getReputation() + 5) / 10.0 + 50);
		
		// Update existing gas station
		gasStationRepository.save(GasStationConverter.toGasStation(gasStationDto));
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		
		// Retrieve all gas stations
		List<GasStation> gasStations = gasStationRepository.findByCarSharing(carSharing);
		if(gasStations == null) {
			return new ArrayList<>();
		}

		// Converting each GasStation to GasStationDto
		return gasStations.stream().map(GasStationConverter::toGasStationDto).collect(Collectors.toList());
	}
		
}
