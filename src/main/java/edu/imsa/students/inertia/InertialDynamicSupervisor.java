package edu.imsa.students.inertia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.InertialAttributes;
import edu.imsa.students.inertia.shapes.InertialCircle;
import edu.imsa.students.inertia.shapes.InertialRectangle;
import edu.imsa.students.inertia.shapes.InertialTriangle;
import edu.imsa.students.inertia.shapes.InertialPentagon;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;

public class InertialDynamicSupervisor extends InertialSupervisor {

	@FXML
	InertialCircle circle;
	@FXML
	InertialTriangle triangle;
	@FXML
	InertialRectangle square;
	@FXML
	InertialPentagon pentagon;
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
	@FXML
	VBox chartContainerContent;
	
	/**
	 * Set up the control window in the InertialApplication
	 */
	private void setUpControls() {
		//set labels to initial values of the sliders
		massLabel.setText(Double.toString(massSlider.getValue())+ "kg");
		airLabel.setText(Double.toString(airSlider.getValue())+ "%");
		gravityLabel.setText(Double.toString(gravitySlider.getValue())+ "%");
		restitutionLabel.setText(Double.toString(restitutionSlider.getValue())+ "%");

		//Add listeners to all of the sliders for each of the labels
		massSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				Double massScalar = massSlider.getValue();
				massLabel.setText((Math.round(massScalar))+ "kg");
				InertialAttributes iA = selectedObject.getInertialAttributes();
				iA.setMass(massScalar);
				selectedObject.setInertialAttributes(iA);
			}
		});

		airSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				Double airScalar = airSlider.getValue();
				airLabel.setText((Math.round(airScalar))+ "%");
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
				gravityLabel.setText((Math.round(gravityScalar))+ "%");
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
						restitutionLabel.setText((Math.round(restitutionScalar))+ "%");
						InertialAttributes iA = selectedObject.getInertialAttributes();
						iA.setRestitutionScalar(restitutionScalar);
						selectedObject.setInertialAttributes(iA);
					}
				});
	}
	
	public void initialize() {
		this.setUpControls();
	}
	
	/**
	 * @param selectedObject
	 *            the selectedObject to set
	 */
	@Override
	public void setSelectedObject(InertialBridge selectedObject) {
		this.selectedObject = selectedObject;
		
		this.chartContainerContent.getChildren().clear();
		this.chartContainerContent.getChildren()
								  .add(selectedObject.getInertialAttributes()
							   						 .getPositionChart());
	}
	
	
}
