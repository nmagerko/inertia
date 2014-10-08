package edu.imsa.students.inertia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.imsa.students.inertia.utils.conf.InertialConfigurationManager;

public class Inertia {
	
	private static InertialConfigurationManager configurationManager;
	private static InertialArchitect architect;
	
	private static Logger logger = LogManager.getLogger(Inertia.class);

	public static void main(String[] args) {
		logger.info("Starting up");
		
		configurationManager = new InertialConfigurationManager();
		architect = new InertialArchitect(configurationManager);
		
		architect.initializeUI();
	}

}
