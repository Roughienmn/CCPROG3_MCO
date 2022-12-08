package Tool;

import Farm.Tile;
public class Pickaxe extends Tool {
    public Pickaxe () {
        super();
        
        super.name = "Pickaxe";
        super.id = 'A';
        super.cost = 50;
        super.xp = 15;
        super.tileReq = 0;
        super.tileChange = 1;
    }

    @Override
    public void toolFunction(Tile tile){
        tile.setStatus(2);
    }
}
