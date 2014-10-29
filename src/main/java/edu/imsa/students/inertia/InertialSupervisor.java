package edu.imsa.students.inertia;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import edu.imsa.students.inertia.world.InertialWorld;
import edu.imsa.students.inertia.world.objects.InertialRectangle;
import edu.imsa.students.inertia.world.services.interactivity.InertialDragSetupService;

public class InertialSupervisor {

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
		InertialDragSetupService.setUpEnvironmentSettings(inertialPane);
	}

}
