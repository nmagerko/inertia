package edu.imsa.students.inertia.services.interactivity;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.copy.InertialCopyService;
import edu.imsa.students.inertia.world.objects.concept.InertialAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObject;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class InertialDragSetupService {
	
	public static final DataFormat MOUSE_DATA_FORMAT = new DataFormat("javax.vecmath.Point2d");
	
	private static void handleDragEvent(final Shape delegatedShape, final InertialAttributes delegatedAttributes, MouseEvent event){
		Dragboard dragBoard = delegatedShape.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		
		// add the rectangle object's attributes as well as
		// the mouse position on the rectangle to the dragboard
		content.put(MOUSE_DATA_FORMAT, delegatedAttributes.getLastInteractionSite());
		dragBoard.setContent(content);
		event.consume();
	}
	
	private static void handleCopyEvent(final Pane environmentalPane) {
		environmentalPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				Point2d mousePosition = (Point2d) dragBoard.getContent(MOUSE_DATA_FORMAT);
				InertialObject gestureSource = (InertialObject) event.getGestureSource();

				InertialCopyService.copyObject(environmentalPane,gestureSource, event, mousePosition);
				
				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(true);

				event.consume();
			}
		});
	}
	
	public static void setUpObjectOnMousePressed(final InertialObject inertialObject){
		final Shape delegatedShape = inertialObject.getDelegatedShape();
		delegatedShape.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Point2d currentPosition = inertialObject.getDelegatedAttributes().getPosition();
				inertialObject.getDelegatedAttributes().setLastInteractionSite(new Point2d(event.getX() - currentPosition.x, event.getY() - currentPosition.y));
			}
		});
	}
	
	public static void setUpObjectCopyDrag(final InertialObject inertialObject){
		final Shape delegatedShape = inertialObject.getDelegatedShape();
		final InertialAttributes delegatedAttributes = inertialObject.getDelegatedAttributes();
		delegatedShape.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleDragEvent(delegatedShape, delegatedAttributes, event);
			}
		});
	}
	
	public static void setUpObjectMoveDrag(final InertialObject inertialObject){
		final Shape delegatedShape = inertialObject.getDelegatedShape();
		final InertialAttributes delegatedAttributes = inertialObject.getDelegatedAttributes();
		delegatedShape.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleDragEvent(delegatedShape, delegatedAttributes, event);
			}
		});
	}
	
	public static void setUpEnvironmentDrag(final Pane environmentalPane){
		environmentalPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				Point2d mousePosition = (Point2d) dragBoard.getContent(MOUSE_DATA_FORMAT);
				InertialObject gestureSource = (InertialObject) event.getGestureSource();
				
				double x = event.getX() - mousePosition.x;
				double y = event.getY() - mousePosition.y;
				Point2d newPosition = new Point2d(x, y);
				
				InertialAttributes currentSourceAttributes = gestureSource.getDelegatedAttributes();
				currentSourceAttributes.setPosition(newPosition);
				gestureSource.updateDelegatedAttributes();
				
				if (event.getDragboard().hasContent(MOUSE_DATA_FORMAT) && event.getTransferMode() == TransferMode.COPY) {
					event.acceptTransferModes(TransferMode.COPY);
					handleCopyEvent(environmentalPane);
				} 
				// if we were accepting "moves," we could add the following
				/*else if (event.getDragboard().hasContent(InertialDragSetupService.MOUSE_DATA_FORMAT)
						&& event.getTransferMode() == TransferMode.MOVE) {
					event.acceptTransferModes(TransferMode.MOVE);
					moveMode();
				}*/

				event.consume();
			}
		});
	}

}
