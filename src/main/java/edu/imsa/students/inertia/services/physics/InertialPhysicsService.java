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

	public static void advance(List<? extends InertialBridge> objectList,
			double timeStep, InertialSupervisor supervisor) {
		for (InertialBridge i : objectList) {

			InertialAttributes attributes = i.getInertialAttributes();
			if (!attributes.isInDrag()) {
				// changes position
				Point2d position = i.getPosition();
				Vector2d velocity = attributes.getVelocity();
				Vector2d velocityStep = new Vector2d(velocity.x, velocity.y);
				velocityStep.scale(timeStep);
				position.sub(velocityStep);
				i.setPosition(position);

				// changes velocity
				Vector2d acceleration = attributes.getAcceleration();
				Vector2d accelerationStep = new Vector2d(acceleration.x,
						acceleration.y);
				accelerationStep.scale(timeStep);
				velocity.add(accelerationStep);

				// changes acceleration
				attributes.applyForces(timeStep);

				// check collisions
				Pane parentPane = supervisor.getInertialPane();

				Bounds container = parentPane.getLayoutBounds();
				Bounds object = i.getBounds();

				double leftX = container.getMinX();
				double rightX = container.getMaxX();
				double topY = container.getMinY();
				double bottomY = container.getMaxY();
				double objectLeftX = object.getMinX();
				double objectRightX = object.getMaxX();
				double objectTopY = object.getMinY();
				double objectBottomY = object.getMaxY();

				if (objectLeftX < leftX) {
					velocity.set(-velocity.x, velocity.y);
				}
				if (objectRightX > rightX) {
					velocity.set(-velocity.x, velocity.y);
				}
				if (objectTopY < topY) {
					velocity.set(velocity.x, -velocity.y);
				}
				if (objectBottomY > bottomY) {
					velocity.set(velocity.x, -velocity.y);
				}
			}
		}
	}

}
