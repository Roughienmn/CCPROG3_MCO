package Tool;

import Farm.Tile;
public class Plow extends Tool {
    public Plow () {
        super();
        
        super.name = "Plow";
        super.id = 'P';
        super.cost = 0;
        super.xp = 0.5;
        super.tileReq = 1;
        super.tileChange = 2;
    }

    @Override 
    public void toolFunction(Tile tile){
        tile.resetTile();
    }
}
