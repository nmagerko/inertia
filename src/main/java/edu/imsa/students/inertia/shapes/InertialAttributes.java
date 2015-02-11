package edu.imsa.students.inertia.shapes;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import javax.vecmath.Tuple2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.InertialApplication;
import edu.imsa.students.inertia.services.physics.force.AirResistanceForce;
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
	private final Double DEFAULT_AIR_RESISTANCE_CONSTANT = new Double(0.3);
	private final GravityForce gravity = new GravityForce();
	private final AirResistanceForce airResistance = new AirResistanceForce();

	private Double mass;
	private Vector2d acceleration;
	private Vector2d velocity;
	private boolean inDrag = false;
	private ArrayList<InertialForce> forces=new ArrayList<>();
	
	private Double gravityScalar= new Double (100.0);
	private Double massScalar = new Double(100.0);
	private Double airScalar = new Double(100.0);
	private Double restitutionScalar = new Double(100.0);
	
	private LineChart<Number, Number> previousPositions;
	
	private void setUpLineChart() {
		// TODO: could these be final? 
		NumberAxis xAxis = new NumberAxis(0, 15,5);
        NumberAxis yAxis = new NumberAxis();
		this.previousPositions = new LineChart<Number, Number>(xAxis, yAxis);
		this.previousPositions.setCreateSymbols(false);
		this.previousPositions.setAnimated(false);
	}
	
	public InertialAttributes(){
		this.mass = DEFAULT_MASS;
		this.velocity = DEFAULT_VELOCITY;
		this.acceleration = DEFAULT_ACCELERATION;
		forces.add(gravity);
		forces.add(airResistance);
		
		setUpLineChart();
	}

	public InertialAttributes(Double mass, Vector2d acceleration, Vector2d velocity) {
		this.mass = mass;
		this.velocity = velocity;
		this.acceleration = acceleration;
		forces.add(gravity);
		forces.add(airResistance);
		
		setUpLineChart();
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
		acceleration.set(0.0, 0.0);
		for(InertialForce force: forces)
		{
			Vector2d forceStep = new Vector2d(((Tuple2d) force.computedAcceleration(this).clone()));
			acceleration.add(forceStep);
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
	
	public LineChart<Number, Number> getPositionChart() {
		return this.previousPositions;
	}
	
}
