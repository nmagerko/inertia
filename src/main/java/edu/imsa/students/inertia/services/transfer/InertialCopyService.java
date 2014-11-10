package edu.imsa.students.inertia.services.transfer;

import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.InertialWorld;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialCopyService {
	
	public static <T extends Shape & InertialBridge> void copyObject(Pane environmentalPane, T gestureSource, DragEvent event, Point2d mousePosition) {
		T newShape = ((T) gestureSource).getRawClone();
		
		// Set the Rectangle's location to the location of the drag
		Double newX = event.getX() - mousePosition.x;
		Double newY = event.getY() - mousePosition.y;
		Point2d newPosition = new Point2d(newX, newY);
		
		newShape.setPosition(newPosition);
		
		newShape.setVisible(true);
		// Add the rectangle to the pane
		environmentalPane.getChildren().add(newShape);
		environmentalPane.setVisible(true);
		InertialWorld.getWorld().addObject(newShape);
	}

}
