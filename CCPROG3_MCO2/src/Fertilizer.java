/**
 * A class with the information of the Fertilizer Tool.
 * When used on a Plowed Tile with a Crop, it increases its fertilizer level by 1.
 */
public class Fertilizer extends Tool{
    /**
     * Constructor of the Fertilizer Tool.
     * This constructor creates an instance of the Fertilizer Tool.
     */
    public Fertilizer(){
        super("Fertilizer", 'F', 10, 4, 3);
    }

    /**
     * Checks if the Fertilizer Tool can be used on a Tile.
     * @param tile the tile that is being checked for compatibility
     * @return if the tile contains a Crop.
     */
    @Override
    public boolean tileCompatible(Tile tile){
        if(tile.hasCrop()) return true; 
        return false;
    }

    /**
     * If possible, increases the Fertilizer Level of a Tile by 1.
     * @param tile the tile whose fertilizer level will be increased
     * @return if fertilizer was successfully added.
     */
    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.addFertilizer();
            return 1;
        }
        return 0;
    }
}
