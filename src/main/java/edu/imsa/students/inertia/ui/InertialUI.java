package edu.imsa.students.inertia.ui;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InertialUI extends Application {
	
	private static Integer DEFAULT_SCENE_WIDTH;
	private static Integer DEFAULT_SCENE_HEIGHT;
	private static String DEFAULT_SCENE_NAME;
	
	private static Logger logger = LogManager.getLogger(InertialUI.class);
	
	public InertialUI(){ }
	
	public InertialUI(XMLConfiguration configuration){
		InertialUI.DEFAULT_SCENE_WIDTH = configuration.getInteger("ui.defaults.width", 1450);
		InertialUI.DEFAULT_SCENE_HEIGHT = configuration.getInteger("ui.defaults.height", 815);
		InertialUI.DEFAULT_SCENE_NAME = configuration.getString("ui.defaults.name", "Inertia");
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/ui/fxml/InertialUI.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.setWidth(DEFAULT_SCENE_WIDTH);
		stage.setHeight(DEFAULT_SCENE_HEIGHT);
		
		stage.setTitle(DEFAULT_SCENE_NAME);
		stage.show();
	}
	
	public void initalize(){
		logger.info("Initalizing UI");
		
		Application.launch();
		logger.info("UI initialized");
	}
}
