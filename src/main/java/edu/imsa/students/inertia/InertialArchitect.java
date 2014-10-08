package edu.imsa.students.inertia;

import edu.imsa.students.inertia.ui.InertialUI;
import edu.imsa.students.inertia.utils.conf.InertialConfigurationManager;
import edu.imsa.students.inertia.world.InertialWorld;

public class InertialArchitect {
	
	private InertialUI ui;
	private InertialWorld world;
	
	public InertialArchitect(InertialConfigurationManager configurationManager){
		this.ui = new InertialUI(configurationManager.getConfiguration("application"));
		this.world = new InertialWorld();
	}
	
	public void initializeUI(){
		this.ui.initalize();
	}
}
