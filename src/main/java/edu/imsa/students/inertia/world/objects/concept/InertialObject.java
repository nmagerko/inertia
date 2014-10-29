package edu.imsa.students.inertia.world.objects.concept;

import java.io.Serializable;

import javafx.scene.shape.Shape;

public abstract class InertialObject implements Cloneable, InertialConcept, Serializable {
	
	private static final long serialVersionUID = -2236801302630542221L;
	
	// the JavaFX shape that will be displayed on-screen
	protected Shape delegatedShape;
	// the attributes that are applied to the delegated shape
	protected InertialAttributes delegatedAttributes;
	
	// each derivative of the InertialObject will need to clone itself properly
	public abstract InertialObject clone();
	// each derivative of the InertialObject will want to update the delegatedShape differently
	public abstract void updateDelegatedAttributes();
	
	/**
	 * Initializes an InertialObject derivative
	 * @param delegatedShape		the shape to delegate to the object
	 * @param delegatedAttributes	the attributes to assign to the delegated shape
	 */
	public InertialObject(Shape delegatedShape, InertialAttributes delegatedAttributes){
		this.delegatedShape = delegatedShape;
		this.delegatedAttributes = delegatedAttributes;
	}
	
	public InertialAttributes getDelegatedAttributes(){
		return this.delegatedAttributes;
	}
	
	public Shape getDelegatedShape(){
		return this.delegatedShape;
	}
}
