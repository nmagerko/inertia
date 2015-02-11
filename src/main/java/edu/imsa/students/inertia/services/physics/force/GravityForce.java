package edu.imsa.students.inertia.services.physics.force;

import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;

public class GravityForce extends InertialForce{

	private static final long serialVersionUID = 3523289804954754911L;

	
	@Override
	public Vector2d computedAcceleration(InertialAttributes attributes){
		Double gravityScalar = attributes.getGravityScalar()/100.0;
		return new Vector2d(0,-9.81*gravityScalar);
	}
}
