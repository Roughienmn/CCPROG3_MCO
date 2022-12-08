package Tool;

import Farm.Tile;
public class Fertilizer extends Tool {
    public Fertilizer () {
        super();
        
        super.name = "Fertilizer";
        super.id = 'F';
        super.cost = 10;
        super.xp = 4;
        super.tileReq = 3;
        super.tileChange = 3;
    }

    @Override
    public void toolFunction(Tile tile){
        if(tile.getStatus() > 1) 
            tile.addFertilizer();
    }
}
