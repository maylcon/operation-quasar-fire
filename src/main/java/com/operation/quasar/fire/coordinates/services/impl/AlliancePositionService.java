package com.operation.quasar.fire.coordinates.services.impl;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.operation.quasar.fire.coordinates.services.IAlliancePositionService;

@Service
public class AlliancePositionService implements IAlliancePositionService {
	private double[][] positionSatellites = new double[][]{
															{-500, -200}, //Kenobi
															{100, -100},  //Skywalker
															{500, 100}    //Sato
														  };
	
	@Override
	public double[] GetLocation(double[] distances){
		TrilaterationFunction loadTrilateration = new TrilaterationFunction(positionSatellites, distances);
		NonLinearLeastSquaresSolver solverTrilateration = new NonLinearLeastSquaresSolver(loadTrilateration, new LevenbergMarquardtOptimizer());
		double[] coordinatesXY = solverTrilateration.solve().getPoint().toArray();
		return coordinatesXY;
	}
	
	@Override
	public String GetMessage(String [][] messages) {
		ArrayList<String> listMessage = new ArrayList<>();
		String messageComplete = "";
		if (messages.length == positionSatellites.length) {
			for(int i = 0; i < messages[0].length; i++) {
				for(int j = 0; j < messages.length; j++) {
					listMessage.add(messages[j][i]);
				}
			}
		}
		messageComplete = listMessage.stream().distinct().filter(x -> !x.isBlank()).collect(Collectors.joining(" "));
		return messageComplete;
	}
}
