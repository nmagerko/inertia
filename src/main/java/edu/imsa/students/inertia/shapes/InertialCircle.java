package edu.imsa.students.inertia.shapes;


import javafx.geometry.Bounds;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialCircle extends Circle implements InertialBridge  {

	private static final long serialVersionUID = -1115655886137636562L;
	private static final Double DEFAULT_RADIUS = new Double(50.0);

	private InertialAttributes<InertialCircle> attributes;
	private Point2d lastIneractionPoint;

	
	private void initializeMoveInteractivity(){
		InertialDragSetupService.setUpMoveObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
	}
	
	private void initializeCopyInteractivity(){
		InertialDragSetupService.setUpCopyObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectCopyDrag(this);
	}
	
	public InertialCircle(){
		super(DEFAULT_RADIUS);
		
		this.attributes = new InertialAttributes<InertialCircle>(this);
		this.initializeCopyInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius){
		super(position.x, position.y, radius);
		
		this.attributes = new InertialAttributes<InertialCircle>(this);
		this.initializeMoveInteractivity();
	}
	
	public InertialCircle(Point2d position){
		super(position.x, position.y, DEFAULT_RADIUS);
		
		this.attributes = new InertialAttributes<InertialCircle>(this);
		this.initializeMoveInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius, Paint paint){
		super(position.x, position.y, radius);
		this.setFill(paint);
		this.attributes = new InertialAttributes<InertialCircle>(this);
		this.initializeMoveInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius, InertialAttributes<InertialCircle> attributes) {
		super(position.x, position.y, radius);
		
		this.attributes = attributes;
		this.initializeMoveInteractivity();
	}	

	@Override
	public void setInertialAttributes(InertialAttributes updatedAttributes) {
		this.attributes = updatedAttributes;
		this.attributes.setParent(this);
	}

	@Override
	public InertialAttributes<? extends InertialBridge> getInertialAttributes() {
		return this.attributes;
	}
	
	@Override
	public void setPosition(Point2d position) {
		this.setLayoutX(position.x);
		this.setLayoutY(position.y);
	}

	@Override
	public Point2d getPosition() {
		return new Point2d(this.getLayoutX(), this.getLayoutY());
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
		return new InertialCircle(new Point2d(this.getCenterX(), this.getCenterY()), this.getRadius(), this.getFill());
	}
	
	@Override
	public Bounds getBounds() {
		return getBoundsInParent();
	}

	@Override
	public Shape getShape() {
		return this;
	}
	
	@Override
	public Point2d getCenter() {
		return new Point2d(this.getLayoutX(), this.getLayoutY());
	}
	
}
