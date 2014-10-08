package edu.imsa.students.inertia;

import java.net.URL;

import edu.imsa.students.inertia.conf.InertialConfigurationManager;
import edu.imsa.students.inertia.world.InertialWorld;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point for the Inertia application
 * @author nmagerko
 *
 */
public class InertialApplication extends Application {
	
	private Integer DEFAULT_SCENE_WIDTH;
	private Integer DEFAULT_SCENE_HEIGHT;
	private String DEFAULT_SCENE_NAME;
	
	private InertialWorld world;
	
	private static Logger logger = LogManager.getLogger(InertialApplication.class);
	
	/**
	 * Sets the default stage properties using the values
	 * provided in the application configuration
	 * @param configuration	the application configuration
	 */
	private void configureDefaultStageProperties(XMLConfiguration configuration){
		this.DEFAULT_SCENE_WIDTH = configuration.getInteger("ui.defaults.width", null);
		this.DEFAULT_SCENE_HEIGHT = configuration.getInteger("ui.defaults.height", null);
		this.DEFAULT_SCENE_NAME = configuration.getString("ui.defaults.title");
	}
	
	/**
	 * Sets the properties of the Parent that will be used
	 * with the JavaFX Scene. The loader is manipulated to
	 * ensure that the Parent's controller class is never
	 * null
	 * @param loader	a Java FXML loader
	 * @return	a Scene parent
	 */
	private Parent configureSceneParent(FXMLLoader loader){
		Parent root = null;
		try {
			URL parentLocation = getClass().getResource("/ui/fxml/InertialUI.fxml");
			loader.setLocation(parentLocation);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			root = (Parent) loader.load(parentLocation.openStream());
		}
		catch (Exception e){
			logger.error("An error occurred while loading the interface properties", e);
		}
		
		return root;
	}
	
	public InertialApplication(){
		logger.info("Initializing application subsystems");
		
		InertialConfigurationManager configurationManager = new InertialConfigurationManager();
		this.world = new InertialWorld(configurationManager);
		this.configureDefaultStageProperties(configurationManager.getConfiguration("application"));
	}
	
	@Override
	public void start(Stage stage) {
		logger.info("Subsystem initialization complete. Displaying graphical interface");
		
		// the loader, root, and scene are initialized in the following
		// order to ensure that the loader received by the controller
		// is never null
		FXMLLoader loader = new FXMLLoader();
		Parent root = configureSceneParent(loader);
		Scene scene = new Scene(root);
		InertialSupervisor supervisor = loader.getController();
		
		// set default scene properties
		stage.setScene(scene);
		stage.setWidth(DEFAULT_SCENE_WIDTH);
		stage.setHeight(DEFAULT_SCENE_HEIGHT);
		stage.setTitle(DEFAULT_SCENE_NAME);
		
		// provide the supervisor with a world to manipulate
		supervisor.setSupervisedWorld(world);
		stage.show();
	}
	
	public static void main(String[] args) {
		logger.info("***Launching application***");
		InertialApplication.launch();
	}
}
