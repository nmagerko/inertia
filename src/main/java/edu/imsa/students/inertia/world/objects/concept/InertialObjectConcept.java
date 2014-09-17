package edu.imsa.students.inertia.world.objects.concept;

import edu.imsa.students.inertia.setup.aliases.InertialVector;

/**
 * The inertia objects' "concept" ensures the following:
 * 1) All objects have a common way to set/get positions via vectors
 * 2) All attributes not provided by an InertialObjectConcept's
 *    parent is provided within the InertialObjectAttributes
 * 3) The InertialObjectAttributes instance is made only once per
 *    InertialObjectConcept implementation, and manipulated as needed
 *    
 * @author nmagerko
 *
 */
public interface InertialObjectConcept {
	public void setObjectPosition(InertialVector position);
	
	public InertialObjectAttributes getObjectAttributes();
	public InertialVector getObjectPosition();
}
