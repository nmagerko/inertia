package edu.imsa.students.inertia;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

import edu.imsa.students.inertia.services.physics.force.IntergravitationalForce;
import edu.imsa.students.inertia.services.transfer.InertialCopyService;
import edu.imsa.students.inertia.shapes.InertialCircle;
import edu.imsa.students.inertia.shapes.InertialRectangle;
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
					Point2d centralObjectPosition = new Point2d(420.5, 161.0);
					Point2d satelliteObjectPosition = new Point2d(180.0, 161.0);
					InertialRectangle centralObject = new InertialRectangle(centralObjectPosition);
					InertialRectangle satelliteObject = new InertialRectangle(satelliteObjectPosition);
					centralObject.getInertialAttributes().setMass(5e14);
					satelliteObject.getInertialAttributes().setMass(5e10);
					satelliteObject.getInertialAttributes().setVelocity(new Vector2d(0,-10));
					
					centralObject.getInertialAttributes().addForce(new IntergravitationalForce());
					satelliteObject.getInertialAttributes().addForce(new IntergravitationalForce());
					
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
