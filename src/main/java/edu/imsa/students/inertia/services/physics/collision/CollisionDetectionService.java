package edu.imsa.students.inertia.services.physics.collision;

import java.util.ArrayList;
import java.util.List;

import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import javafx.util.Pair;

public class CollisionDetectionService {
	
	public static List<Pair<InertialBridge, InertialBridge>> findIntersectingObjects(List<InertialBridge> objects){
		List<Pair<InertialBridge, InertialBridge>> collidingObjects = new ArrayList<>();
		int objectsExisting = objects.size();
		
		if(objectsExisting > 1) {
			for(int i = 0; i < objectsExisting; i++) {
				// get the ith object from the object list
				InertialBridge objectA = objects.get(i);
				for(int j = i + 1; j < objectsExisting; j++) {
					// get the (i + 1) to terminal element from the object list for comparison
					InertialBridge objectB = objects.get(j);
					
					if(objectA.getShape().getBoundsInParent().intersects(objectB.getShape().getBoundsInParent())) {
						// add to colliding objects if object A and B intersect
						collidingObjects.add(new Pair<>(objectA, objectB));
					}
				}
			}
		}
		
		return collidingObjects;
	}
}
