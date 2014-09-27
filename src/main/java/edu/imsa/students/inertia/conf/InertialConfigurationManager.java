package edu.imsa.students.inertia.conf;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Builds and manages the default (base) configuration
 * files for the Inertia simulations
 * 
 * @author nmagerko
 *
 */
public class InertialConfigurationManager {
	
	private static final String PROJECT_CONF_PATH = "src/main/java/edu/imsa/students/inertia/conf";
	private static final String BASE_CONF_PATH = "/base/";
	private static final String LIVE_CONF_PATH = "/live/";
	private static final String PROJECT_CONF_FILE_SUFFIX = ".conf.xml";
	
	private static Map<String, XMLConfiguration> baseConfigurations = new HashMap<String, XMLConfiguration>();
	
	/**
	 * Converts all configuration files in the parameterized directory
	 * to their XMLConfiguration equivalents, and saves each in the baseConfigurations
	 * map
	 * @param baseConfigurationSource	<code>File</code> containing the base/default configurations
	 * @throws ConfigurationException
	 */
	private static void generateBaseConfigurations(File baseConfigurationSource) throws ConfigurationException{
		for(File potentialConfiguration : baseConfigurationSource.listFiles()){
			String absoluteFileName = potentialConfiguration.getName();
			
			// add only XML configurations to the baseConfigurations
			if(absoluteFileName.toLowerCase().contains(PROJECT_CONF_FILE_SUFFIX)){
				String normalFileName = potentialConfiguration.getName().substring(0, absoluteFileName.indexOf(PROJECT_CONF_FILE_SUFFIX)); 
				baseConfigurations.put(normalFileName, new XMLConfiguration(String.format("%s%s%s", PROJECT_CONF_PATH, BASE_CONF_PATH, absoluteFileName)));
			}
		}
	}
	
	static {
		File baseConfigurationFolder = new File(String.format("%s%s", PROJECT_CONF_PATH, BASE_CONF_PATH));
		try {
			generateBaseConfigurations(baseConfigurationFolder);
		} catch (ConfigurationException e) {
			// replace this with logger
			System.err.println("An error occurred while generating the base configurations:");
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves a configuration file in XML format
	 * @param identifier	the filename of the configuration, without the .conf.xml extension
	 * @return	the XMLConfiguration-equivalent of the configuration file, if found
	 */
	public XMLConfiguration getConfiguration(String identifier){
		return baseConfigurations.get(identifier);
	}
	
	/**
	 * Retrieves a configuration file that was saved during
	 * program execution in XML format
	 * @param identifier	the filename of the configuration, without the .conf.xml extension
	 * @return	the XMLConfiguration-equivalent of the configuration file, if found
	 */
	public XMLConfiguration getLiveConfiguration(String identifier){
		throw new UnsupportedOperationException("Accessing configurations that were saved live is unsupported");
	}
	
	/**
	 * Saves a configuration to the filesystem during execution
	 * @param identifier	the filename of the configuration, without the .conf.xml extension
	 * @param configurationData	the XMLConfiguration object to save
	 */
	public void setLiveConfiguration(String identifier, XMLConfiguration configurationData){
		throw new UnsupportedOperationException("Saving configurations live is not supported");
	}
}
