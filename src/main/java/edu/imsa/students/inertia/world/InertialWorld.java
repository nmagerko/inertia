package edu.imsa.students.inertia.world;

import java.util.ArrayList;
import java.util.List;

import edu.imsa.students.inertia.world.objects.concept.InertialObjectConcept;

public class InertialWorld {
	
	private Double gravitationalAcceleration;
	private Double airResistance;
	private List<InertialObjectConcept> objects;
	
	public InertialWorld(){
		this.objects = new ArrayList<InertialObjectConcept>();
	}
	
	public InertialWorld(List<InertialObjectConcept> objects){
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
