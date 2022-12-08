public class Pickaxe extends Tool{
    public Pickaxe(){
        super("Pickaxe", 'X', 50, 15, 0);
    }

    public int updateTile(Tile tile){
        if(this.tileCompatible(tile)){
            tile.resetTile();
            return 1;
        }
        return 0;
    }
}
