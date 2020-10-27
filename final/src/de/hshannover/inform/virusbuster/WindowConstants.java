package de.hshannover.inform.virusbuster;

/**
 * Window Size information
 * @author Jannes Hachmer
 *
 */

public enum WindowConstants {
    WINDOW_HEIGHT(600), WINDOW_WIDTH(800);

    private int pxl;
    
    WindowConstants(int pxl) {
        this.pxl = pxl;
    }
    
    /**
     * 
     * @return Returns number of pixels
     */
    public int getPxl() {
        return pxl;
    }
}