public class Fertilizer extends Tool{
    public Fertilizer(){
        super("Fertilizer", 'F', 10, 4, 3);
    }

    @Override
    public boolean tileCompatible(Tile tile){
        if(tile.hasCrop()) return true; 
        return false;
    }

    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.addFertilizer();
            return 1;
        }
        return 0;
    }
}
