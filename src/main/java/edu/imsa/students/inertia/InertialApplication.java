package edu.imsa.students.inertia;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import edu.imsa.students.inertia.services.configuration.InertialConfigurationService;
import edu.imsa.students.inertia.services.physics.InertialPhysicsService;
import edu.imsa.students.inertia.services.physics.collision.CollisionDetectionService;
import edu.imsa.students.inertia.services.physics.collision.CollisionEvaluationService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	
	private static InertialSupervisor supervisor;
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
	
	public static InertialSupervisor getMainController() {
		return supervisor;
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
		supervisor = loader.getController();
		
		// set default scene properties
		this.configureDefaultStageProperties(stage);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/inertia-icon-48.png")));
		stage.setScene(scene);
		
		// provide the supervisor with a world to manipulate
		final InertialWorld world = InertialWorld.getWorld();
		this.configureDefaultWorldProprties(world);
		supervisor.setSupervisedWorld(world);
		stage.show();
		
		// setup drag-and-drop
		supervisor.setDragAndDropSettings();
		supervisor.setUpControls();
	
		new AnimationTimer() {
			
			private final double UPDATE_AFTER_SECONDS = 0.08;
			//Note: NANOSECONDS_PER_SECONDS only works when changed by a factor of 10. Should be 10e9
			private final double NANOSECONDS_PER_SECOND = 10e8;
			private long lastUpdate = 0;
			private int MAX_SECONDS_PER_GRAPH = 15;

		    @Override
		    public void handle(long now) {
    			ArrayList<InertialBridge> objectList = InertialWorld.getObjects();
    			InertialPhysicsService.advance(objectList);  
    			InertialPhysicsService.handleCollisions(objectList);
    			if((double)(now - lastUpdate)/NANOSECONDS_PER_SECOND > UPDATE_AFTER_SECONDS) {
    				InertialPhysicsService.updateCharts(objectList, (double) (now-lastUpdate)/NANOSECONDS_PER_SECOND, MAX_SECONDS_PER_GRAPH);
    				lastUpdate = now;
    			}
		    }
		}.start();
		
	}
	
	public static void main(String[] args) {
		logger.info("***Launching application***");
		InertialApplication.launch();
		logger.info("***Application closed***");
	}
}
