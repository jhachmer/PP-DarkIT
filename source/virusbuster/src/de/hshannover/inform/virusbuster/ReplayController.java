package de.hshannover.inform.virusbuster;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller Class for the Replay scene
 * @author Jannes Hachmer
 *
 */
public class ReplayController {
    @FXML
    Button replay;
    //Shown when game is lost
    @FXML
    Label gameOverLabel;
    //Shown when game is won
    @FXML
    Label youWonLabel;
    
    /**
     * Returns to Main Menu after a game is lost or won
     */
    public void playAgain(){
        try {
            replay.getScene().setRoot(FXMLLoader.load(getClass().getResource(Scenes.MAIN_MENU.getFxmlFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}