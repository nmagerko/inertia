package edu.imsa.students.inertia.services.physics.force;

import javax.vecmath.Vector2d;

public class GravityForce extends InertialForce{

	private static final long serialVersionUID = 3523289804954754911L;

	
	@Override
	public Vector2d computedAcceleration(Double mass){
		return new Vector2d(0,-9.81);
	}
}
