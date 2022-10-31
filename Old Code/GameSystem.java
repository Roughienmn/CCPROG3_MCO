package CCPROG3_MCO;
import java.util.ArrayList;

public class GameSystem {
    private ArrayList<Tool> toolList;
    private Farmer farmer;
    private Farm farm;
    private ArrayList<Plant> plantReferenceList;

    public GameSystem(ArrayList<Integer> status){
        this.toolList.add(new Tool("plow", 0, 0.5, 1, 2));
        this.toolList.add(new Tool("watering can", 0, 0.5, 3, 3));
        this.toolList.add(new Tool("fertilizer", 10, 4, 3, 3));
        this.toolList.add(new Tool("pickaxe", 50, 15, 0, 1));
        this.toolList.add(new Tool("shovel", 7, 2, 4, 1));

        farmer = new Farmer();
        farm = new Farm(status);
    }

    public int plantSeed(String name, int row, int col){
        Plant seed = new Plant(name);
        Tile tile = farm.getTile(row, col);
        if(tile.getStatus() == 2){
            if(seed.getType().equals("tree")){
                if(col != 1 && col != 5 && row != 1 && row != 10){
                    boolean surroundEmpty = true;
                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            int tileStatus = farm.getTile(row+i, col+j).getStatus();
                            if(tileStatus != 1 && tileStatus != 2) surroundEmpty = false;
                        }
                    }
                    if(!surroundEmpty) return 0;
                }
                else return 0;
            }
            tile.addPlant(seed);
        }
        return 0;
    }
}
