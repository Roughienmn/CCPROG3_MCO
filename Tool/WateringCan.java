package Tool;

import Farm.Tile;

public class WateringCan extends Tool{
    public WateringCan () {
        super();
        
        super.name = "Watering Can";
        super.id = 'W';
        super.cost = 0;
        super.xp = 0.5;
        super.tileReq = 3;
        super.tileChange = 3;
    }

    //add water to tile if tile is plowed 
    public void toolFunction (Tile tile){
        if(tile.getStatus() > 1) 
            tile.addWater();
    }
}
