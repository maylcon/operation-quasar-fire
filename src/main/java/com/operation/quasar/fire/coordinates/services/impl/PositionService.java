package com.operation.quasar.fire.coordinates.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.operation.quasar.fire.coordinates.dtos.DetailPosition;
import com.operation.quasar.fire.coordinates.dtos.DetailSateliteDto;
import com.operation.quasar.fire.coordinates.dtos.PositionDto;
import com.operation.quasar.fire.coordinates.dtos.SateliteDto;
import com.operation.quasar.fire.coordinates.dtos.SateliteUniqueDto;
import com.operation.quasar.fire.coordinates.services.ICoordinatesSateliteService;
import com.operation.quasar.fire.coordinates.services.IMessageSateliteService;
import com.operation.quasar.fire.coordinates.services.IPositionService;

@Service
public class PositionService implements IPositionService{
	
	@Autowired
	private ICoordinatesSateliteService coordinatesService;
	
	@Autowired
	private IMessageSateliteService messageSatelite;
	
	@Override
	public PositionDto getPositionMessage(SateliteDto data) {
		List<Double> listDistance = new ArrayList<>();
		List<String[]> listMessage = new ArrayList<>();
		for(DetailSateliteDto satelite: data.getSatellites()) {
			listDistance.add(satelite.getDistance());
			listMessage.add(satelite.getMessage());
		}
		double[] distances = listDistance.stream().mapToDouble(Double::doubleValue).toArray();
		String[][] mesagges = listMessage.toArray(new String[0][0]);
		String mensaje = messageSatelite.GetMessage(mesagges);
		double[] coordenadasXY = coordinatesService.GetLocation(distances);
		DetailPosition position = new DetailPosition();
		position.setX(coordenadasXY[0]);
		position.setY(coordenadasXY[1]);
		PositionDto result = new PositionDto();
		result.setMessage(mensaje);
		result.setPosition(position);
		return result;
	}
	
	@Override
	public PositionDto getPositionMessageSatelite(String satelite_name, SateliteUniqueDto data) {
		List<Double> listDistance = new ArrayList<>();
		List<String[]> listMessage = new ArrayList<>();
		listDistance.add(data.getDistance());
		listMessage.add(data.getMessage());
		double[] distances = listDistance.stream().mapToDouble(Double::doubleValue).toArray();
		String[][] mesagges = listMessage.toArray(new String[0][0]);
		String mensaje = messageSatelite.GetMessage(mesagges,satelite_name);
		double[] coordenadasXY = coordinatesService.GetLocation(distances,satelite_name);
		DetailPosition position = new DetailPosition();
		position.setX(coordenadasXY[0]);
		position.setY(coordenadasXY[1]);
		PositionDto result = new PositionDto();
		result.setMessage(mensaje);
		result.setPosition(position);
		return result;
	}
	
	public PositionDto getPositionSatelite(String satelite_name) {		
		String mensaje = "";
		double[] coordenadasXY = coordinatesService.GetLocation(satelite_name);
		DetailPosition position = new DetailPosition();
		position.setX(coordenadasXY[0]);
		position.setY(coordenadasXY[1]);
		PositionDto result = new PositionDto();
		result.setMessage(mensaje);
		result.setPosition(position);
		return result;
	}
}
