package edu.imsa.students.inertia.services.physics.force;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.InertialApplication;
import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class IntergravitationalForce extends InertialForce {
	
	public static final double G = 6.67384e-11;
	
	@Override
	public Vector2d computedAcceleration(InertialAttributes<? extends InertialBridge> attributes){
		Vector2d totalForce = new Vector2d(0,0);
		double satelliteMass = attributes.getMass();
		Point2d satellitePosition = attributes.getParent().getCenter();
		
		List<InertialBridge> objects = new ArrayList<InertialBridge>();
		objects.addAll(InertialApplication.getMainController().getSupervisedWorld().getObjects());
		objects.remove(attributes.getParent());
		
		for(InertialBridge centralObject: objects){
			Point2d centralPosition = centralObject.getCenter();
			double centralMass = centralObject.getInertialAttributes().getMass();
			
			Vector2d deltaPosition = new Vector2d(satellitePosition.x, satellitePosition.y);
			deltaPosition.sub(centralPosition);
			double radius = satellitePosition.distance(centralPosition);
			System.out.println(radius);
			deltaPosition.normalize();
			double forceMagnitude = G*satelliteMass*centralMass/Math.pow(radius,2);
			deltaPosition.scale(forceMagnitude);
			totalForce.add(deltaPosition);
		}
		totalForce.scale(1/satelliteMass);
		return totalForce;
	}
}
