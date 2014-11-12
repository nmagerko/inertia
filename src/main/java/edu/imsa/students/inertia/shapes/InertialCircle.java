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

	
	private void initializeMoveInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
	}
	
	private void initializeCopyInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectCopyDrag(this);
	}
	
	public InertialCircle(){
		super(DEFAULT_RADIUS);
		
		this.attributes = new InertialAttributes();
		this.initializeCopyInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius){
		super(position.x, position.y, radius);
		
		this.attributes = new InertialAttributes();
		this.initializeMoveInteractivity();
	}
	
	public InertialCircle(Point2d position, Double radius, InertialAttributes attributes) {
		super(position.x, position.y, radius);
		
		this.attributes = attributes;
		this.initializeMoveInteractivity();
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
		return new InertialCircle(new Point2d(this.getCenterX(), this.getCenterY()), this.getRadius());
	}
	
}
