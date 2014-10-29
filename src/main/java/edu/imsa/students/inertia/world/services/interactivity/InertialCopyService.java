package edu.imsa.students.inertia.world.services.interactivity;

import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.world.objects.concept.InertialAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObject;

public class InertialCopyService {
	
	public static void copyObject(Pane environmentalPane, InertialObject gestureSource, DragEvent event, Point2d mousePosition) {
		InertialObject newShape = gestureSource.clone();
		InertialAttributes newShapeAttributes = newShape.getDelegatedAttributes();
		
		// Set the Rectangle's location to the location of the drag
		Double newX = event.getX() - mousePosition.x;
		Double newY = event.getY() - mousePosition.y;
		Point2d newPosition = new Point2d(newX, newY);
		newShapeAttributes.setPosition(newPosition);
		
		newShape.getDelegatedShape().setVisible(true);
		// Add the rectangle to the pane
		environmentalPane.getChildren().add(newShape.getDelegatedShape());
		environmentalPane.setVisible(true);

	}

}
