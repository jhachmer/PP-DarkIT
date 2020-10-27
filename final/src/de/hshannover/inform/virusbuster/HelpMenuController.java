package de.hshannover.inform.virusbuster;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Controller Class for the Help Menu scene
 * @author Jannes Hachmer
 *
 */

public class HelpMenuController {

    @FXML
    private BorderPane root;
    
    @FXML
    private Button backButton;
    
    /**
     * Steuerungserklärung
     */
    @FXML
    private AnchorPane leftBox;
    
    /**
     * Spielerklärung
     */
    @FXML
    private AnchorPane rightBox;

    /**
     * Returns to main menu when back button is pressed
     * @param event
     */
    @FXML
    void returnToMainMenu(ActionEvent event) {
        try {
            root.getScene().setRoot(FXMLLoader.load(getClass().getResource(Scenes.MAIN_MENU.getFxmlFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

