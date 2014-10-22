package edu.imsa.students.inertia;

import edu.imsa.students.inertia.world.InertialWorld;
import edu.imsa.students.inertia.world.models.InertialVector;
import edu.imsa.students.inertia.world.objects.InertialEllipse;
import edu.imsa.students.inertia.world.objects.InertialRectangle;
import edu.imsa.students.inertia.world.objects.MouseData;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectAttributes;
import edu.imsa.students.inertia.world.objects.concept.InertialObjectConcept;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class InertialSupervisor {

	public static final DataFormat mouseDataFormat = new DataFormat(
			"edu.imsa.students.inertia.world.objects.MouseData");

	private InertialWorld supervisedWorld;

	@FXML
	Circle circle;
	@FXML
	InertialRectangle square;
	@FXML
	ToggleButton play;
	@FXML
	ImageView image;
	@FXML
	Pane inertialPane;
	@FXML
	AnchorPane upperDetailsPane;
	@FXML
	Slider timeSlider;
	@FXML
	MenuItem copyMenuItem;
	@FXML
	MenuItem deleteMenuItem;
	@FXML
	MenuItem closeMenuItem;

	@FXML
	private void tryPlaceCircle() {

	}

	@FXML
	private void tryPlaceSquare() {

	}

	@FXML
	private void playPauseSimulation() {
		if (play.getText().toLowerCase().equals("run")) {
			play.setText("RUNNING");
		} else {
			play.setText("RUN");
		}
	}

	@FXML
	private void handleNextButton() {
	}

	@FXML
	private void handlePreviousButton() {

		System.out.println("This works");
	}

	@FXML
	private void copy() {

	}

	@FXML
	private void delete() {
		System.out.println("This works");
	}

	@FXML
	private void closeProgram() {

		System.out.println("This works");
	}

	@FXML
	private void changeTime() {

		System.out.println("This works");
	}

	@FXML
	private void checkObjectsLocations() {

		System.out.println("This works");
	}

	@FXML
	private void checkObjectsDrag() {

		System.out.println("This works");
	}

	@FXML
	private void releaseObject() {
		System.out.println("This works");
	}

	public InertialWorld getSupervisedWorld() {
		return this.supervisedWorld;
	}

	public void setSupervisedWorld(InertialWorld newSupervisedWorld) {
		this.supervisedWorld = newSupervisedWorld;
	}

	/**
	 * Set up drag and drop between the objects.
	 */
	public void setDragAndDropSettings() {
		inertialPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				MouseData mousePosition = (MouseData) db
						.getContent(mouseDataFormat);
				InertialObjectConcept gestureSource = (InertialObjectConcept) event
						.getGestureSource();
				double x = event.getX() - mousePosition.getX();
				double y = event.getY() - mousePosition.getY();
				InertialVector newPosition = new InertialVector(x, y);
				gestureSource.setObjectPosition(newPosition);
				/*
				 * accept it only if it is not dragged from the same node and if
				 * it has a InertialRectangle data
				 */
				// System.out.println( event.getGestureSource());
				System.out.println(event.getGestureSource());
				if (event.getDragboard().hasContent(mouseDataFormat)
						&& event.getTransferMode() == TransferMode.COPY) {
					/* allow for only copying */
					event.acceptTransferModes(TransferMode.COPY);
					copyMode();
				} else if (event.getDragboard().hasContent(mouseDataFormat)
						&& event.getTransferMode() == TransferMode.MOVE) {
					event.acceptTransferModes(TransferMode.MOVE);
					moveMode();
				}

				event.consume();
			}
		});
	}

	private void copyMode() {
		inertialPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/*
				 * if there is InertialRectangle data on dragboard, read it and
				 * use it
				 */
				Dragboard db = event.getDragboard();
				MouseData mousePosition = (MouseData) db
						.getContent(mouseDataFormat);
				InertialObjectConcept gestureSource = (InertialObjectConcept) event
						.getGestureSource();
				switch (gestureSource.getClass().getName()) {
				case "edu.imsa.students.inertia.world.objects.InertialRectangle":
					copyRectangle(gestureSource, event, mousePosition);
					break;
				case "edu.imsa.students.inertia.world.objects.InertialEllipse":
					copyEllipse(gestureSource, event, mousePosition);
					break;
				}
				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(true);

				event.consume();
			}
		});
	}

	private void moveMode() {
		inertialPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/*
				 * if there is InertialRectangle data on dragboard, read it and
				 * use it
				 */
				Dragboard db = event.getDragboard();
				MouseData mousePosition = (MouseData) db
						.getContent(mouseDataFormat);
				InertialObjectConcept gestureSource = (InertialObjectConcept) event
						.getGestureSource();
				double x = event.getX() - mousePosition.getX();
				double y = event.getY() - mousePosition.getY();
				InertialVector newPosition = new InertialVector(x, y);
				gestureSource.setObjectPosition(newPosition);
				event.setDropCompleted(true);

				event.consume();
			}
		});
	}

	private void copyRectangle(InertialObjectConcept gestureSource,
			DragEvent event, MouseData mousePosition) {
		InertialRectangle newShape = new InertialRectangle(
				gestureSource.getObjectAttributes());
		// Set the Rectangle's location to the location of the drag
		newShape.setX(event.getX() - mousePosition.getX());
		newShape.setY(event.getY() - mousePosition.getY());
		newShape.setVisible(true);
		// Add the rectangle to the pane
		inertialPane.getChildren().add(newShape);
		inertialPane.setVisible(true);

	}

	private void copyEllipse(InertialObjectConcept gestureSource,
			DragEvent event, MouseData mousePosition) {
		// TODO Auto-generated method stub
		InertialEllipse newShape = new InertialEllipse(
				gestureSource.getObjectAttributes());
		// Set the Rectangle's location to the location of the drag
		newShape.setCenterX(event.getX() - mousePosition.getX());
		newShape.setCenterY(event.getY() - mousePosition.getY());
		newShape.setVisible(true);
		// Add the rectangle to the pane
		inertialPane.getChildren().add(newShape);
		inertialPane.setVisible(true);
	}

}
