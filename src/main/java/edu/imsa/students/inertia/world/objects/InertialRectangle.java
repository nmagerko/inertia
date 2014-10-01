package edu.imsa.students.inertia.world.objects;

import edu.imsa.students.inertia.utils.vectors.InertialVector;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectConcept;
import javafx.scene.shape.Rectangle;

/**
 * A dynamic representation of the Rectangle
 * object used in JavaFX
 * 
 * @author nmagerko
 */
public class InertialRectangle extends Rectangle implements InertialObjectConcept {
	
	private InertialObjectAttributes attributes;
	
	public InertialRectangle(InertialObjectAttributes attributes){
		super();
		this.attributes = attributes;
	}
	
	public InertialRectangle(double width, double height, InertialObjectAttributes attributes){
		super(width, height);
		this.attributes = attributes;
	}
	
	public void setObjectPosition(InertialVector position){
		this.setX(position.getX());
		this.setY(position.getY());
	}
	
	public InertialObjectAttributes getObjectAttributes(){
		return this.attributes;
	}
	
	public InertialVector getObjectPosition(){
		return new InertialVector(this.getX(), this.getY());
	}

}
