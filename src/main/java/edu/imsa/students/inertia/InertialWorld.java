package edu.imsa.students.inertia;

import java.util.ArrayList;

import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialWorld {

	private static InertialWorld world = null;
	private ArrayList<InertialBridge> objects = new ArrayList<InertialBridge>();
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
	
	public void addObject(InertialBridge object){
		objects.add(object);
	}
	
	public ArrayList<InertialBridge> getObjects(){
		return objects;
	}
}
