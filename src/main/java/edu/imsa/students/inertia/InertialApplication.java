package edu.imsa.students.inertia;

import java.net.URL;
import java.util.ArrayList;

import edu.imsa.students.inertia.services.configuration.InertialConfigurationService;
import edu.imsa.students.inertia.services.physics.InertialPhysicsService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.InertialWorld;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	
	private XMLConfiguration configuration;
	
	private static Logger logger = LogManager.getLogger(InertialApplication.class);
	
	/**
	 * Sets the default stage properties using the values
	 * provided in the application configuration
	 */
	private void configureDefaultStageProperties(Stage stage){
		stage.setWidth(configuration.getInt("ui.defaults.width"));
		stage.setHeight(configuration.getInt("ui.defaults.height"));
		stage.setTitle(configuration.getString("ui.defaults.title"));
	}
	
	private void configureDefaultWorldProprties(InertialWorld world){
		// TODO: complete World implementation then rewrite below
		// world.setGravitationalAcceleration(configuration.getDouble("world.defaults.gravitational-acceleration", 0));
		// world.setAirResistance(configuration.getDouble("world.defaults.air-resistance", 0));
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
		
		this.configuration = InertialConfigurationService.getConfiguration("application");
	}
	
	@Override
	public void start(Stage stage) throws InterruptedException {
		logger.info("Setting up graphical interface");
		
		// the loader, root, and scene are initialized in the following
		// order to ensure that the loader received by the controller
		// is never null
		FXMLLoader loader = new FXMLLoader();
		Parent root = configureSceneParent(loader);
		Scene scene = new Scene(root);
		final InertialWorld world = InertialWorld.getWorld();
		InertialSupervisor supervisor = loader.getController();
		
		// set default scene properties
		this.configureDefaultStageProperties(stage);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/inertia-icon-48.png")));
		stage.setScene(scene);
		
		// provide the supervisor with a world to manipulate
		this.configureDefaultWorldProprties(world);
		supervisor.setSupervisedWorld(world);
		stage.show();
		
		// setup drag-and-drop
		supervisor.setDragAndDropSettings();
		while(true){
			ArrayList<InertialBridge> objectList = world.getObjects();
			InertialPhysicsService.advance(objectList);
			wait(100);
		}
		
	}
	
	public static void main(String[] args) {
		logger.info("***Launching application***");
		InertialApplication.launch();
		logger.info("***Application closed***");
	}
}
