public class Shovel extends Tool {
    public Shovel(){
        super("Shovel", 'S', 7, 2, 4);
    }

    @Override
    public boolean tileCompatible(Tile tile){
        return true;
    }

    public int updateTile(Tile tile){
        if(tile.getStatus() > 0)
            tile.resetTile();
        return 1;
    }
}
