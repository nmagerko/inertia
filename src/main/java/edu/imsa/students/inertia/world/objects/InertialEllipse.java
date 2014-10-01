package edu.imsa.students.inertia.world.objects;

import edu.imsa.students.inertia.utils.vectors.InertialVector;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectConcept;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectAttributes;
import javafx.scene.shape.Ellipse;

/**
 * A dynamic representation of the Ellipse
 * object in JavaFX
 * 
 * @author nmagerko
 *
 */
public class InertialEllipse extends Ellipse implements InertialObjectConcept {
	
	private InertialObjectAttributes attributes;
	
	public InertialEllipse(InertialObjectAttributes attributes){
		super();
		this.attributes = attributes;
	}
	
	public InertialEllipse(double radiusX, double radiusY, InertialObjectAttributes attributes){
		super(radiusX, radiusY);
		this.attributes = attributes;
	}
	
	public InertialEllipse(double centerX, double centerY, double radiusX, double radiusY, InertialObjectAttributes attributes){
		super(centerX, centerY, radiusX, radiusY);
		this.attributes = attributes;
	}
	
	public void setObjectPosition(InertialVector position){
		this.setCenterX(position.getX());
		this.setCenterY(position.getY());
	}
	
	public InertialObjectAttributes getObjectAttributes(){
		return this.attributes;
	}
	
	public InertialVector getObjectPosition(){
		return new InertialVector(this.getCenterX(), this.getCenterY());
	}
}
