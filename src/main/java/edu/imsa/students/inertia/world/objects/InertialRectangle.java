package edu.imsa.students.inertia.world.objects;

import javafx.scene.shape.Rectangle;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.world.objects.concept.InertialAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObject;

public class InertialRectangle extends InertialObject {
	
	private static final long serialVersionUID = 4681262468133435896L;
	
	private static final Double DEFAULT_MASS = new Double(1.0);
	private static final Point2d DEFAULT_POSITION = new Point2d(50.0, 50.0);
	private static final Vector2d DEFAULT_VELOCITY = new Vector2d(0, 0);
	private static final Double DEFAULT_SIDE_LENGTH = new Double(100.0);
	
	private static InertialAttributes generateDefaultAttributes(){
		return new InertialAttributes(DEFAULT_MASS, DEFAULT_POSITION, DEFAULT_VELOCITY, DEFAULT_SIDE_LENGTH);
	}
	
	private void initializeInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
		// we can also set up copy drag
	}
	
	public InertialRectangle() {
		super(new Rectangle(), generateDefaultAttributes());
		
		this.updateDelegatedAttributes();
		this.initializeInteractivity();
	}
	
	public InertialRectangle(InertialAttributes attributes) {
		super(new Rectangle(), attributes);
		
		this.updateDelegatedAttributes();
		this.initializeInteractivity();
	}	
	
	@Override
	public InertialRectangle clone(){
		return new InertialRectangle(this.delegatedAttributes);
	}
	
	@Override
	public void updateDelegatedAttributes() {
		Rectangle delegatedShapeProper = ((Rectangle) this.delegatedShape);
		delegatedShapeProper.setX(delegatedAttributes.getPosition().x);
		delegatedShapeProper.setY(delegatedAttributes.getPosition().y);
		
		delegatedShapeProper.setWidth(delegatedAttributes.getSize());
		delegatedShapeProper.setHeight(delegatedAttributes.getSize());
		
		delegatedShapeProper.setFill(delegatedAttributes.getColor());
	}
}
