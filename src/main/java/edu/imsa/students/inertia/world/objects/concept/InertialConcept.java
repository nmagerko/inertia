package edu.imsa.students.inertia.world.objects.concept;

import javafx.scene.shape.Shape;

public interface InertialConcept {
	
	public Shape getDelegatedShape();
	
	public InertialAttributes getDelegatedAttributes();
	public void updateDelegatedAttributes();
	
}
