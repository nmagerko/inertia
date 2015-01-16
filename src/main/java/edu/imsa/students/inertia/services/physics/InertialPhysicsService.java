package edu.imsa.students.inertia.services.physics;

import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.InertialSupervisor;
import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialPhysicsService {

	/**
	 * Steps forward in time, moving all objects
	 * 
	 * @param objectList
	 * @param timeStep
	 * @param supervisor
	 */
	public static void advance(List<? extends InertialBridge> objectList,
			double timeStep, InertialSupervisor supervisor) {
		for (InertialBridge i : objectList) {

			InertialAttributes attributes = i.getInertialAttributes();
			if (!attributes.isInDrag()) {

				Vector2d velocity = attributes.getVelocity();
				Point2d position = i.getPosition();

				// changes acceleration
				attributes.applyForces(timeStep);

				// changes velocity
				// also check collisions
				Pane parentPane = supervisor.getInertialPane();

				Bounds container = parentPane.getLayoutBounds();
				Bounds object = i.getBounds();

				boolean outOfBounds = false;
				// test reflection in x direction
				if (isOutOfXBounds(object, container)) {
					velocity.set(-velocity.x, velocity.y);
					outOfBounds = true;
				}
				// test reflection in y direction
				if (isOutOfYBounds(object, container)) {
					velocity.set(velocity.x, -velocity.y);
					outOfBounds = true;
				}
				
				if (!outOfBounds) {
					Vector2d acceleration = attributes.getAcceleration();
					Vector2d accelerationStep = new Vector2d(acceleration.x,
							acceleration.y);
					accelerationStep.scale(timeStep);
					velocity.add(accelerationStep);
				}

				// changes position
				Vector2d velocityStep = new Vector2d(velocity.x, velocity.y);
				velocityStep.scale(timeStep);
				position.sub(velocityStep);
				i.setPosition(position);
			}
		}
	}

	/**
	 * Checks whether an object is within x bounds of its container.
	 * 
	 * @param object
	 * @param container
	 * @return true if out of bounds, false if in bounds.
	 */
	public static boolean isOutOfXBounds(Bounds object, Bounds container) {
		double leftX = container.getMinX();
		double rightX = container.getMaxX();
		double objectLeftX = object.getMinX();
		double objectRightX = object.getMaxX();
		if (objectLeftX < leftX) {
			return true;
		} else if (objectRightX > rightX) {
			return true;
		}
		return false;
	}

	/**
	 * Checks whether an object is within y bounds of its container.
	 * 
	 * @param object
	 * @param container
	 * @return true if out of bounds, false if in bounds.
	 */
	public static boolean isOutOfYBounds(Bounds object, Bounds container) {
		double topY = container.getMinY();
		double bottomY = container.getMaxY();
		double objectTopY = object.getMinY();
		double objectBottomY = object.getMaxY();
		if (objectTopY < topY) {
			return true;
		}
		if (objectBottomY > bottomY) {
			return true;
		}
		return false;
	}

}
