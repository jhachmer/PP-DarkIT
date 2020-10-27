package de.hshannover.inform.virusbuster.view;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Class for the game area
 * @author Jannes Hachmer
 *
 */
public class Board {
    private int rows;
    private int columns;
    private int viruses;
    private Tile[][] tiles;

    /**
     * Creates a new board, called by the game constructor
     * 
     * @param rows Number of rows on the game board
     * @param columns Number of columns on the game board
     * @param viruses Number of viruses 
     */
    public Board(int rows, int columns, int viruses) {
        this.rows = rows;
        this.columns = columns;
        this.viruses = viruses;
        this.tiles  = new Tile[rows][columns];
        generateGameTiles();
        addViruses();
        updateValues();
    }

    /**
     * Returns Tile at Position x,y
     * 
     * @param x X-Position of Tile
     * @param y Y-Position of Tile
     * @return Tile at Position x,y
     */
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getRows() {
        return this.rows;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public int getViruses() {
        return this.viruses;
    }
    
    /**
     * Creates a list of all tiles
     * 
     * @return Returns a List of all tiles
     */
    private LinkedList<Tile> createTileList() {
        LinkedList<Tile> tileList = new LinkedList<>();
        Arrays.stream(tiles).forEach(tiles -> tileList.addAll(Arrays.asList(tiles)));
        return tileList;
    }

    /**
     * Creates number of tiles depending on ROWS and COLUMNS values
     */
    private void generateGameTiles() {
        IntStream.range(0, rows)
            .parallel()
            .forEach(rows -> IntStream.range(0, columns)
                .parallel()
                .forEach(columns -> tiles[rows][columns] = new Tile(rows, columns)));
    }

    /**
     * Creates viruses on existing tiles until the number of viruses matches the value of VIRUSES
     */
    private void addViruses() {
        Random random = new Random();
        Set<List<Integer>> virusCoordinates = new HashSet<>(viruses);

        while (virusCoordinates.size() < viruses)
            virusCoordinates.add(Arrays.asList(random.nextInt(rows), random.nextInt(columns)));
                    
        for(List<Integer> tile : virusCoordinates)
            tiles[tile.get(0)][tile.get(1)].setVirus();
    }

    /**
     * Updates the value of tiles to show number of adjacent viruses when revealed
     */
    private void updateValues() {
        createTileList().stream().filter(tileWOVirus -> !tileWOVirus.hasVirus())
            .forEach(tile -> tile.setVirusValue((int) findAdjacentOf(tile).stream()
                .parallel()
                .filter(Tile::hasVirus)
                .count()));
    }

    /**
     * Returns list of tiles adjacent to given tile
     * 
     * @param tile Center Tile
     * @return List of adjacent tiles of center tile
     */
    public List<Tile> findAdjacentOf(Tile tile) {
        List<Tile> adjacentTiles = new LinkedList<>();
        int neighborTiles[] = {-1, 0, 0, -1, -1, -1, -1, 1, 1, -1, 1, 0, 0, 1, 1, 1};

        for(int i=0; i<neighborTiles.length; i+=2){
            int newX = tile.getX()+neighborTiles[i];
            int newY = tile.getY()+neighborTiles[i+1];

            if (newX >= 0 && newX < rows && newY >= 0 && newY < columns)
                adjacentTiles.add(tiles[newX][newY]);
        }
        return adjacentTiles;
    }
    
}