package edu.imsa.students.inertia.services.physics.force;

import javax.vecmath.Vector2d;

public class GravityForce extends InertialForce{

	private static final long serialVersionUID = 3523289804954754911L;

	
	@Override
	public Vector2d computedAcceleration(Double mass){
		
		/*
		 * double universalGravitationalConstant = 6.67*Math.pow(10,-11);
		 * double earthRadius = 6.31*Math.pow(10, 6);
		 * double earthMass = 5.97219*Math.pow(10,24);
		 * double gravitationalForce = -universalGravitationalConstant*earthMass*mass/Math.pow(earthRadius,2);
		 */
		
		return new Vector2d(0,-9.81);
	}
}
