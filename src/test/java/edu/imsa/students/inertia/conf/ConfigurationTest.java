package edu.imsa.students.inertia.conf;

import static org.junit.Assert.*;

import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {
	
	private InertialConfigurationManager configurationManager;
	private XMLConfiguration baseConfiguration;
	
	private final String APPLICATION_CONFIGURATION_KEY = "application";
	private final String UI_DEFAULT_WIDTH_KEY = "ui.defaults.width";
	private final String UI_DEFAULT_HEIGHT_KEY = "ui.defaults.height";
	
	private final Integer EXPECTED_DEFAULT_WIDTH = 750;
	private final Integer EXPECTED_DEFAULT_HEIGHT = 800;

	@Before
	public void setUp() throws Exception {
		this.configurationManager = new InertialConfigurationManager();
		this.baseConfiguration = configurationManager.getBaseConfiguration(APPLICATION_CONFIGURATION_KEY);
	}
	
	@Test
	public void testSimpleKeyRetrieval(){
		assertEquals(baseConfiguration.getInteger(UI_DEFAULT_WIDTH_KEY, null), EXPECTED_DEFAULT_WIDTH);
		assertEquals(baseConfiguration.getInteger(UI_DEFAULT_HEIGHT_KEY, null), EXPECTED_DEFAULT_HEIGHT);
	}

}
