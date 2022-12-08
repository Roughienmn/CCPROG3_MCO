package Tool;

import Farm.Tile;
public class Shovel extends Tool {
    public Shovel () {
        super();
        
        super.name = "Shovel";
        super.id = 'S';
        super.cost = 7;
        super.xp = 2;
        super.tileReq = 4;
        super.tileChange = 1;
    }

    @Override 
    public void toolFunction(Tile tile){
        tile.resetTile();
    }
}
