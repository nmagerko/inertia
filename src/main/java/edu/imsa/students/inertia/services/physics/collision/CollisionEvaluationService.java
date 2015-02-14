package edu.imsa.students.inertia.services.physics.collision;

import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import javafx.util.Pair;

public class CollisionEvaluationService {

	public static void evaluateCollidingPair(Pair<InertialBridge, InertialBridge> collidingPair) {
		// get each collider's attributes
		InertialAttributes colliderA = collidingPair.getKey().getInertialAttributes();
		InertialAttributes colliderB = collidingPair.getValue().getInertialAttributes();
		
		// http://www.euclideanspace.com/physics/dynamics/collision/twod/		
		// ???	
	}
}
