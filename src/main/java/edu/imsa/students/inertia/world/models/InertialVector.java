package edu.imsa.students.inertia.world.models;

import java.io.Serializable;

import javafx.geometry.Point2D;

/**
 * Vector alias for JavaFX's Point2D
 * @author nmagerko
 *
 */
public class InertialVector extends Point2D {

	/**
	 * Constructs a 2D vector representation
	 * @param x	the x-component
	 * @param y	the y-component
	 */
	public InertialVector(double x, double y) {
		super(x, y);
	}
	

}
