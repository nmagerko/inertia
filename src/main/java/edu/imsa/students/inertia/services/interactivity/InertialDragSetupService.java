package edu.imsa.students.inertia.services.interactivity;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.InertialSupervisor;
import edu.imsa.students.inertia.services.transfer.InertialCopyService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import javafx.event.EventHandler;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class InertialDragSetupService {

	public static final DataFormat MOUSE_DATA_FORMAT = new DataFormat(
			"javax.vecmath.Point2d");

	/**
	 * Handles a mouse press on InertialObjects
	 * 
	 * @param inertialShape
	 *            - the object being clicked
	 * @param event
	 *            - the MouseEvent of the click
	 */
	private static <T extends Shape & InertialBridge> void handleMousePressedEvent(
			T inertialShape, MouseEvent event, boolean canSelect) {
		Point2d currentPosition = inertialShape.getPosition();

		// point of mouse press in reference of shape pressed
		inertialShape.setLastInteractionPoint(new Point2d(event.getSceneX()
				- currentPosition.x, event.getSceneY() - currentPosition.y));

		inertialShape.getInertialAttributes().setInDrag(true);
		inertialShape.getInertialAttributes().setVelocity(new Vector2d(0, 0));
		// removes attributes of last selected object
		if (canSelect) {
			if (InertialSupervisor.hasSelectedObject()) {
				InertialBridge selected = InertialSupervisor
						.getSelectedObject();
				Shape selectedShape = selected.getShape();
				selectedShape.setStroke(null);
				selectedShape.setFill(Color.STEELBLUE);

			}
			// sets inertialShape as the selected object
			InertialSupervisor.setSelectedObject(inertialShape);
			inertialShape.setFill(Color.AQUAMARINE);
		}
	}

	/**
	 * Handles the start of a copy event on an Inertial object.
	 * 
	 * @param inertialShape
	 *            - the shape being copied
	 * @param event
	 *            - the MouseEvent of the drag
	 */
	private static <T extends Shape & InertialBridge> void handleCopyDragEvent(
			T inertialShape, MouseEvent event) {
		Dragboard dragBoard = inertialShape.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();

		// add the shape object's attributes as well as
		// the mouse position on the shape to the dragboard
		Point2d lastInteractionPoint = inertialShape.getLastInteractionPoint();
		content.put(MOUSE_DATA_FORMAT, lastInteractionPoint);
		dragBoard.setContent(content);

		// Add an opaque snapshot to be shown as the user copies the objects
		Image snapshot = inertialShape.snapshot(null, null);
		dragBoard.setDragView(snapshot);
		dragBoard.setDragViewOffsetX(lastInteractionPoint.x);
		dragBoard.setDragViewOffsetY(lastInteractionPoint.y);

		event.consume();
	}

	/**
	 * Handles the beginning of a move-drag action.
	 * 
	 * @param inertialShape
	 *            - the shape being dragged
	 * @param event
	 *            - the MouseEvent of the drag
	 */
	private static <T extends Shape & InertialBridge> void handleMoveDragEvent(
			T inertialShape, MouseEvent event) {
		Dragboard dragBoard = inertialShape.startDragAndDrop(TransferMode.MOVE);
		ClipboardContent content = new ClipboardContent();

		// add the shape object's attributes as well as
		// the mouse position on the shape to the dragboard
		content.put(MOUSE_DATA_FORMAT, inertialShape.getLastInteractionPoint());
		dragBoard.setContent(content);
		event.consume();
	}

	/**
	 * Sets up the InertialPane for copying of objects
	 * 
	 * @param environmentalPane
	 *            - the Pane which will contain the objects
	 */
	private static <T extends Shape & InertialBridge> void handleCopyEvent(
			final Pane environmentalPane) {
		environmentalPane.setOnDragDropped(new EventHandler<DragEvent>() {
			@SuppressWarnings("unchecked")
			public void handle(DragEvent event) {
				Dragboard dragBoard = event.getDragboard();
				Point2d mousePosition = (Point2d) dragBoard
						.getContent(MOUSE_DATA_FORMAT);
				T gestureSource = (T) event.getGestureSource();

				InertialCopyService.copyObject(environmentalPane,
						gestureSource, event, mousePosition);

				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(true);

				event.consume();
			}
		});
	}

	/**
	 * Stops objects on click and sets them in "drag-mode."
	 * 
	 * @param inertialShape
	 */
	public static <T extends Shape & InertialBridge> void setUpCopyObjectOnMousePressed(
			final T inertialShape) {
		inertialShape.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				boolean canSelect = false;
				handleMousePressedEvent(inertialShape, event, canSelect);
			}
		});
		inertialShape.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				inertialShape.getInertialAttributes().setInDrag(false);
			}
		});
	}

	public static <T extends Shape & InertialBridge> void setUpMoveObjectOnMousePressed(
			final T inertialShape) {
		inertialShape.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				boolean canSelect = true;
				handleMousePressedEvent(inertialShape, event, canSelect);
			}
		});
		inertialShape.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				inertialShape.getInertialAttributes().setInDrag(false);
			}
		});
	}

	/**
	 * Sets up objects for copying
	 * 
	 * @param inertialShape
	 */
	public static <T extends Shape & InertialBridge> void setUpObjectCopyDrag(
			final T inertialShape) {
		inertialShape.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleCopyDragEvent(inertialShape, event);
			}
		});
	}

	/**
	 * Sets up objects for moving
	 * 
	 * @param inertialShape
	 */
	public static <T extends Shape & InertialBridge> void setUpObjectMoveDrag(
			final T inertialShape) {
		inertialShape.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				handleMoveDragEvent(inertialShape, event);
			}
		});
		inertialShape.setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				inertialShape.getInertialAttributes().setInDrag(false);
			}
		});
	}

	/**
	 * Allow objects to be dragged across pane, changing position and velocity
	 * of the objects while they're dragged.
	 * 
	 * @param environmentalPane
	 */
	public static <T extends Shape & InertialBridge> void setUpEnvironmentDrag(
			final Pane environmentalPane) {
		environmentalPane.setOnDragOver(new EventHandler<DragEvent>() {
			@SuppressWarnings("unchecked")
			public void handle(DragEvent event) {

				Dragboard dragBoard = event.getDragboard();
				Point2d mousePosition = (Point2d) dragBoard
						.getContent(MOUSE_DATA_FORMAT);
				T gestureSource = (T) event.getGestureSource();
				double x = event.getSceneX() - mousePosition.x;
				double y = event.getSceneY() - mousePosition.y;
				Point2d newPosition = new Point2d(x, y);

				// change velocity and position while moving
				Vector2d difference = new Vector2d(
						gestureSource.getPosition().x, gestureSource
								.getPosition().y);
				difference.sub(newPosition);
				difference.scale(3);

				gestureSource.setPosition(newPosition);
				gestureSource.getInertialAttributes().setVelocity(difference);

				if (event.getDragboard().hasContent(MOUSE_DATA_FORMAT)
						&& event.getTransferMode() == TransferMode.COPY) {
					event.acceptTransferModes(TransferMode.COPY);
					handleCopyEvent(environmentalPane);
				}
				// if we were accepting "moves," we could add the following
				/*
				 * else if
				 * (event.getDragboard().hasContent(InertialDragSetupService
				 * .MOUSE_DATA_FORMAT) && event.getTransferMode() ==
				 * TransferMode.MOVE) {
				 * event.acceptTransferModes(TransferMode.MOVE); moveMode(); }
				 */

				event.consume();
			}
		});
	}

}
