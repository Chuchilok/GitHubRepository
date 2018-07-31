package com.dogpro.domain.model.extend;

import com.dogpro.domain.model.DogLocation;

public class DoglocationDistance extends DogLocation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6106305692694389526L;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
