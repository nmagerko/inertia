package edu.imsa.students.inertia;

import edu.imsa.students.inertia.world.InertialWorld;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InertialSupervisor {
	
	private InertialWorld supervisedWorld;

	boolean paused = true;
	@FXML
	ToggleButton play;
	@FXML
	ImageView image;
	@FXML
	Canvas canvas;
	@FXML
	Slider timeSlider;
	@FXML
	MenuItem copyMenuItem;
	@FXML
	MenuItem deleteMenuItem;
	@FXML
	MenuItem closeMenuItem;

	@FXML
	private void playPauseSimulation() {
		System.out.println("The play/pause button works.");
		// if (paused) {
		// image.setImage(new Image("src/main/resources/ui/img/pause.png"));
		// paused = false;
		// } else {
		// image.setImage(new Image("src/main/resources/ui/img/pause.png"));
		// paused = true;
		// }
	}

	@FXML
	private void handleNextButton() {
		System.out.println("This works");
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
	
	public InertialWorld getSupervisedWorld(){
		return this.supervisedWorld;
	}
	
	public void setSupervisedWorld(InertialWorld newSupervisedWorld){
		this.supervisedWorld = newSupervisedWorld;
	}

}
