package edu.imsa.students.inertia.utils.conf;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Builds and manages the default (base) configuration
 * files for the Inertia simulations
 * 
 * @author nmagerko
 *
 */
public class InertialConfigurationManager {
	
	private static final String PROJECT_CONF_PATH = "src/main/conf";
	private static final String SYSTEM_CONF_PATH = "/system/";
	private static final String ENV_CONF_PATH = "/environment/";
	private static final String PROJECT_CONF_FILE_SUFFIX = ".conf.xml";
	
	private static Logger logger = LogManager.getLogger(InertialConfigurationManager.class);
	private static Map<String, XMLConfiguration> systemConfigurations = new HashMap<String, XMLConfiguration>();
	
	/**
	 * Converts all configuration files in the parameterized directory
	 * to their XMLConfiguration equivalents, and saves each in the systemConfigurations
	 * map
	 * @param baseConfigurationSource	<code>File</code> containing the base/default configurations
	 * @throws ConfigurationException
	 */
	private static void generateSystemConfigurations(File systemConfigurationSource) throws ConfigurationException{
		for(File potentialConfiguration : systemConfigurationSource.listFiles()){
			String absoluteFileName = potentialConfiguration.getName();
			
			if(absoluteFileName.toLowerCase().contains(PROJECT_CONF_FILE_SUFFIX)){
				String normalFileName = potentialConfiguration.getName().substring(0, absoluteFileName.indexOf(PROJECT_CONF_FILE_SUFFIX)); 
				systemConfigurations.put(normalFileName, new XMLConfiguration(String.format("%s%s%s", PROJECT_CONF_PATH, SYSTEM_CONF_PATH, absoluteFileName)));
			}
		}
	}
	
	static {
		File systemConfigurationFolder = new File(String.format("%s%s", PROJECT_CONF_PATH, SYSTEM_CONF_PATH));
		try {
			logger.info("Initializing system configurations");
			generateSystemConfigurations(systemConfigurationFolder);
			
			logger.info("System configurations initialized");
		} catch (ConfigurationException e) {
			logger.error("An error occurred while generating the system configurations", e);
		}
	}
	
	/**
	 * Retrieves a configuration file in XML format
	 * @param identifier	the filename of the configuration, without the .conf.xml extension
	 * @return	the XMLConfiguration-equivalent of the configuration file, if found
	 */
	public XMLConfiguration getConfiguration(String identifier){
		return systemConfigurations.get(identifier);
	}
	
	/**
	 * Retrieves a configuration file that was saved during
	 * program execution in XML format
	 * @param identifier	the filename of the configuration, without the .conf.xml extension
	 * @return	the XMLConfiguration-equivalent of the configuration file, if found
	 */
	public XMLConfiguration getEnvironmentalConfiguration(String identifier){
		throw new UnsupportedOperationException("Accessing configurations that were saved live is unsupported");
	}
	
	/**
	 * Saves a configuration to the filesystem during execution
	 * @param identifier	the filename of the configuration, without the .conf.xml extension
	 * @param configurationData	the XMLConfiguration object to save
	 */
	public void setEnvironmentalConfiguration(String identifier, XMLConfiguration configurationData){
		throw new UnsupportedOperationException("Saving configurations live is unsupported");
	}
}
