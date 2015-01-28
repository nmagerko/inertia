package edu.imsa.students.inertia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.InertialCircle;
import edu.imsa.students.inertia.shapes.InertialRectangle;
import edu.imsa.students.inertia.shapes.InertialTriangle;
import edu.imsa.students.inertia.shapes.InertialPentagon;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;

public class InertialSupervisor {

	private InertialWorld supervisedWorld;

	private static InertialBridge selectedObject;

	@FXML
	InertialCircle circle;
	@FXML
	InertialTriangle triangle;
	@FXML
	InertialRectangle square;
	@FXML
	InertialPentagon pentagon;
	@FXML
	private Pane inertialPane;
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
	Slider massSlider;
	@FXML
	Slider gravitySlider;
	@FXML
	Slider airSlider;
	@FXML
	Slider restitutionSlider;
	@FXML
	Label massLabel;
	@FXML
	Label airLabel;
	@FXML
	Label gravityLabel;
	@FXML
	Label restitutionLabel;

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
		InertialDragSetupService.setUpEnvironmentDrag(getInertialPane());
	}

	/**
	 * Returns the inertialPane
	 * 
	 * @return - inertialPane
	 */
	public Pane getInertialPane() {
		return inertialPane;
	}

	/**
	 * Sets the inertialPane
	 * 
	 * @param inertialPane
	 *            - the pane to be set as the inertialPane
	 */
	public void setInertialPane(Pane inertialPane) {
		this.inertialPane = inertialPane;
	}

	/**
	 * @return the selectedObject
	 */
	public static InertialBridge getSelectedObject() {
		return selectedObject;
	}

	/**
	 * @param selectedObject
	 *            the selectedObject to set
	 */
	public static void setSelectedObject(InertialBridge selectedObject) {
		InertialSupervisor.selectedObject = selectedObject;
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean hasSelectedObject(){
		if(selectedObject != null)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Set up the control window in the InertialApplication
	 */
	public void setUpControls() {
		//set labels to initial values of the sliders
		massLabel.setText(Double.toString(massSlider.getValue()));
		airLabel.setText(Double.toString(airSlider.getValue()));
		gravityLabel.setText(Double.toString(gravitySlider.getValue()));
		restitutionLabel.setText(Double.toString(restitutionSlider.getValue()));

		//Add listeners to all of the sliders for each of the labels
		massSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				Double massScalar = massSlider.getValue();
				massLabel.setText(Double.toString(massScalar));
				InertialAttributes iA = selectedObject.getInertialAttributes();
				iA.setMassScalar(massScalar);
				selectedObject.setInertialAttributes(iA);
			}
		});

		airSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				Double airScalar = airSlider.getValue();
				airLabel.setText(Double.toString(airScalar));
				InertialAttributes iA = selectedObject.getInertialAttributes();
				iA.setAirScalar(airScalar);
				selectedObject.setInertialAttributes(iA);
			}
		});
		gravitySlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				Double gravityScalar = gravitySlider.getValue();
				gravityLabel.setText(Double.toString(gravityScalar));
				InertialAttributes iA = selectedObject.getInertialAttributes();
				iA.setGravityScalar(gravityScalar);
				selectedObject.setInertialAttributes(iA);
			}
		});
		restitutionSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> arg0,
							Number arg1, Number arg2) {
						Double restitutionScalar = restitutionSlider.getValue();
						restitutionLabel.setText(Double.toString(restitutionScalar));
						InertialAttributes iA = selectedObject.getInertialAttributes();
						iA.setRestitutionScalar(restitutionScalar);
						selectedObject.setInertialAttributes(iA);
					}
				});
	}

}
