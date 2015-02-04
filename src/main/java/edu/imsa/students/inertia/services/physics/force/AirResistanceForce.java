package edu.imsa.students.inertia.services.physics.force;

import javax.vecmath.Tuple2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;

public class AirResistanceForce extends InertialForce {
	
	private static final long serialVersionUID = 1L;
	private final Double RESISTANCE_CONSTANT = 0.18;
	
	@Override
	public Vector2d computedAcceleration(InertialAttributes attributes) {
		Double airScalar =attributes.getAirScalar()/100;
		Vector2d resistance = new Vector2d((Tuple2d) attributes.getVelocity().clone());
		resistance.scale(-1*RESISTANCE_CONSTANT*airScalar);
		return resistance;
	}
}
