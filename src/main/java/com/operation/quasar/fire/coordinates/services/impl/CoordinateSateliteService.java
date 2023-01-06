package com.operation.quasar.fire.coordinates.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.operation.quasar.fire.coordinates.services.ICoordinatesSateliteService;

@Service
public class CoordinateSateliteService implements ICoordinatesSateliteService{
	
	private static Logger logger = LoggerFactory.getLogger(CoordinateSateliteService.class);
	
	@Autowired
	private Environment environment;
	
	@Override
	public double[] GetLocation(double[] distances){
		TrilaterationFunction loadTrilateration = new TrilaterationFunction(getCoordinatesSatelite(), distances);
		NonLinearLeastSquaresSolver solverTrilateration = new NonLinearLeastSquaresSolver(loadTrilateration, new LevenbergMarquardtOptimizer());
		double[] coordinatesXY = solverTrilateration.solve().getPoint().toArray();
		return coordinatesXY;
	}
	
	@Override
	public double[] GetLocation(double[] distances, String nameSatelite){
		double[] coordinatesXY = null;
		TrilaterationFunction loadTrilateration = new TrilaterationFunction(getCoordinatesSatelite(nameSatelite), distances);
		NonLinearLeastSquaresSolver solverTrilateration = new NonLinearLeastSquaresSolver(loadTrilateration, new LevenbergMarquardtOptimizer());
		coordinatesXY = solverTrilateration.solve().getPoint().toArray();
		return coordinatesXY;
	}
	
	@Override
	public double[] GetLocation(String nameSatelite){
		return getCoordinatesSatelite(nameSatelite)[0];
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
