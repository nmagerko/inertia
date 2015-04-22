package edu.imsa.students.inertia;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import edu.imsa.students.inertia.services.configuration.InertialConfigurationService;
import edu.imsa.students.inertia.services.physics.InertialPhysicsService;
import edu.imsa.students.inertia.shapes.bridge.InertialBridge;
import edu.imsa.students.inertia.world.InertialWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
	
	private static AnimationTimer timer;
	private static Stage stage;
	private static InertialSupervisor supervisor;
	// private static XMLConfiguration configuration = InertialConfigurationService.getConfiguration("application");
	private static Logger logger = LogManager.getLogger(InertialApplication.class);
	
	private static URL fetchParentURL(Class<?> parentClass){
		URL parentLocation = null;
		try {
			if(parentClass == InertialSelectionSupervisor.class){
				parentLocation = InertialApplication.class.getClassLoader().getResource("ui/fxml/SelectionUI.fxml");
			} else if (parentClass == InertialStaticSupervisor.class){
				parentLocation = InertialApplication.class.getClassLoader().getResource("ui/fxml/StaticUI.fxml");
			} else if (parentClass == InertialDynamicSupervisor.class){
				parentLocation = InertialApplication.class.getClassLoader().getResource("ui/fxml/InteractiveUI.fxml");
			}
		} catch (Exception e) {
			logger.error("An error occurred while loading the interface properties", e);
		}
			
		return parentLocation;
	}

	/**
	 * Sets the properties of the Parent that will be used
	 * with the JavaFX Scene. The loader is manipulated to
	 * ensure that the Parent's controller class is never
	 * null
	 * @param loader	a Java FXML loader
	 * @return	a Scene parent
	 * @throws IOException 
	 */
	private static Parent configureSceneParent(FXMLLoader loader, Class<?> parentClass) throws IOException{
		URL parentLocation = fetchParentURL(parentClass);
		loader.setLocation(parentLocation);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
			
		return (Parent) loader.load(parentLocation.openStream());
	}
	
	public InertialApplication(){
		logger.info("Initialized application subsystems");
	}
	
	public static InertialSupervisor getMainController() {
		return supervisor;
	}
	
	public static void switchMainController(Class<?> newController) throws IOException {
		if (newController.getSuperclass() != InertialSupervisor.class) {
			throw new IllegalArgumentException("The new controller must be a subclass of InertialSupervisor");
		}
		
		FXMLLoader loader = new FXMLLoader();
		Parent root = configureSceneParent(loader, newController);
		Scene scene = new Scene(root);
		
		supervisor = loader.getController();
		supervisor.setDragAndDropSettings();
		// final InertialWorld world = InertialWorld.getWorld();
		// supervisor.setSupervisedWorld(world);
		
		stage.setScene(scene);	
		timer.start();
	}
	
	@Override
	public void start(Stage stage) throws InterruptedException, IOException {
		InertialApplication.class.getClassLoader();
		
		logger.info("Setting up graphical interface");
		InertialApplication.stage = stage;
		
		// the loader, root, and scene are initialized in the following
		// order to ensure that the loader received by the controller
		// is never null
		FXMLLoader loader = new FXMLLoader();
		Parent root = configureSceneParent(loader, InertialSelectionSupervisor.class);
		Scene scene = new Scene(root);
		
		// set default scene properties
		//stage.setTitle(configuration.getString("ui.defaults.title"));
		stage.setTitle("Inertia");
		stage.getIcons().add(new Image(InertialApplication.class.getClassLoader().getResourceAsStream("img/inertia-icon-48.png")));
		stage.setScene(scene);
		
		stage.show();
	
		timer = new AnimationTimer() {
			
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
		};
		
	}
	
	public static void main(String[] args) {
		logger.info("***Launching application***");
		InertialApplication.launch();
		logger.info("***Application closed***");
	}
}
