package edu.imsa.students.inertia.services.physics;

import java.util.List;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
public class InertialPhysicsService {
	
	public static void advance(List<? extends InertialBridge> objectList, double timeStep){
		for(InertialBridge i : objectList){
			InertialAttributes attributes = i.getInertialAttributes();
			//changes position
			Point2d position = i.getPosition();
			Vector2d velocity = attributes.getVelocity();
			Vector2d velocityStep = new Vector2d(velocity.x, velocity.y);
			velocityStep.scale(timeStep);
			position.sub(velocityStep);
			i.setPosition(position);
			//changes velocity
			Vector2d acceleration = attributes.getAcceleration();
			Vector2d accelerationStep = new Vector2d(acceleration.x, acceleration.y);
			accelerationStep.scale(timeStep);
			velocity.add(accelerationStep);
			//changes acceleration
			attributes.applyForces(timeStep);
		}
	}

}
