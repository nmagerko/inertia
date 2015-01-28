package edu.imsa.students.inertia.shapes;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialRectangle extends Rectangle implements InertialBridge {
	
	private static final long serialVersionUID = 4681262468133435896L;
	private static final Double DEFAULT_WIDTH = new Double(100.0);
	private static final Double DEFAULT_HEIGHT = new Double(100.0);
	
	private InertialAttributes attributes;
	private Point2d lastInteractionPoint;
	
	private void initializeMoveInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
	}
	
	private void initializeCopyInteractivity(){
		InertialDragSetupService.setUpObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectCopyDrag(this);
	}
	
	public InertialRectangle(){
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		this.attributes = new InertialAttributes();
		this.initializeCopyInteractivity();
	}
	
	public InertialRectangle(Point2d position, Double width, Double height){
		super(position.x, position.y, width, height);
		
		this.attributes = new InertialAttributes();
		this.initializeMoveInteractivity();
	}
	
	public InertialRectangle(Point2d position, Double width, Double height, Paint paint){
		super(position.x, position.y, width, height);
		this.setFill(paint);
		this.attributes = new InertialAttributes();
		this.initializeMoveInteractivity();
	}
	
	public InertialRectangle(Point2d position, Double width, Double height, InertialAttributes attributes) {
		super(position.x, position.y, width, height);
		
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
		this.lastInteractionPoint = updatedInteractionPoint;
		
	}

	@Override
	public Point2d getLastInteractionPoint() {
		return this.lastInteractionPoint;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public InertialRectangle getRawClone(){
		// the clone is returned without its parent's InertialAttributes
		return new InertialRectangle(new Point2d(this.getX(), this.getY()), this.getWidth(), this.getHeight(), this.getFill());
	}
}
