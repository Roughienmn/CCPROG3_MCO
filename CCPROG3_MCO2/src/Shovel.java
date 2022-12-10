/**
 * A class with the information of the Shovel Tool.
 * If the tile is plowed, the Tile gets reset. Otherwise, nothing happens.
 */
public class Shovel extends Tool {
    /**
     * Constructor of the Shovel Tool.
     * This constructor creates an instance of the Shovel Tool.
     */
    public Shovel(){
        super("Shovel", 'S', 7, 2, 4);
    }

    /**
     * Checks if the Shovel Tool can be used on a Tile.
     * @param tile the tile that is being checked for compatibility
     * @return if the tile is compatible.
     */
    @Override
    public boolean tileCompatible(Tile tile){
        return true;
    }

    /**
     * If possible, resets the tile to an unplowed state.
     * @param tile the tile that will be reset.
     * @return 1.
     */
    public int updateTile(Tile tile){
        if(tile.getStatus() > 0)
            tile.resetTile();
        return 1;
    }
}
