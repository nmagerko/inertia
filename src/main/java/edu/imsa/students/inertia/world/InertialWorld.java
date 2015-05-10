package edu.imsa.students.inertia.world;

import java.util.ArrayList;
import java.util.List;

import edu.imsa.students.inertia.services.physics.force.InertialForce;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialWorld {

	private static InertialWorld world = null;
	private static List<InertialForce> globalForces = new ArrayList<InertialForce>();
	private static List<InertialBridge> objects = new ArrayList<InertialBridge>();
	
	protected InertialWorld(){
		//not applicable
	}
	
	public static InertialWorld getWorld(){
		if(world == null)
		{
			world = new InertialWorld();
		}
		return world;
	}
	
	public void setGlobalForces(List<InertialForce> newForces) {
		globalForces.clear();
		globalForces.addAll(newForces);
	}
	
	public void addObject(InertialBridge object){
		objects.add(object);
	}
	
	public List<InertialForce> getGlobalForces() {
		return globalForces;
	}
	
	public List<InertialBridge> getObjects() {
		return objects;
	}
}
