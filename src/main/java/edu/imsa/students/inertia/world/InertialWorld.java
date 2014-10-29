package edu.imsa.students.inertia.world;

import java.util.ArrayList;
import java.util.List;

import edu.imsa.students.inertia.world.objects.concept.InertialObject;

public class InertialWorld {
	
	private Double gravitationalAcceleration;
	private Double airResistance;
	private List<InertialObject> objects;
	
	public InertialWorld(){
		this.objects = new ArrayList<InertialObject>();
	}
	
	public InertialWorld(List<InertialObject> objects){
		this.objects = objects;
	}

	public Double getGravitationalAcceleration() {
		return gravitationalAcceleration;
	}

	public void setGravitationalAcceleration(Double gravitationalAcceleration) {
		this.gravitationalAcceleration = gravitationalAcceleration;
	}

	public Double getAirResistance() {
		return airResistance;
	}

	public void setAirResistance(Double airResistance) {
		this.airResistance = airResistance;
	}
}
