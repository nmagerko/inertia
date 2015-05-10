package edu.imsa.students.inertia.services.transfer;

import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.InertialApplication;
import edu.imsa.students.inertia.services.physics.force.InertialForce;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;

public class InertialCopyService {
	
	public static <T extends Shape & InertialBridge> void addObject(Pane environmentalPane, T newShape, Point2d newPosition) {
		// add appropriate global forces
		InertialWorld world = InertialApplication.getMainController().getSupervisedWorld();
		for(InertialForce force : world.getGlobalForces()) {
			newShape.getInertialAttributes().addForce(force);
		}
		
		newShape.setPosition(newPosition);
		newShape.setVisible(true);
		
		// add the shape to the pane
		environmentalPane.getChildren().add(newShape);
		environmentalPane.setVisible(true);
		world.addObject(newShape);
	}
	
	public static <T extends Shape & InertialBridge> void copyObject(Pane environmentalPane, T gestureSource, DragEvent event, Point2d mousePosition) {
		T newShape = ((T) gestureSource).getRawClone();		
		
		// set the shape's location to the location of the drag
		Double newX = event.getX() - mousePosition.x;
		Double newY = event.getY() - mousePosition.y;
		Point2d newPosition = new Point2d(newX, newY);
		
		addObject(environmentalPane, newShape, newPosition);
	}

}
