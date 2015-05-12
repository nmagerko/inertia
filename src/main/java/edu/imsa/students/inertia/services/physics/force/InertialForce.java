package edu.imsa.students.inertia.services.physics.force;

import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;


public class InertialForce extends Vector2d {
	
	private static final long serialVersionUID = 2085296602259075032L;

	public Vector2d computedAcceleration(InertialAttributes<? extends InertialBridge> attributes) {
		Vector2d acceleration = new Vector2d(this.x,this.y);
		//     F/m=a
		acceleration.scale(1.0/attributes.getMass());
		return acceleration;
	}

}
