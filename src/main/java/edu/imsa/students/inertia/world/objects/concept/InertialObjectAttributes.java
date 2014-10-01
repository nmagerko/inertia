package edu.imsa.students.inertia.world.objects.concept;

import edu.imsa.students.inertia.utils.vectors.InertialVector;

/**
 * A POJO containing fields common to all
 * 2D objects in the project. Position is 
 * excluded to eliminate any asynchronous
 * data-reporting issues between the object
 * and these attributes
 * 
 * @author nmagerko
 *
 */
public class InertialObjectAttributes {

	private Double mass;
	private InertialVector velocity;
	
	public InertialObjectAttributes(){
		
	}
	
	public InertialObjectAttributes(Double mass, InertialVector velocity){
		this.mass = mass;
		this.velocity = velocity;
	}

	public void setMass(Double mass) {
		this.mass = mass;
	}
	
	public void setVelocity(InertialVector velocity) {
		this.velocity = velocity;
	}
	
	public Double getMass() {
		return mass;
	}

	public InertialVector getVelocity() {
		return velocity;
	}
	
}
