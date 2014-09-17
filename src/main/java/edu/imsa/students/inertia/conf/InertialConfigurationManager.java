package edu.imsa.students.inertia.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Manages the paths that point towards specific
 * configuration files
 * 
 * @author nmagerko
 *
 */
public class InertialConfigurationManager {
	private static Map<String, String> baseConfigurations = new HashMap<String, String>();
	private static Map<String, String> envConfigurations = new HashMap<String, String>();
	
	private static final String PROJECT_CONF_PREFIX = "src/main/java/edu/imsa/students/inertia/conf";
	
	static {
		/* Base configuration path assignment */
		baseConfigurations.put("application", String.format("%s/base/application.conf.xml", PROJECT_CONF_PREFIX));
		
		/* Env configuration path assignment */
		envConfigurations.put("falling", String.format("%s/env/falling.conf.xml", PROJECT_CONF_PREFIX));
	}
	
	/**
	 * Retrieves a configuration from the "base" configuration folder
	 * @param configurationIdentifier	pre-defined identifier for the configuration file
	 * @return	XML configuration
	 * @throws ConfigurationException 
	 */
	public XMLConfiguration getBaseConfiguration(String configurationIdentifier) throws ConfigurationException{
		return new XMLConfiguration(baseConfigurations.get(configurationIdentifier));
	}
	
	/**
	 * Retrieves a configuration from the "env" configuration folder
	 * @param configurationIdentifier	pre-defined identifier for the configuration file
	 * @return	XML configuration
	 * @throws ConfigurationException 
	 */
	public XMLConfiguration getEnvConfiguration(String configurationIdentifier) throws ConfigurationException{
		return new XMLConfiguration(envConfigurations.get(configurationIdentifier));
	}
}
