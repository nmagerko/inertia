package edu.imsa.students.inertia.services.interactivity;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.transfer.InertialCopyService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
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
	
	private static <T extends Shape & InertialBridge> void handleMousePressedEvent(T inertialShape, MouseEvent event){
		Point2d currentPosition = inertialShape.getPosition();
		inertialShape.setLastInteractionPoint(new Point2d(event.getSceneX() - currentPosition.x, 
														  event.getSceneY() - currentPosition.y));
	}
	
	private static <T extends Shape & InertialBridge> void handleCopyDragEvent(T inertialShape, MouseEvent event){
		Dragboard dragBoard = inertialShape.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		
		// add the shape object's attributes as well as
		// the mouse position on the shape to the dragboard
		content.put(MOUSE_DATA_FORMAT, inertialShape.getLastInteractionPoint());
		dragBoard.setContent(content);
		event.consume();
	}
	
	private static <T extends Shape & InertialBridge> void handleMoveDragEvent(T inertialShape, MouseEvent event){
		Dragboard dragBoard = inertialShape.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();
		
		// add the shape object's attributes as well as
		// the mouse position on the shape to the dragboard
		content.put(MOUSE_DATA_FORMAT, inertialShape.getLastInteractionPoint());
		dragBoard.setContent(content);
		event.consume();
	}
	
	private static <T extends Shape & InertialBridge> void handleCopyEvent(final Pane environmentalPane) {
		environmentalPane.setOnDragDropped(new EventHandler<DragEvent>() {
			@SuppressWarnings("unchecked")
			public void handle(DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				Point2d mousePosition = (Point2d) dragBoard.getContent(MOUSE_DATA_FORMAT);
				T gestureSource = (T) event.getGestureSource();

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
	
	public static <T extends Shape & InertialBridge> void setUpObjectOnMousePressed(final T inertialShape){
		inertialShape.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleMousePressedEvent(inertialShape, event);
			}
		});
	}
	
	public static <T extends Shape & InertialBridge> void setUpObjectCopyDrag(final T inertialShape){
		inertialShape.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleCopyDragEvent(inertialShape, event);
			}
		});
	}
	
	public static <T extends Shape & InertialBridge> void setUpObjectMoveDrag(final T inertialShape){
		inertialShape.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleMoveDragEvent(inertialShape, event);
			}
		});
	}
	
	public static <T extends Shape & InertialBridge> void setUpEnvironmentDrag(final Pane environmentalPane){
		environmentalPane.setOnDragOver(new EventHandler<DragEvent>() {
			@SuppressWarnings("unchecked")
			public void handle(DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				Point2d mousePosition = (Point2d) dragBoard.getContent(MOUSE_DATA_FORMAT);
				T gestureSource = (T) event.getGestureSource();
				double x = event.getSceneX() - mousePosition.x;
				double y = event.getSceneY() - mousePosition.y;
				Point2d newPosition = new Point2d(x, y);
				
				gestureSource.setPosition(newPosition);
				
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
