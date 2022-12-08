public class WateringCan extends Tool{
    public WateringCan(){
        super("Watering Can", 'W', 0, 0.5, 3);
    }

    @Override
    public boolean tileCompatible(Tile tile){
        if(tile.hasCrop()) return true; 
        return false;
    }

    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.addWater();
            return 1;
        }
        return 0;
    }
}
