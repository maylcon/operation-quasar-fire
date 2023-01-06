package com.operation.quasar.fire.coordinates.dtos;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SateliteDto {
	private ArrayList<DetailSateliteDto> satellites;
}
