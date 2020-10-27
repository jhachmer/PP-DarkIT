package de.hshannover.inform.virusbuster;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller Class for the Main Menu scene
 * @author Jannes Hachmer
 *
 */

public class MainMenuController {
	
    public Main main;
    
    public void setMain(Main main) {
    	this.main = main;
    }
	@FXML
	private AnchorPane root;
	
    @FXML
    private Button exitButton;

    @FXML
    private Button startButton;

    @FXML
    private Button helpButton;

    /**
     * Switches to the difficulty menu, when a new game is started
     * @param event
     */
    @FXML
    void openDifficulty(ActionEvent event) {
        try {
            root.getScene().setRoot(FXMLLoader.load(getClass().getResource(Scenes.DIFFICULTY_MENU.getFxmlFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Switches to the help menu, when the help button is pressed
     * @param event
     */
    @FXML
    void openHelp(ActionEvent event) {
        try {
            root.getScene().setRoot(FXMLLoader.load(getClass().getResource(Scenes.HELP_MENU.getFxmlFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns to game collection, when the user selects to exit the game
     * @param event
     */
    @FXML
    void exitGame(ActionEvent event) {
    	System.exit(-1);
    }  

}
