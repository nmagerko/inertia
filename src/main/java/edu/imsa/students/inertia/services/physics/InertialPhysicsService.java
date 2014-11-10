package edu.imsa.students.inertia.services.physics;

import java.util.List;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
public class InertialPhysicsService {
	
	public static void advance(List<? extends InertialBridge> objectList){
		for(InertialBridge i : objectList){
			InertialAttributes attributes = i.getInertialAttributes();
			//changes position
			Point2d position = i.getPosition();
			position.add(attributes.getVelocity());
			i.setPosition(position);
			//changes velocity
			Vector2d velocity = attributes.getVelocity();
			velocity.add(attributes.getAcceleration());
			attributes.setVelocity(velocity);
			//changes acceleration
			attributes.applyForces();
		}
	}

}
