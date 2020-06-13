package it.polito.ezgas.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

public class GasStationConverter {
	
	// Convert from gasstatiodto to gasstation
	public static GasStation toGasStation(GasStationDto gasStationDto) {
		
		GasStation gasStation = new GasStation();
		gasStation.setGasStationId(gasStationDto.getGasStationId());
		gasStation.setGasStationName(gasStationDto.getGasStationName());
		gasStation.setGasStationAddress(gasStationDto.getGasStationAddress());
		gasStation.setHasDiesel(gasStationDto.getHasDiesel());
		gasStation.setHasSuper(gasStationDto.getHasSuper());
		gasStation.setHasSuperPlus(gasStationDto.getHasSuperPlus());
		gasStation.setHasMethane(gasStationDto.getHasMethane());
		gasStation.setHasPremiumDiesel(gasStationDto.getHasPremiumDiesel());
		gasStation.setHasGas(gasStationDto.getHasGas());
		if(gasStationDto.getCarSharing() == null)
			gasStation.setCarSharing("null");
		else
			gasStation.setCarSharing(gasStationDto.getCarSharing());
		gasStation.setLat(gasStationDto.getLat());
		gasStation.setLon(gasStationDto.getLon());
		gasStation.setDieselPrice(gasStationDto.getDieselPrice());
		gasStation.setSuperPrice(gasStationDto.getSuperPrice());
		gasStation.setSuperPlusPrice(gasStationDto.getSuperPlusPrice());
		gasStation.setGasPrice(gasStationDto.getGasPrice());
		gasStation.setMethanePrice(gasStationDto.getMethanePrice());
		gasStation.setPremiumDieselPrice(gasStationDto.getPremiumDieselPrice());
		gasStation.setUser(UserConverter.toUser(gasStationDto.getUserDto()));
		gasStation.setReportUser(gasStationDto.getReportUser());
		gasStation.setReportTimestamp(gasStationDto.getReportTimestamp());
		// Check if gas station has a price report
		if(gasStationDto.getReportTimestamp() != null)
			gasStation.setReportDependability(dependabilityCalculator(gasStationDto.getReportTimestamp(), gasStationDto.getUserDto().getReputation()));
		
		return gasStation;
	}
	
	// Convert from gasstation to gasstationdto
	public static GasStationDto toGasStationDto(GasStation gasStation) {
		
		GasStationDto gasStationDto = new GasStationDto();
		gasStationDto.setGasStationId(gasStation.getGasStationId());
		gasStationDto.setGasStationName(gasStation.getGasStationName());
		gasStationDto.setGasStationAddress(gasStation.getGasStationAddress());
		gasStationDto.setHasDiesel(gasStation.getHasDiesel());
		gasStationDto.setHasSuper(gasStation.getHasSuper());
		gasStationDto.setHasSuperPlus(gasStation.getHasSuperPlus());
		gasStationDto.setHasGas(gasStation.getHasGas());
		gasStationDto.setHasMethane(gasStation.getHasMethane());
		gasStationDto.setHasPremiumDiesel(gasStation.getHasPremiumDiesel());
		if(gasStation.getCarSharing().compareTo("null") == 0)
			gasStationDto.setCarSharing(null);
		else
			gasStationDto.setCarSharing(gasStation.getCarSharing());
		gasStationDto.setLat(gasStation.getLat());
		gasStationDto.setLon(gasStation.getLon());
		gasStationDto.setDieselPrice(gasStation.getDieselPrice());
		gasStationDto.setSuperPrice(gasStation.getSuperPrice());
		gasStationDto.setSuperPlusPrice(gasStation.getSuperPlusPrice());
		gasStationDto.setGasPrice(gasStation.getGasPrice());
		gasStationDto.setMethanePrice(gasStation.getMethanePrice());
		gasStationDto.setPremiumDieselPrice(gasStation.getPremiumDieselPrice());
		gasStationDto.setUserDto(UserConverter.toUserDto(gasStation.getUser()));
		gasStationDto.setReportUser(gasStation.getReportUser());
		gasStationDto.setReportTimestamp(gasStation.getReportTimestamp());
		// Check if gas station has a price report

		if(gasStation.getReportTimestamp() != null)
			gasStationDto.setReportDependability(dependabilityCalculator(gasStation.getReportTimestamp(), gasStation.getUser().getReputation()));
		
		return gasStationDto;
	}
	
	// Calculate the value of dependability
	public static double dependabilityCalculator(String timestamp, Integer trust) {
		
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.ITALY);
		// Try to convert timestamp into GregorianCalendar format
		try { cal.setTime(sdf.parse(timestamp)); } catch (ParseException e) { e.printStackTrace(); }
		// Convert timestamps in milliseconds
		long msNow = now.getTimeInMillis();
		long msReportTime = cal.getTimeInMillis();
		// Calculate difference 
		long diff = msNow - msReportTime;
		double obs = 0;
		// Check if report timestamp is not older than 7 days
		if((diff / (24 * 60 * 60 * 1000))<=7) 
			// Calculate obsolescence
			obs = 1 - ((double)(diff / (24 * 60 * 60 * 1000)) / 7);
		
		return Double.parseDouble(String.format("%.2f%n", (50 * (trust +5)/10 + 50 * obs)).replace(',', '.'));
	}
	

}
