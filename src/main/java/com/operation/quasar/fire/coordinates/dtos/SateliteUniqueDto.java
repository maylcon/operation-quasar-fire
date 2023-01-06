package com.operation.quasar.fire.coordinates.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SateliteUniqueDto {
	private double distance;
	private String[] message;
}