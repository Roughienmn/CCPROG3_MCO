package CCPROG3_MCO;

import java.util.ArrayList;

public class Farmer {
    private ArrayList<Tool> toolList; //tool list inventory
    private double xp; //farmer xp
    private int level; //farmer level
    private double coins; //farmer wallet
    private int registration; //farmer registration index
    private int waterLimitIncrease; //farmer water limit increase
    private int fertilizerLimitIncrease; //farmer fertilizer limit increase
    private int bonusPerProduce; //farmer bonus income per produce
    private int costReduction; //farmer seed cost reduction
    private String regName; //farmer registration "descriptor"

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

    //gets specific tool
    public Tool getTool(char id){
        for(Tool t: this.toolList){
            if(t.getID() == id) return t;
        }
        return null;
    }

    //returns list of tools
    public ArrayList<Tool> getToolList(){ 
        return this.toolList;
    }

    //uses tool on tile
    public int useTool(char id, Tile tile){
        Tool tool = this.getTool(id);
        if(tool.tileCompatible(tile) && this.canAfford(tool.getCost())){ //tool can be used on tile and farmer can afford cost of use
            this.payCost(tool.getCost());
            this.addXp(tool.getXp());

            if(id == 'P'){ //if plow, set tile status to plowed
                tile.setStatus(2);
            }
            if(id == 'W'){ //if tile is watered, add water to tile
                tile.addWater();
            }
            if(id == 'F'){ //if fertilizer is used, add fertilizer to tile
                tile.addFertilizer();
            }
            if(id == 'S' || id == 'A'){ //shovel and pickaxes set tile to unplowed with no water and fertilizer
                tile.resetTile();
            }
            return 1;
        }
        return 0;
    }

    //plants seed in tile
    public int plantSeed(Crop seed, Tile tile){ //if tree, assume allowed to plant
        double totalCost = seed.getSeedCost() - this.costReduction; //get total cost of planting
        if(this.canAfford(totalCost) && tile.getStatus() == 2){ //check if farmer can afford seed and tile is plowed
            this.payCost(totalCost);
            tile.addCrop(seed);
            return 1;
        }
        return 0;
    }

    //harvests tile
    public int harvestTile(Tile tile){
        if(tile.canHarvest()){//check if can harvest
            //get crop data
            Crop crop = tile.getCrop(); //get crop
            int maxWater = crop.getWaterLimit() + this.waterLimitIncrease; //get highest allowed amount of water
            int maxFertilizer = crop.getFertLimit() + this.fertilizerLimitIncrease; //get highest allowed amount of fertilizer

            //compute income
            int produce = crop.generateProduce(); //get number of produce generated (random)
            double harvestTotal = produce * (crop.getSellPrice() + this.bonusPerProduce);
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater() - 1);
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer());

            //add to stats
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (crop.getType().equals("Flower")) finalHarvestPrice = finalHarvestPrice * 1.1; //flower condition
            this.addCoins(finalHarvestPrice); //add objectcoins to money
            this.addXp(crop.getXp()); //xp
            tile.resetTile(); //resets tile to unplowed

            return produce; //successful
        }
        return 0; //unsuccessful
    }

    //registers for farmer type based on number equivalent
    public int registerForLevel(int type){
        if(this.registration < type && type <= 3){ //current registration is less than type registering for; type is also within registration types
            double fee = (type + 1) * 100;

            if(this.level >= (type * 5) && this.canAfford(fee)){ //level is sufficient for next type and farmer can afford
                this.registration = type; //change registration
                this.payCost(fee); //pay fee
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

    //add tool to farmer tool list
    public void addTool(Tool tool){
        toolList.add(tool);
    }

    //farmer can afford to pay for given fee
    public boolean canAfford(double cost){
        if(this.coins >= cost) return true;
        return false;
    }

    //subtract fee from current objectcoins
    public void payCost(double cost){
        this.coins -= cost;
    }

    //add xp to farmer
    public void addXp(double xp){
        this.xp += xp;
        this.level = ((int) xp) / 100;
    }

    //add income to wallet
    public void addCoins(double income){
        this.coins += income;
    }

    //returns current farmer xp
    public double getXp(){
        return this.xp;
    }

    //returns current farmer level
    public int getLevel(){
        return this.level;
    }

    //returns amount of objectcoins
    public double getCoins(){
        return this.coins;
    }

    //returns number equivalent of registration
    public int getRegistration(){
        return this.registration;
    }

    //returns water limit increase
    public int getWaterLimit(){
        return this.waterLimitIncrease;
    }

    //returns fertilizer limit increase
    public int getFertLimit(){
        return this.fertilizerLimitIncrease;
    }

    //returns bonus income per produce
    public int getBonus(){
        return this.bonusPerProduce;
    }

    //returns seed cost reduction
    public int getCostReduction(){
        return this.costReduction;
    }

    //returns registration type "descriptor"
    public String getRegName(){
        return this.regName;
    }
}
