package edu.imsa.students.inertia.shapes;


import javafx.geometry.Bounds;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.shapes.regular.RegularPentagon;

public class InertialPentagon extends RegularPentagon implements InertialBridge  {

	private static final long serialVersionUID = -1512058523775658486L;
	private static final Double DEFAULT_SCALE = new Double(1.0);
	
	private InertialAttributes<InertialPentagon> attributes;
	private Point2d lastInteractionPoint;
	private void initializeMoveInteractivity(){
		InertialDragSetupService.setUpMoveObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectMoveDrag(this);
	}
	
	private void initializeCopyInteractivity(){
		InertialDragSetupService.setUpCopyObjectOnMousePressed(this);
		InertialDragSetupService.setUpObjectCopyDrag(this);
	}
	
	public InertialPentagon(){
		super();
		this.attributes = new InertialAttributes<InertialPentagon>(this);
		this.initializeCopyInteractivity();
	}
	
	public InertialPentagon(Point2d position, Double scale){
		super(position.x, position.y, scale);
		
		this.attributes = new InertialAttributes<InertialPentagon>(this);
		this.initializeMoveInteractivity();
	}
	
	public InertialPentagon(Point2d position, Double scale, Paint paint){
		super(position.x, position.y, scale);
		this.setFill(paint);
		this.attributes = new InertialAttributes<InertialPentagon>(this);
		this.initializeMoveInteractivity();
	}
	
	public InertialPentagon(Point2d position, Double scale, InertialAttributes<InertialPentagon> attributes) {
		super(position.x, position.y, scale);
		
		this.attributes = attributes;
		this.initializeMoveInteractivity();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void setInertialAttributes(InertialAttributes<? extends InertialBridge> updatedAttributes) {
		this.attributes = (InertialAttributes<InertialPentagon>) updatedAttributes;		
	}

	@Override
	public InertialAttributes<InertialPentagon> getInertialAttributes() {
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

	@Override
	public Shape getShape() {
		return this;
	}
	
	@Override
	public Point2d getCenter() {
		throw new java.lang.UnsupportedOperationException("Implementation required");
	}
}
