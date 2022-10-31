package CCPROG3_MCO;
import java.util.ArrayList;

public class Farmer {
    private double xp;
    private int lvl;
    private double coins;
    private int registration;
    private int waterLimit;
    private int fertilizerLimit;
    private int bonusPerProduce;
    private int costReduction;
    

    public Farmer(){
        this.xp = 0;
        this.lvl = 0;
        this.coins = 100;
        this.registration = 0;
        this.waterLimit = 0;
        this.fertilizerLimit = 0;
        this.bonusPerProduce = 0;
        this.costReduction = 0;
    }

    public int useTool(Tool tool, Tile tile){
        if(tool.getTile() == tile.getStatus() || tool.getName().equals("shovel")){
            tile.updateStatus(tool.getNewTile());
            this.addXp(tool.getXp());

            if(tool.getName().equalsIgnoreCase("watering can")){
                tile.addWater();
            }
            if(tool.getName().equalsIgnoreCase("fertilizer")){
                tile.addFertilizer();
            }
            return 1; //successfully used tool
        }
        return 0; //cannot be used on tile
    }

    public void registerForLevel(int type){
        if(this.registration < type && type <= 3){
            double fee = (type+1) * 100;
            if(this.lvl >= (type*5) && this.coins >= fee){
                this.registration = type;
                this.coins-=fee;
                switch(type){
                    case 1:
                        this.bonusPerProduce = 1;
                        this.costReduction = 1;
                    break;
                    case 2:
                        this.bonusPerProduce = 2;
                        this.costReduction = 2;
                        this.waterLimit = 1;
                    break;
                    case 3:
                        this.bonusPerProduce = 4;
                        this.costReduction = 3;
                        this.waterLimit = 2;
                        this.fertilizerLimit = 1;
                    break;
                }
            }
        }
    }

    public int plantSeed(Tile tile, Plant plant){
        double totalCost = plant.getCost() - this.costReduction;
        if(this.coins >= totalCost){
            tile.addPlant(plant);
            this.coins-=totalCost;
            
            return 1;
        }
        return 0;
    }

    public int harvestTile(Tile tile){
        if(tile.isHarvestable()){
            Plant plant = tile.getPlant();
            int maxWater = plant.getWater() + plant.getBonus() + this.waterLimit;
            int maxFertilizer = plant.getFertilizer() + plant.getBonus() + this.fertilizerLimit;

            int produce = plant.getProduce();
            double harvestTotal = produce * (plant.getSellPrice() + this.bonusPerProduce);
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater()-1);
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer());

            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if(plant.getType().equals("flower")) finalHarvestPrice = finalHarvestPrice * 1.1;

            this.coins+=finalHarvestPrice;
            this.addXp(plant.getXp());
            return produce; //return number of produced items
        }
        return 0; //return 0 if not harvested
    }



    public void addXp(double xp){
        this.xp+=xp;
        this.lvl = ((int) this.xp)/100;
    }
}
