package edu.imsa.students.inertia;

import javax.vecmath.Point2d;

import edu.imsa.students.inertia.services.transfer.InertialCopyService;
import edu.imsa.students.inertia.shapes.InertialCircle;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class InertialSelectionSupervisor {

	private enum SimulationChoice {
		INTERACTIVE(InertialDynamicSupervisor.class) {
			@Override
			public String toString() {
				return "Interactive Mode";
			}
		},
		ORBITAL(InertialStaticSupervisor.class) {
			@Override
			public String toString() {
				return "Orbital Motion";
			}
		};

		private Class<?> associatedSupervisor;
		
		SimulationChoice(Class<?> associatedSupervisor) {
			this.associatedSupervisor = associatedSupervisor;
		}
		public Class<?> associatedSupervisor() {
			return associatedSupervisor;
		}
	}

	@FXML
	ListView<SimulationChoice> simulationChoices;

	private void addSimulationChoices() {
		ObservableList<SimulationChoice> choiceList = FXCollections.observableArrayList(SimulationChoice.values());
		simulationChoices.setItems(choiceList);
	}

	private void addSimulationChoicesListener() {
		simulationChoices.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Class<?> associatedSupervisor = simulationChoices.getSelectionModel().getSelectedItem().associatedSupervisor();
				try {
					InertialApplication.switchMainController(associatedSupervisor);
				} catch (Exception e) {
					System.err.println("An error occurred while trying to switch controllers");
				}
				
				if(simulationChoices.getSelectionModel().getSelectedItem().equals(SimulationChoice.ORBITAL)) {
					// TODO: find a cleaner solution to static object additions
					// TODO: fix drag service in static supervisor
					Pane environmentalPane = InertialApplication.getMainController().getInertialPane();
					InertialCircle centralObject = new InertialCircle();
					InertialCircle satelliteObject = new InertialCircle();
					Point2d centralObjectPosition = new Point2d(320.5, 322.0);
					Point2d satelliteObjectPosition = new Point2d(100.0, 322.0);
					centralObject.getInertialAttributes().setMass(500.0);
					
					InertialCopyService.addObject(environmentalPane, centralObject, centralObjectPosition);
					InertialCopyService.addObject(environmentalPane, satelliteObject, satelliteObjectPosition);
				}
			}
		});
	}

	public void initialize() {
		addSimulationChoices();
		addSimulationChoicesListener();
	}

}
