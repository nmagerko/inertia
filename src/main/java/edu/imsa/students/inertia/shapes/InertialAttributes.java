package edu.imsa.students.inertia.shapes;

import javax.vecmath.Vector2d;

/**
 * Defines the "inertial" attributes, or
 * those attributes that a Shape derivative
 * must possess in order to be moved dynamically 
 * @author nmagerko
 *
 */
public class InertialAttributes {
	
	private static final Double DEFAULT_MASS = new Double(1.0);
	private static final Vector2d DEFAULT_VELOCITY = new Vector2d(0, 0);
	private static final Vector2d DEFAULT_ACCELERATION = new Vector2d(0, 0);

	private Double mass;
	private Vector2d acceleration;
	private Vector2d velocity;
	
	public InertialAttributes(){
		this.mass = DEFAULT_MASS;
		this.velocity = DEFAULT_VELOCITY;
		this.acceleration = DEFAULT_ACCELERATION;
	}

	public InertialAttributes(Double mass, Vector2d acceleration, Vector2d velocity) {
		this.mass = mass;
		this.velocity = velocity;
		this.acceleration = acceleration;
	}

	public Double getMass() {
		return mass;
	}

	public void setMass(Double mass) {
		this.mass = mass;
	}

	public Vector2d getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2d acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2d getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2d velocity) {
		this.velocity = velocity;
	}

}
