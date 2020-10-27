package de.hshannover.inform.virusbuster.model;

import de.hshannover.inform.virusbuster.Scenes;
import de.hshannover.inform.virusbuster.sound.Sound;
import de.hshannover.inform.virusbuster.sound.SoundFiles;
import de.hshannover.inform.virusbuster.view.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 * Class for the main game logic
 * Created when new game is started
 * 
 * @author Jannes Hachmer
 *
 */
public class Game {
    @FXML 
    public AnchorPane gameScene;
    //used to specify game grid position
    @FXML
    public Pane boardPane;
    @FXML
    public Label viruses;
    @FXML
    public Label tiles;
    
    private final Board board;
    
    private int virusCount = 0, tileCount = 0;
    
    /**
     * Creates a new game with given number of rows/columns/viruses
     * 
     * @param rows Number of rows of the game grid
     * @param columns Number of columns of the game grid
     * @param viruses Number of viruses present on the game grid
     */
    public Game(int rows, int columns, int viruses) {
        board = new Board(rows, columns, viruses);
    }
    
    /**
     * Initializes the field of tiles, viruses and the labels
     * for missing tiles/viruses on the game grid
     * Gets called, when a new Game FXML is loaded
     */
    public void initialize(){
        setUpGameGrid();
        Sound.play(SoundFiles.START.getSoundFilePath());
        //Return to main menu when Escape Button is pressed in a running game
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE == event.getCode())
                try {
                    AnchorPane root = FXMLLoader.load(getClass().getResource(Scenes.MAIN_MENU.getFxmlFile()));
                    gameScene.getScene().setRoot(root);
                }catch (IOException e){
                    e.printStackTrace();
                }
        });
        viruses.setText("Viruses: 0/" + board.getViruses());
        tiles.setText("Tiles: 0/" + board.getRows() * board.getColumns());
        gameScene.setCursor(Cursor.CROSSHAIR);
    }

    /**
     * Decides what happens when a tile is revealed
     * Either ends the game when tile is a virus,
     * or shows the Number of adjacent viruses
     * 
     * @param tile Tile Object that is revealed
     */
    private void reveal(Tile tile) {
        tile.disable();
        tileCounter();
        if (tile.hasVirus())
            gameover(false);
        else if (tile.getVirusValue() == 0)
            board.findAdjacentOf(tile).stream()
                .filter(adjTile -> !(adjTile.isDisabled() || adjTile.hasVirus()))
                .forEach(this::reveal);
    }

    /**
     * Changes flag status of a tile when right clicked
     * 
     * @param tile Selected tile
     */
    private void flag(Tile tile){
        virusCounter(!tile.hasFlag());
        tile.flag();
        if(tile.hasFlag()) {
            tile.setStyle(
                    "-fx-background-image: url('/de/hshannover/inform/virusbuster/files/umbrella2.png');" +
                    "-fx-background-size: cover;");
        } else {
            tile.setStyle(
                    "-fx-background-color: linear-gradient(#33cc00, #00cc33);");
        }
    }

    /**
     * Determines what happens if the player has finished the game
     * Shows Win or Loss screen and gives option to return to the main menu
     * 
     * @param won True if game is won, or false if game is lost
     */
    private void gameover(boolean won){
        try{
            AnchorPane root;
            if(!won && tileCount==1) {
                Sound.play(SoundFiles.EASTER_EGG.getSoundFilePath());
                root = FXMLLoader.load(getClass().getResource(Scenes.EASTER_EGG.getFxmlFile()));
            }else if(!won) {
                Sound.play(SoundFiles.LOSS.getSoundFilePath());
                root = FXMLLoader.load(getClass().getResource(Scenes.LOSS.getFxmlFile()));
            }
            else {
                Sound.play(SoundFiles.VICTORY.getSoundFilePath());
                root = FXMLLoader.load(getClass().getResource(Scenes.VICTORY.getFxmlFile()));
            }
            Timeline tl = new Timeline(new KeyFrame(Duration.seconds(3), event -> gameScene.getScene().setRoot(root)));
            tl.play();
            //Reveal all viruses and disable all tiles
            for(int i=0; i<board.getRows(); i++) {
                for(int j=0; j<board.getColumns(); j++) {
                    if(board.getTile(i, j).hasVirus()) {
                        board.getTile(i, j).setStyle(
                                "-fx-background-image: url('/de/hshannover/inform/virusbuster/files/virus.png');" +
                                "-fx-background-size: cover;"
                                );
                    }
                    board.getTile(i, j).setDisable(true);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates value of the virus counter label
     * 
     * @param inc True if tile was not yet flagged, False if tile was flagged
     */
    private void virusCounter(boolean inc){
        viruses.setText("Viruses: " + (inc ? ++virusCount : --virusCount) + "/" + board.getViruses());
    }

    /**
     * Updates value of the tile counter label
     */
    private void tileCounter(){
        tiles.setText("Tiles: "+ ++tileCount + "/" + board.getRows()*board.getColumns());
        if(gameWon())
            gameover(true);
    }

    /**
     * Checks if the game is won or lost
     * 
     * @return True if game is won, False if game is lost
     */
    private boolean gameWon(){
        return tileCount == board.getRows() * board.getColumns() - board.getViruses();
    }

    /**
     * Creates a (rows x columns) grid for all tiles
     * which is placed on the boardPane
     */
    private void setUpGameGrid(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(1));
        grid.setHgap(2);
        grid.setVgap(2);

        IntStream.range(0, board.getRows())
            .forEach(rows -> IntStream.range(0, board.getColumns())
                .forEach(columns -> grid.add(createTile(rows, columns), columns, rows)));
        //Add grid to underlying pane
        boardPane.getChildren().add(grid);
    }

    /**
     * Creates a single tile
     * and sets the performed action when mouse clicked
     * 
     * @param x X-Position of tile
     * @param y Y-Position of tile
     * @return Returns a single Tile Object
     */
    private Tile createTile(int x, int y){
        final Tile tile = board.getTile(x, y);
        tile.setOnMouseClicked(event -> {
            if(event.getButton()==MouseButton.PRIMARY)
                reveal(tile);
            else
                flag(tile);
        });
        return tile;
    }
    
}