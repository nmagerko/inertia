package edu.imsa.students.inertia.world.objects.concept;

import javafx.scene.paint.Color;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

/**
 * A POJO containing fields common to all 2D objects in the project. Position is
 * excluded to eliminate any asynchronous data-reporting issues between the
 * object and these attributes
 * 
 * @author nmagerko
 *
 */
public class InertialAttributes {

	private static final Double DEFAULT_SIZE = 50.0;
	private static final Color DEFAULT_COLOR = Color.BLACK;

	/* Physical properties */
	private Double mass;
	private Double size;
	private Point2d position;
	private Vector2d velocity;
	private Color color;
	
	/* Interactivity properties */
	private Point2d lastInteractionSite;

	public InertialAttributes(Double mass, Point2d position, Vector2d velocity) {
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.size = InertialAttributes.DEFAULT_SIZE;
		this.color = InertialAttributes.DEFAULT_COLOR;
	}

	public InertialAttributes(Double mass, Point2d position, Vector2d velocity, Double size) {
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		this.color = InertialAttributes.DEFAULT_COLOR;
	}
	
	public InertialAttributes(Double mass, Point2d position, Vector2d velocity, Double size, Color color) {
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		this.color = color;
	}

	public void setMass(Double mass) {
		this.mass = mass;
	}
	
	public void setPosition(Point2d position){
		this.position = position;
	}

	public void setVelocity(Vector2d velocity) {
		this.velocity = velocity;
	}

	public Double getMass() {
		return mass;
	}

	public Vector2d getVelocity() {
		return velocity;
	}
	
	public Point2d getPosition(){
		return this.position;
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
	
	public Point2d getLastInteractionSite(){
		return this.lastInteractionSite;
	}
	
	public void setLastInteractionSite(Point2d lastInteractionSite){
		this.lastInteractionSite = lastInteractionSite;
	}

}
