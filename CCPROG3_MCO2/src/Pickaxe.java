/**
 * A class with the information of the Pickaxe Tool.
 * When used on a Tile with a Rock, it removes said Rock.
 */
public class Pickaxe extends Tool{
    /**
     * Constructor of the Pickaxe Tool.
     * This constructor creates an instance of the Pickaxe Tool.
     */
    public Pickaxe(){
        super("Pickaxe", 'X', 50, 15, 0);
    }

    /**
     * If possible, resets the tile to an unplowed state.
     * @param tile the tile that the tool will be used on
     * @return 1 if the tile was pickaxed, 0 if not.
     */
    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.resetTile();
            return 1;
        }
        return 0;
    }
}
