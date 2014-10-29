package edu.imsa.students.inertia.world.objects;

import javafx.scene.shape.Circle;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.world.objects.concept.InertialAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObject;

public class InertialCircle extends InertialObject {

	private static final long serialVersionUID = -1115655886137636562L;
	
	private static final Double DEFAULT_MASS = new Double(1.0);
	private static final Vector2d DEFAULT_VELOCITY = new Vector2d(0, 0);
	private static final Point2d DEFAULT_POSITION = new Point2d(50.0, 50.0);
	private static final Double DEFAULT_RADIUS = new Double(50.0);

	private static InertialAttributes generateDefaultAttributes(){
		return new InertialAttributes(DEFAULT_MASS, DEFAULT_POSITION, DEFAULT_VELOCITY, DEFAULT_RADIUS);
	}
	
	private void initializeInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
		// we can also set up copy drag
	}
	
	public InertialCircle() {
		super(new Circle(), generateDefaultAttributes());
		
		this.updateDelegatedAttributes();
		this.initializeInteractivity();
	}
	
	public InertialCircle(InertialAttributes attributes) {
		super(new Circle(), attributes);
		
		this.updateDelegatedAttributes();
		this.initializeInteractivity();
	}	
	
	@Override
	public InertialCircle clone(){
		return new InertialCircle(this.delegatedAttributes);
	}
	
	@Override
	public void updateDelegatedAttributes() {
		Circle delegatedShapeProper = ((Circle) this.delegatedShape);
		delegatedShapeProper.setCenterX(delegatedAttributes.getPosition().x);
		delegatedShapeProper.setCenterX(delegatedAttributes.getPosition().y);
		
		delegatedShapeProper.setRadius(delegatedAttributes.getSize());
		
		delegatedShapeProper.setFill(delegatedAttributes.getColor());
	}
}
