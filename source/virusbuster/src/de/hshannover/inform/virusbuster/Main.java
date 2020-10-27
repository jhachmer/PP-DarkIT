package de.hshannover.inform.virusbuster;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class for the VirusBuster game
 * @author Jannes Hachmer
 *
 */

public class Main extends Application {

	@SuppressWarnings("unused")
    private Stage primaryStage;
	
	@Override 
	public void start(Stage primaryStage) {
	    //Start game with the main menu
	    AnchorPane root = null;
        try {
            //Load Main Menu scene and custom font
            root = FXMLLoader.load(getClass().getResource(Scenes.MAIN_MENU.getFxmlFile()));
            Font.loadFont(getClass().getResourceAsStream("/de/hshannover/inform/virusbuster/view/fonts/Tourmaline-Bold.ttf"), 10);
            Font.loadFont(getClass().getResourceAsStream("/de/hshannover/inform/virusbuster/view/fonts/Tourmaline-Glitch.ttf"), 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getIcons().add(new Image("/de/hshannover/inform/virusbuster/files/virus.png"));
        primaryStage.setTitle("VirusBuster");
        primaryStage.setResizable(false);
        //Set window size as 600x800
        Scene scene = new Scene(root, WindowConstants.WINDOW_WIDTH.getPxl(), WindowConstants.WINDOW_HEIGHT.getPxl());
        primaryStage.setScene(scene);
        //Load .css stylesheet
        scene.getStylesheets().add(Main.class.getResource("view/style.css").toExternalForm());
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
