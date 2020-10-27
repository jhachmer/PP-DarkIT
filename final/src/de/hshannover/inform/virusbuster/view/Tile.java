package de.hshannover.inform.virusbuster.view;


import javafx.geometry.Insets;
import javafx.scene.control.Button;

/**
 * Class for clickable tiles
 * @author Jannes Hachmer
 *
 */
public class Tile extends Button {
    private boolean isFlagged;
    private boolean isVirus;
    private final int x;
    private final int y;
    private int virusValue;

    Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.setPrefSize(23, 23);
        this.setMaxSize(23, 23);
        this.setPadding(Insets.EMPTY);
        //set id for CSS
        this.setId("tile");
    }

    /**
     * X-Position of Tile
     * 
     * @return X-Position of Tile
     */
    public int getX(){
        return x;
    }

    /**
     * Y-Position of Tile
     * 
     * @return Y-Position of Tile
     */
    public int getY(){
        return y;
    }

    /**
     * Gets value (number of adjacent viruses)
     * 
     * @return Number of adjacent viruses
     */
    public int getVirusValue(){
        return virusValue;
    }

    /**
     * Sets value (number of adjacent viruses)
     * 
     * @param n number of adjacent viruses
     */
    void setVirusValue(int n){
        virusValue = n;
    }

    /**
     * Change status of flag
     */
    public void flag(){
        isFlagged = !isFlagged;
    }

    /**
     * Checks if tile is flagged
     * 
     * @return Flag status of tile
     */
    public boolean hasFlag(){
        return isFlagged;
    }

    /**
     * Sets a tile as virus
     */
    void setVirus(){
        isVirus = true;
        virusValue = 9;
    }

    /**
     * Checks if tile is a virus
     * 
     * @return Returns true if a tile is a virus
     */
    public boolean hasVirus(){
        return isVirus;
    }

    /**
     * Disables a revealed tile and changes its status
     * depending on whether its a virus (red icon) or a normal tile(show value)
     */
    public void disable(){
        this.setDisable(true);

        if(isVirus) {
            this.setStyle(
                    "-fx-background-image: url('/de/hshannover/inform/virusbuster/files/virus.png');" +
                    "-fx-background-size: cover;"
                    );
        }
        else if (virusValue > 0) {
            this.setText(virusValue+"");
        }
    }
    
}