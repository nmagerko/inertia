package edu.imsa.students.inertia.services.physics.collision;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import javafx.util.Pair;

public class CollisionEvaluationService {
	
	public static Point2d evaluatePointOfCollision(){
		return null;
	}
	public static void evaluateCollidingPair(Pair<InertialBridge, InertialBridge> collidingPair) {
		// get each collider
		InertialBridge colliderA = collidingPair.getKey();
		InertialBridge colliderB = collidingPair.getValue();
		
		double distanceX = colliderA.getCenter().x - colliderB.getCenter().x;
		double distanceY = colliderA.getCenter().y - colliderB.getCenter().y;
		
		Vector2d unitA = new Vector2d();
		Vector2d unitB = new Vector2d();
		if(colliderA.getCenter().x < colliderB.getCenter().x) {
			unitA.x = distanceX;
			unitB.x = -distanceX;
		} else {
			unitA.x = -distanceX;
			unitB.x = distanceX;
		}
		if (colliderA.getCenter().y < colliderB.getCenter().y) {
			unitA.y = distanceY;
			unitB.y = -distanceY;
		} else {
			unitA.y = -distanceY;
			unitB.y = distanceY;
		}
		
		unitA.normalize();
		unitB.normalize();
		
		InertialAttributes attributesA = colliderA.getInertialAttributes();
		InertialAttributes attributesB = colliderB.getInertialAttributes();
		
		Vector2d velocityVectorA = attributesA.getVelocity();
		Vector2d velocityVectorB = attributesB.getVelocity();
		Double massA = attributesA.getMass();	
		Double massB = attributesB.getMass();
		Double coefficient = (2.0*(massA*massB)/(massA+massB));
		
		Vector2d differenceAB = new Vector2d(velocityVectorA);
		differenceAB.sub(velocityVectorB);
		
		Vector2d dotA = new Vector2d(differenceAB);
		Double coefficientA = dotA.dot(unitA)*coefficient;
		
		Vector2d dotB = new Vector2d(differenceAB);
		Double coefficientB = dotB.dot(unitB)*coefficient;
		
		Vector2d impulseA = new Vector2d(unitA);
		impulseA.scale(coefficientA);
		Vector2d impulseB = new Vector2d(unitB);
		impulseB.scale(coefficientB);

		impulseA.scale(1.0/massA);
		impulseB.scale(1.0/massB);
		velocityVectorA.sub(impulseA);
		velocityVectorB.add(impulseB);
	}
}
