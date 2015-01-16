package edu.imsa.students.inertia.services.physics.force;


import javax.vecmath.Vector2d;


public class InertialForce extends Vector2d {
	
	private static final long serialVersionUID = 2085296602259075032L;

	/**
	 * Returns the computed acceleration for a force object based on an objects mass
	 */
	public Vector2d computedAcceleration(Double mass) {
		Vector2d acceleration = new Vector2d(this.x,this.y);
		//     F/m=a
		acceleration.scale(1.0/mass);
		return acceleration;
	}

}
