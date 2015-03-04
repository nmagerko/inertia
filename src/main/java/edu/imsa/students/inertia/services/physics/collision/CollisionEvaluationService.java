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
		// get each collider's attributes
		InertialAttributes colliderA = collidingPair.getKey().getInertialAttributes();
		InertialAttributes colliderB = collidingPair.getValue().getInertialAttributes();

		Vector2d velocityVectorA = colliderA.getVelocity();
		Vector2d velocityVectorB = colliderB.getVelocity();
		Double massA = colliderA.getMass();	
		Double massB = colliderB.getMass();
		Double coefficient = (2.0*(massA*massB)/(massA+massB));
		Vector2d unitA = new Vector2d(velocityVectorA);
		unitA.normalize();
		Vector2d unitB = new Vector2d(velocityVectorB);
		unitB.normalize();
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
		// http://www.euclideanspace.com/physics/dynamics/collision/twod/		
		// ???	
	}
}
