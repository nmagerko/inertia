package edu.imsa.students.inertia.world.objects;

import java.io.Serializable;
import java.util.Map;

import edu.imsa.students.inertia.InertialSupervisor;
import edu.imsa.students.inertia.world.models.InertialVector;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectConcept;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A dynamic representation of the Rectangle object used in JavaFX
 * 
 * @author nmagerko
 */
public class InertialRectangle extends Rectangle implements
		InertialObjectConcept, Cloneable, Serializable {

	private static final long serialVersionUID = -5549361709079598066L;
	private MouseData lastClick;
	private InertialObjectAttributes attributes;

	public InertialRectangle(InertialObjectAttributes attributes) {
		super();
		this.attributes = attributes;
		setWidth(attributes.getSize());
		setHeight(attributes.getSize());
		setFill(attributes.getColor());
		setupMousePresses();
		setUpMoveDrag();
	}

	public InertialRectangle() {
		super();
		this.attributes = new InertialObjectAttributes((double) 1,
				new InertialVector(0, 0), (double) 100, (Color)getFill());
		setupMousePresses();
		setUpCopyDrag();
	}

	private void setupMousePresses()
	{
		setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				lastClick = new MouseData(event.getX() - getX(),
						event.getY() - getY());
			}});
	}
	/*
	 * The default constructor will make a copyable InertialRectangle. This is
	 * because Any rectangles created with the default constructor were made
	 * through JavaFXML
	 */
	private void setUpCopyDrag() {
		setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				// add the rectangle object's attributes as well as
				// the mouse position on the rectangle to the dragboard
				content.put(InertialSupervisor.mouseDataFormat, lastClick);
				db.setContent(content);
				event.consume();
			}
		});
	}

	private void setUpMoveDrag() {
		setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				// add the rectangle object's attributes as well as
				// the mouse position on the rectangle to the dragboard
				content.put(InertialSupervisor.mouseDataFormat, lastClick);
				db.setContent(content);
				event.consume();
			}
		});
	}

	public InertialRectangle(double width, double height,
			InertialObjectAttributes attributes) {
		super(width, height);
		this.attributes = attributes;
	}

	public void setObjectPosition(InertialVector position) {
		this.setX(position.getX());
		this.setY(position.getY());
	}

	public InertialObjectAttributes getObjectAttributes() {
		return this.attributes;
	}

	public InertialVector getObjectPosition() {
		return new InertialVector(this.getX(), this.getY());
	}

}
