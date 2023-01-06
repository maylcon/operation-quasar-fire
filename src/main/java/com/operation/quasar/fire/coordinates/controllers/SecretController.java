package com.operation.quasar.fire.coordinates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.operation.quasar.fire.coordinates.dtos.PositionDto;
import com.operation.quasar.fire.coordinates.dtos.SateliteUniqueDto;
import com.operation.quasar.fire.coordinates.dtos.SateliteDto;
import com.operation.quasar.fire.coordinates.services.IPositionService;


@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class SecretController {

	@Autowired
	private IPositionService service;
	
	@PostMapping("/topsecret/")
	@ResponseBody
	public ResponseEntity<PositionDto> topSecret(@RequestBody SateliteDto satelites) {
		PositionDto result = service.getPositionMessage(satelites);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/topsecret_split/{satelite_name}")
	@ResponseBody
	public ResponseEntity<PositionDto> topSecretSplit(@PathVariable String satelite_name, @RequestBody SateliteUniqueDto data) {
		PositionDto result = service.getPositionMessageSatelite(satelite_name, data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/topsecret_split/{satelite_name}")
	@ResponseBody
	public ResponseEntity<PositionDto> topSecretSplit(@PathVariable String satelite_name) {		
		PositionDto result = service.getPositionSatelite(satelite_name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
