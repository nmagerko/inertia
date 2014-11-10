package edu.imsa.students.inertia;

public class InertialWorld {

	private static InertialWorld world = null;
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
}
