package com.operation.quasar.fire.coordinates.services;

import com.operation.quasar.fire.coordinates.dtos.PositionDto;
import com.operation.quasar.fire.coordinates.dtos.SateliteDto;
import com.operation.quasar.fire.coordinates.dtos.SateliteUniqueDto;

public interface IPositionService {
	public PositionDto getPositionMessage(SateliteDto data);
	public PositionDto getPositionMessageSatelite(String satelite_name, SateliteUniqueDto data);
	public PositionDto getPositionSatelite(String satelite_name);
}
