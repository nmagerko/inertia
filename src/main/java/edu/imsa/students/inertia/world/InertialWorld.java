package edu.imsa.students.inertia.world;

import java.util.ArrayList;

import edu.imsa.students.inertia.shapes.bridge.InertialBridge;

public class InertialWorld {

	private static InertialWorld world = null;
	private static ArrayList<InertialBridge> objects = new ArrayList<InertialBridge>();
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
	
	public static void addObject(InertialBridge object){
		objects.add(object);
	}
	
	public static ArrayList<InertialBridge> getObjects(){
		return objects;
	}
}
