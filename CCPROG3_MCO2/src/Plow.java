public class Plow extends Tool{
    public Plow(){
        super("Plow", 'P', 0, 0.5, 1);
    }

    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.setStatus(2);
            return 1;
        }
        return 0;
    }
}
