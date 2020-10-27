package de.hshannover.inform.virusbuster;

import java.io.IOException;

import de.hshannover.inform.virusbuster.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller Class for the Difficulty Menu scene
 * @author Jannes Hachmer
 *
 */

public class DifficultyMenuController {
    
    @FXML
    public AnchorPane root;
    
    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button mediumButton;
    
    @FXML
    private Label chooseDifficulty;

    /**
     * Starts the game on easy difficulty (9x9 field, 10 viruses)
     * @param event
     */
    @FXML
    void startGameEasy(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.GAME_EASY.getFxmlFile()));
            loader.setControllerFactory(c -> {
                return new Game(9,9,10);
            });
            root.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Starts the game on medium difficulty (16x16 field, 40 viruses)
     * @param event
     */
    @FXML
    void startGameMedium(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.GAME_MEDIUM.getFxmlFile()));
            loader.setControllerFactory(c -> {
                return new Game(16,16,40);
            });
            root.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game on hard difficulty (30x16 field, 99 viruses)
     * @param event
     */
    @FXML
    void startGameHard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.GAME_HARD.getFxmlFile()));
            loader.setControllerFactory(c -> {
                return new Game(16,30,99);
            });
            root.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
