package edu.imsa.students.inertia.world;

import java.util.ArrayList;
import java.util.List;

import edu.imsa.students.inertia.world.objects.concept.InertialConcept;

public class InertialWorld {
	
	private Double gravitationalAcceleration;
	private Double airResistance;
	private List<InertialConcept> objects;
	
	public InertialWorld(){
		this.objects = new ArrayList<InertialConcept>();
	}
	
	public InertialWorld(List<InertialConcept> objects){
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
