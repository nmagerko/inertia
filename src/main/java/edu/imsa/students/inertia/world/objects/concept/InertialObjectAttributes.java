package edu.imsa.students.inertia.world.objects.concept;

import java.io.Serializable;

import javafx.scene.paint.Color;
import edu.imsa.students.inertia.world.models.InertialVector;

/**
 * A POJO containing fields common to all 2D objects in the project. Position is
 * excluded to eliminate any asynchronous data-reporting issues between the
 * object and these attributes
 * 
 * @author nmagerko
 *
 */
public class InertialObjectAttributes {

	private Double mass;
	private InertialVector velocity;
	private Double size;
	private Color color;

	public InertialObjectAttributes() {

	}

	public InertialObjectAttributes(Double mass, InertialVector velocity) {
		this.mass = mass;
		this.velocity = velocity;
		this.size = (double) 50;
		this.color = Color.INDIGO;
	}

	public InertialObjectAttributes(Double mass, InertialVector velocity, Double size, Color color) {
		this.mass = mass;
		this.velocity = velocity;
		this.size = size;
		this.color = color;
	}
	
	public void setMass(Double mass) {
		this.mass = mass;
	}

	public void setVelocity(InertialVector velocity) {
		this.velocity = velocity;
	}

	public Double getMass() {
		return mass;
	}

	public InertialVector getVelocity() {
		return velocity;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
