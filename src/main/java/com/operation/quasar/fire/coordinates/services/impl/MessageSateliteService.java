package com.operation.quasar.fire.coordinates.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.operation.quasar.fire.coordinates.services.IMessageSateliteService;


@Service
public class MessageSateliteService implements IMessageSateliteService {
	
	private static Logger logger = LoggerFactory.getLogger(MessageSateliteService.class);
	
	@Autowired
	private Environment environment;
	
	@Override
	public String GetMessage(String [][] messages) {
		ArrayList<String> listMessage = new ArrayList<>();
		String messageComplete = "";
		if (messages.length == getCoordinatesSatelite().length) {
			for(int i = 0; i < messages[0].length; i++) {
				for(int j = 0; j < messages.length; j++) {
					listMessage.add(messages[j][i]);
				}
			}
		}
		messageComplete = listMessage.stream().distinct().filter(x -> !x.isBlank()).collect(Collectors.joining(" "));
		return messageComplete;
	}
	
	@Override
	public String GetMessage(String [][] messages, String nameSatelite) {
		ArrayList<String> listMessage = new ArrayList<>();
		String messageComplete = "";
		if (messages.length == getCoordinatesSatelite(nameSatelite).length) {
			for(int i = 0; i < messages[0].length; i++) {
				for(int j = 0; j < messages.length; j++) {
					listMessage.add(messages[j][i]);
				}
			}
		}
		messageComplete = listMessage.stream().distinct().filter(x -> !x.isBlank()).collect(Collectors.joining(" "));
		return messageComplete;
	}
	
	//kenobi*-500,-200$skywalker*100,-100$sato*500,100
	public double[][] getCoordinatesSatelite(String nameSatelite) {
		String satelitesEnvironment = environment.getProperty("satelites.coordinates");
		String[] listSatelites = satelitesEnvironment.split("\\$");
		int cantidadSatelites = listSatelites.length;
		ArrayList<double[]> listCoordinates = new ArrayList<>();
		for(int i = 0; i < cantidadSatelites; i++) {
			String[] satelite = listSatelites[i].split("\\*");
			if(satelite[0].equalsIgnoreCase(nameSatelite)) {
				double[] doubleValues = Arrays.stream(satelite[1].split(","))
	                    .mapToDouble(Double::parseDouble)
	                    .toArray();
				listCoordinates.add(doubleValues);
				break;
			}
		}
		double[][] coordinatesXY = listCoordinates.toArray(new double[0][0]);
		return coordinatesXY;
	}
	
	public double[][] getCoordinatesSatelite() {
		String satelitesEnvironment = environment.getProperty("satelites.coordinates");
		String[] listSatelites = satelitesEnvironment.split("\\$");
		int cantidadSatelites = listSatelites.length;
		ArrayList<double[]> listCoordinates = new ArrayList<>();
		for(int i = 0; i < cantidadSatelites; i++) {
			String[] satelite = listSatelites[i].split("\\*");
			double[] doubleValues = Arrays.stream(satelite[1].split(","))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
			listCoordinates.add(doubleValues);
		}
		double[][] coordinatesXY = listCoordinates.toArray(new double[0][0]);
		return coordinatesXY;
	}
	
	
}
