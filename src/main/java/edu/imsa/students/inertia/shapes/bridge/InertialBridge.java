package edu.imsa.students.inertia.shapes.bridge;

import java.io.Serializable;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.shapes.InertialAttributes;

/**
 * The InertialBridge allows us to cross the
 * standard JavaFX shapes' components with
 * our own "inertial" components
 * All implementations should also implement
 * Serializable
 * 
 * @author nmagerko
 *
 */
public interface InertialBridge extends Serializable {
	
	// getters and setters for the InertialAttributes
	public void setInertialAttributes(InertialAttributes<? extends InertialBridge> updatedAttributes);
	public InertialAttributes<? extends InertialBridge> getInertialAttributes();
	
	// getters and setters for point-setting convenience
	public void setPosition(Point2d position);
	public Point2d getPosition();
	
	// getters and setters for the last Point of interaction
	public void setLastInteractionPoint(Point2d updatedInteractionPoint);
	public Point2d getLastInteractionPoint();
	
	// a way to clone the object
	public <T extends Shape & InertialBridge> T getRawClone();
	
	// a way to find the object's center
	public Point2d getCenter();
	
	// getter for the bounds of the object. used for intersection detection.
	public Bounds getBounds();
	
	//getter for the Shape component of each Bridge
	public Shape getShape();
}
