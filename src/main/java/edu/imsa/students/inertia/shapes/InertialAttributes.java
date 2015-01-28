package edu.imsa.students.inertia.shapes;

import java.util.ArrayList;

import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.services.physics.force.GravityForce;
import edu.imsa.students.inertia.services.physics.force.InertialForce;

/**
 * Defines the "inertial" attributes, or
 * those attributes that a Shape derivative
 * must possess in order to be moved dynamically 
 * @author nmagerko
 *
 */
public class InertialAttributes {
	
	private final Double DEFAULT_MASS = new Double(1.0);
	private final Vector2d DEFAULT_VELOCITY = new Vector2d(0, 0);
	private final Vector2d DEFAULT_ACCELERATION = new Vector2d(0, 0);
	private final GravityForce gravity = new GravityForce();
	
	private Double mass;
	private Vector2d acceleration;
	private Vector2d velocity;
	private boolean inDrag = false;
	private ArrayList<InertialForce> forces=new ArrayList<>();
	
	private Double gravityScalar=new Double(1.0);
	private Double massScalar = new Double(1.0);
	private Double airScalar = new Double(1.0);
	private Double restitutionScalar = new Double(1.0);
	
	public InertialAttributes(){
		this.mass = DEFAULT_MASS;
		this.velocity = DEFAULT_VELOCITY;
		this.acceleration = DEFAULT_ACCELERATION;
		forces.add(gravity);
	}

	public InertialAttributes(Double mass, Vector2d acceleration, Vector2d velocity) {
		this.mass = mass;
		this.velocity = velocity;
		this.acceleration = acceleration;
		forces.add(gravity);
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
	
	public void addForce(InertialForce force) {
		forces.add(force);
	}

	public void applyForces(double timeStep)
	{
		for(InertialForce force: forces)
		{
			Vector2d forceStep = new Vector2d(force.computedAcceleration(mass).x, force.computedAcceleration(mass).y);
			acceleration.set(forceStep);
		}
	}

	public boolean isInDrag() {
		return inDrag;
	}

	public void setInDrag(boolean inDrag) {
		this.inDrag = inDrag;
	}

	/**
	 * @return the gravityScalar
	 */
	public Double getGravityScalar() {
		return gravityScalar;
	}

	/**
	 * @param gravityScalar the gravityScalar to set
	 */
	public void setGravityScalar(Double gravityScalar) {
		this.gravityScalar = gravityScalar;
	}

	/**
	 * @return the massScalar
	 */
	public Double getMassScalar() {
		return massScalar;
	}

	/**
	 * @param massScalar the massScalar to set
	 */
	public void setMassScalar(Double massScalar) {
		this.massScalar = massScalar;
	}

	/**
	 * @return the airScalar
	 */
	public Double getAirScalar() {
		return airScalar;
	}

	/**
	 * @param airScalar the airScalar to set
	 */
	public void setAirScalar(Double airScalar) {
		this.airScalar = airScalar;
	}

	/**
	 * @return the restitutionScalar
	 */
	public Double getRestitutionScalar() {
		return restitutionScalar;
	}

	/**
	 * @param restitutionScalar the restitutionScalar to set
	 */
	public void setRestitutionScalar(Double restitutionScalar) {
		this.restitutionScalar = restitutionScalar;
	}
	
}
