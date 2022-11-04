package Ver2;

import java.util.ArrayList;

public class Farmer {
    private ArrayList<Tool> toolList;
    private double xp;
    private int level;
    private double coins;
    private int registration;
    private int waterLimitIncrease;
    private int fertilizerLimitIncrease;
    private int bonusPerProduce;
    private int costReduction;
    private String regName;

    public Farmer(){
        this.xp = 0;
        this.level = 0;
        this.coins = 100;
        this.registration = 0;
        this.waterLimitIncrease = 0;
        this.fertilizerLimitIncrease = 0;
        this.bonusPerProduce = 0;
        this.costReduction = 0;
        this.toolList = new ArrayList<Tool>();
        this.regName = "";
    }

    public Tool getTool(char id){
        for(Tool t: this.toolList){
            if(t.getID() == id) return t;
        }
        return null;
    }

    public ArrayList<Tool> getToolList(){
        return this.toolList;
    }

    public int useTool(char id, Tile tile){
        Tool tool = this.getTool(id);
        if(tool.tileCompatible(tile) && this.canAfford(tool.getCost())){
            this.payCost(tool.getCost());
            this.addXp(tool.getXp());

            if(id == 'P'){
                tile.setStatus(2);
            }
            if(id == 'W'){
                tile.addWater();
            }
            if(id == 'F'){
                tile.addFertilizer();
            }
            if(id == 'S' || id == 'A'){
                tile.resetTile();
            }
            return 1;
        }
        return 0;
    }

    public int plantSeed(Crop seed, Tile tile){ //if tree, assume allowed to plant
        double totalCost = seed.getSeedCost() - this.costReduction;
        if(this.canAfford(totalCost) && tile.getStatus() == 2){
            this.payCost(totalCost);
            tile.addCrop(seed);
            return 1;
        }
        return 0;
    }

    public int harvestTile(Tile tile){
        if(tile.canHarvest()){//check if can harvest
            //get crop data
            Crop crop = tile.getCrop();
            int maxWater = crop.getWater() + crop.getBonus() + this.waterLimitIncrease;
            int maxFertilizer = crop.getFertilizer() + crop.getBonus() + this.fertilizerLimitIncrease;

            //compute income
            int produce = crop.generateProduce();
            double harvestTotal = produce * (crop.getSellPrice() + this.bonusPerProduce);
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater() - 1);
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer());

            //add to stats
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (crop.getType().equals("Flower")) finalHarvestPrice = finalHarvestPrice * 1.1;
            this.addCoins(finalHarvestPrice);
            this.addXp(crop.getXp());
            tile.resetTile();

            return produce; //successful
        }
        return 0; //unsuccessful
    }

    public int registerForLevel(int type){
        if(this.registration < type && type <= 3){
            double fee = (type + 1) * 100;

            if(this.level >= (type * 5) && this.canAfford(fee)){
                this.registration = type;
                this.payCost(fee);
                switch(type){
                    case 1:
                        this.bonusPerProduce = 1;
                        this.costReduction = 1;
                        this.regName = "Registered";
                    break;
                    case 2:
                        this.bonusPerProduce =2;
                        this.costReduction = 2;
                        this.waterLimitIncrease = 1;
                        this.regName = "Distinguised";
                    break;
                    case 3:
                        this.bonusPerProduce = 4;
                        this.costReduction = 3;
                        this.waterLimitIncrease = 2;
                        this.fertilizerLimitIncrease = 1;
                        this.regName = "Legendary";
                    break;
                }
                return 1; //successfully registered
            }
        }
        return 0; //unsuccessful register
    }

    public void addTool(Tool tool){
        toolList.add(tool);
    }

    public boolean canAfford(double cost){
        if(this.coins >= cost) return true;
        return false;
    }

    public void payCost(double cost){
        this.coins -= cost;
    }

    public void addXp(double xp){
        this.xp += xp;
        this.level = ((int) xp) / 100;
    }

    public void addCoins(double income){
        this.coins += income;
    }

    public double getXp(){
        return this.xp;
    }

    public int getLevel(){
        return this.level;
    }

    public double getCoins(){
        return this.coins;
    }

    public int getRegistration(){
        return this.registration;
    }

    public int getWaterLimit(){
        return this.waterLimitIncrease;
    }

    public int getFertLimit(){
        return this.fertilizerLimitIncrease;
    }

    public int getBonus(){
        return this.bonusPerProduce;
    }

    public int getCostReduction(){
        return this.costReduction;
    }

    public String getRegName(){
        return this.regName;
    }
}
