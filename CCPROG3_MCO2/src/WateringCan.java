/**
 * A class with the information of the Watering Can Tool.
 * When used on a Plowed Tile with a Crop, it increases its water level by 1.
 */
public class WateringCan extends Tool{
    /**
     * Constructor of the Watering Can Tool.
     * This constructor creates an instance of the Watering Can Tool.
     */
    public WateringCan(){
        super("Watering Can", 'W', 0, 0.5, 3);
    }

    /**
     * Checks if the Watering Can Tool can be used on a Tile.
     * @param tile the tile that is being checked for compatibility
     * @return if the tile contains a Crop.
     */
    @Override
    public boolean tileCompatible(Tile tile){
        if(tile.hasCrop()) return true; 
        return false;
    }

    /**
     * If possible, increases the Water Level of a Tile by 1.
     * @param tile the tile whose water level will be increased
     * @return if water was successfully added.
     */
    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.addWater();
            return 1;
        }
        return 0;
    }
}
