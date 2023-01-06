package com.operation.quasar.fire.coordinates.services;

public interface ICoordinatesSateliteService {
	public double[] GetLocation(double[] distances);
	public double[] GetLocation(double[] distances, String nameSatelite);
	public double[] GetLocation(String nameSatelite);
}
