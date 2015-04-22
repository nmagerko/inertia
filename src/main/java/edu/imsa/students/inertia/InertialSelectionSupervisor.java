package edu.imsa.students.inertia;

import java.io.IOException;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class InertialSelectionSupervisor {

	private enum SimulationChoice {
		ORBITAL(InertialStaticSupervisor.class) {
			@Override
			public String toString() {
				return "Orbital Motion";
			}
		},
		INTERACTIVE(InertialDynamicSupervisor.class) {
			@Override
			public String toString() {
				return "Interactive Mode";
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
			}
		});
	}

	public void initialize() {
		addSimulationChoices();
		addSimulationChoicesListener();
	}

}
