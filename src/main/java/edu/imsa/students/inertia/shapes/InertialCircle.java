package edu.imsa.students.inertia.shapes;


import javafx.scene.shape.Circle;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialCircle extends Circle implements InertialBridge  {

	private static final long serialVersionUID = -1115655886137636562L;
	private static final Double DEFAULT_RADIUS = new Double(50.0);

	private InertialAttributes attributes;
	private Point2d lastIneractionPoint;
	
	private void initializeInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
		// we can also set up copy drag
	}
	
	public InertialCircle(){
		super(DEFAULT_RADIUS);
		
		this.attributes = new InertialAttributes();
		this.initializeInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius){
		super(position.x, position.y, radius);
		
		this.attributes = new InertialAttributes();
		this.initializeInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius, InertialAttributes attributes) {
		super(position.x, position.y, radius);
		
		this.attributes = attributes;
		this.initializeInteractivity();
	}	

	@Override
	public void setInertialAttributes(InertialAttributes updatedAttributes) {
		this.attributes = updatedAttributes;
	}

	@Override
	public InertialAttributes getInertialAttributes() {
		return this.attributes;
	}
	
	@Override
	public void setPosition(Point2d position) {
		this.setCenterX(position.x);
		this.setCenterY(position.y);
	}

	@Override
	public Point2d getPosition() {
		return new Point2d(this.getCenterX(), this.getCenterY());
	}

	@Override
	public void setLastInteractionPoint(Point2d updatedInteractionPoint) {
		this.lastIneractionPoint = updatedInteractionPoint;
	}

	@Override
	public Point2d getLastInteractionPoint() {
		return this.lastIneractionPoint;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public InertialCircle getRawClone(){
		// the clone is returned without its parent's InertialAttributes
		return new InertialCircle(new Point2d(this.getCenterX(), this.getCenterY()), this.getRadius());
	}
}
