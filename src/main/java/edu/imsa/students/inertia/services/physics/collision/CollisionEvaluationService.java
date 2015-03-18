package edu.imsa.students.inertia.services.physics.collision;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.services.physics.InertialPhysicsService;
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
		
		double distanceX = -(colliderA.getCenter().x - colliderB.getCenter().x);
		double distanceY = -(colliderA.getCenter().y - colliderB.getCenter().y);
	
		Vector2d direction = new Vector2d(distanceX, distanceY);
		direction.normalize();
		
		InertialAttributes attributesA = colliderA.getInertialAttributes();
		InertialAttributes attributesB = colliderB.getInertialAttributes();
		
		Vector2d velocityVectorA = attributesA.getVelocity();
		Vector2d velocityVectorB = attributesB.getVelocity();
		Double massA = attributesA.getMass();	
		Double massB = attributesB.getMass();
		Double coefficient = (2.0*(massA*massB)/(massA+massB));
		
		Vector2d differenceAB = new Vector2d(velocityVectorA);
		differenceAB.sub(velocityVectorB);
		
		Vector2d dotProduct = new Vector2d(differenceAB);
		Double coefficientFinal = dotProduct.dot(direction)*coefficient;
		
		Vector2d impulseA = new Vector2d(direction);
		impulseA.scale(coefficientFinal);
		Vector2d impulseB = new Vector2d(direction);
		impulseB.scale(coefficientFinal);

		impulseA.scale(1.0/massA);
		impulseB.scale(1.0/massB);
		
		velocityVectorA.sub(impulseA);
		velocityVectorB.add(impulseB);
		
		Point2d positionA = colliderA.getPosition();
		Point2d positionB = colliderB.getPosition();
		while (colliderA.getShape().getBoundsInParent().intersects(colliderB.getShape().getBoundsInParent())) {
			positionA.x -= velocityVectorA.x * InertialPhysicsService.TIME_STEP;
			positionA.y -= velocityVectorA.y * InertialPhysicsService.TIME_STEP;
			positionB.x -= velocityVectorB.x * InertialPhysicsService.TIME_STEP;
			positionB.y -= velocityVectorB.y * InertialPhysicsService.TIME_STEP;
			
			colliderA.setPosition(positionA);
			colliderB.setPosition(positionB);
		}
	}
}
