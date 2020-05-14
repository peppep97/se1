package it.polito.ezgas.converter;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

public class GasStationConverter {

	public static GasStationDto toGasStationDto(GasStation gasStation) {
		GasStationDto gasStationDto =new GasStationDto();
		gasStationDto.setGasStationId(gasStation.getGasStationId());
		gasStationDto.setGasStationName(gasStation.getGasStationName());
		gasStationDto.setGasStationAddress(gasStation.getGasStationAddress());
		gasStationDto.setHasDiesel(gasStation.getHasDiesel());
		gasStationDto.setHasSuper(gasStation.getHasSuper());
		gasStationDto.setHasSuperPlus(gasStation.getHasSuperPlus());
		gasStationDto.setHasGas(gasStation.getHasGas());
		gasStationDto.setHasMethane(gasStation.getHasMethane());
		gasStationDto.setCarSharing(gasStation.getCarSharing());
		gasStationDto.setLat(gasStation.getLat());
		gasStationDto.setLon(gasStation.getLon());
		gasStationDto.setDieselPrice(gasStation.getDieselPrice());
		gasStationDto.setSuperPrice(gasStation.getSuperPrice());
		gasStationDto.setSuperPlusPrice(gasStation.getSuperPlusPrice());
		gasStationDto.setGasPrice(gasStation.getGasPrice());
		gasStationDto.setMethanePrice(gasStation.getMethanePrice());
		gasStationDto.setUserDto(UserConverter.toUserDto(gasStation.getUser()));
		gasStationDto.setReportUser(gasStation.getReportUser());
		gasStationDto.setReportTimestamp(gasStation.getReportTimestamp());
		gasStationDto.setReportDependability(gasStation.getReportDependability());
		
		return gasStationDto;
	}

	public static GasStation toGasStation(GasStationDto gasStationDto) {
		GasStation gasStation = new GasStation();
		gasStation.setGasStationId(gasStationDto.getGasStationId());
		gasStation.setGasStationName(gasStationDto.getGasStationName());
		gasStation.setGasStationAddress(gasStationDto.getGasStationAddress());
		gasStation.setHasDiesel(gasStationDto.getHasDiesel());
		gasStation.setHasSuper(gasStationDto.getHasSuper());
		gasStation.setHasSuperPlus(gasStationDto.getHasSuperPlus());
		gasStation.setHasMethane(gasStationDto.getHasMethane());
		gasStation.setHasGas(gasStationDto.getHasGas());
		gasStation.setCarSharing(gasStationDto.getCarSharing());
		gasStation.setLat(gasStationDto.getLat());
		gasStation.setLon(gasStationDto.getLon());
		gasStation.setDieselPrice(gasStationDto.getDieselPrice());
		gasStation.setSuperPrice(gasStationDto.getSuperPrice());
		gasStation.setSuperPlusPrice(gasStationDto.getSuperPlusPrice());
		gasStation.setGasPrice(gasStationDto.getGasPrice());
		gasStation.setMethanePrice(gasStationDto.getMethanePrice());
		gasStation.setUser(UserConverter.toUser(gasStationDto.getUserDto()));
		gasStation.setReportUser(gasStationDto.getReportUser());
		gasStation.setReportTimestamp(gasStationDto.getReportTimestamp());
		gasStation.setReportDependability(gasStationDto.getReportDependability());

		return gasStation;
	}
	

}
