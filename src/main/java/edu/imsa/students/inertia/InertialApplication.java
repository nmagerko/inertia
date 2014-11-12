package edu.imsa.students.inertia;

import java.net.URL;
import java.util.ArrayList;

import edu.imsa.students.inertia.services.configuration.InertialConfigurationService;
import edu.imsa.students.inertia.services.physics.InertialPhysicsService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.concurrent.Task;
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
		supervisor.inertialPane.setStyle("-fx-background-color: WHITESMOKE;\n"
				+ "-fx-border-color: LIGHTGRAY;\n"
				+ "-fx-border-width: 2;\n");
	
		//Animations set up
		final double updateInterval = 0.01;
		Timeline animator = new Timeline(new KeyFrame(Duration.seconds(updateInterval), new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
    			ArrayList<InertialBridge> objectList = InertialWorld.getObjects();
    			InertialPhysicsService.advance(objectList, 10*updateInterval);
		    }
		}));
		animator.setCycleCount(Timeline.INDEFINITE);
		animator.play();
		/**
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
            	System.out.println(now - lastUpdate.get());
                if (now - lastUpdate.get() > minUpdateInterval) {
        			ArrayList<InertialBridge> objectList = InertialWorld.getObjects();
        			InertialPhysicsService.advance(objectList);
                    lastUpdate.set(now);
                }
            }

        };

        timer.start();
            **/
		
	}
	
	public static void main(String[] args) {
		logger.info("***Launching application***");
		InertialApplication.launch();
		logger.info("***Application closed***");
	}
}
