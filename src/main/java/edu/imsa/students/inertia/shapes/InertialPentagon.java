package edu.imsa.students.inertia.shapes;


import javafx.geometry.Bounds;
import javafx.scene.paint.Paint;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.shapes.regular.RegularPentagon;

public class InertialPentagon extends RegularPentagon implements InertialBridge  {

	private static final long serialVersionUID = -1512058523775658486L;
	private static final Double DEFAULT_SCALE = new Double(1.0);
	
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
	
	public InertialPentagon(){
		super();
		this.attributes = new InertialAttributes();
		this.initializeCopyInteractivity();
	}
	
	public InertialPentagon(Point2d position, Double scale){
		super(position.x, position.y, scale);
		
		this.attributes = new InertialAttributes();
		this.initializeMoveInteractivity();
	}
	
	public InertialPentagon(Point2d position, Double scale, Paint paint){
		super(position.x, position.y, scale);
		this.setFill(paint);
		this.attributes = new InertialAttributes();
		this.initializeMoveInteractivity();
	}
	
	public InertialPentagon(Point2d position, Double scale, InertialAttributes attributes) {
		super(position.x, position.y, scale);
		
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
		this.setX(position.x);
		this.setY(position.y);
	}

	@Override
	public Point2d getPosition() {
		return new Point2d(this.getX(), this.getY());
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
	public InertialPentagon getRawClone(){
		// the clone is returned without its parent's InertialAttributes
		return new InertialPentagon(new Point2d(this.getX(), this.getY()), DEFAULT_SCALE, this.getFill());
	}

	@Override
	public Bounds getBounds() {
		return getBoundsInParent();
	}
}
