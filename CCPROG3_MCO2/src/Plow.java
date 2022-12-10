/**
 * A class with the information of the Plow Tool.
 * When used on an Unplowed Tile, the Tile becomes Plowed.
 */
public class Plow extends Tool{
    /**
     * Constructor of the Plow Tool.
     * This constructor creates an instance of the Plow Tool.
     */
    public Plow(){
        super("Plow", 'P', 0, 0.5, 1);
    }

    /**
     * If possible, turns an unplowed tile into a plowed tile.
     * @param tile the tile that will be plowed.
     * @return 1 if the Tile was plowed, 0 if not.
     */
    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.setStatus(2);
            return 1;
        }
        return 0;
    }
}
