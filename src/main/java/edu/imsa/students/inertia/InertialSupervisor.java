package edu.imsa.students.inertia;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import edu.imsa.students.inertia.services.interactivity.InertialDragSetupService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;

public abstract class InertialSupervisor {
	
	protected InertialWorld supervisedWorld = InertialWorld.getWorld();
	protected InertialBridge selectedObject = null;
	
	@FXML
	Pane inertialPane;
	
	public InertialWorld getSupervisedWorld() {
		return this.supervisedWorld;
	}
	
	public InertialBridge getSelectedObject() {
		return selectedObject;
	}

	public Pane getInertialPane() {
		return inertialPane;
	}
	
	public void setSupervisedWorld(InertialWorld newSupervisedWorld) {
		this.supervisedWorld = newSupervisedWorld;
	}

	public void setSelectedObject(InertialBridge selectedObject) {
		this.selectedObject = selectedObject;
	}

	public void setInertialPane(Pane inertialPane) {
		this.inertialPane = inertialPane;
	}
	
	public boolean hasSelectedObject(){
		return selectedObject != null;
	}
	
	public void setDragAndDropSettings() {
		InertialDragSetupService.setUpEnvironmentDrag(inertialPane);
	}
}
