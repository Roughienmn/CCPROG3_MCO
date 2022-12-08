public class Farmer {
    private double coins;
    private int level;
    private double xp;
    private int farmertype;
    private int waterLimitIncrease;
    private int fertilizerLimitIncrease;
    private int bonusPerProduce;
    private int costReduction;
    private String regName;

    public Farmer(){
        this.coins = 100;
        this.xp = 0;
        this.level = 0;
        this.farmertype = 0;
        this.waterLimitIncrease = 0;
        this.fertilizerLimitIncrease = 0;
        this.bonusPerProduce = 0;
        this.costReduction = 0;
        this.regName = "";
    }

    public int useTool(Tool tool, Tile tile){
        if(tool.tileCompatible(tile) && this.canAfford(tool.getCost())){
            this.addXP(tool.getXP());
            this.payCoins(tool.getCost());
            tool.updateTile(tile);

            return 1;
        }
        return 0;
    }

    public int plantSeed(Crop seed, Tile tile){
        double totalCost = seed.getSeedCost() - this.costReduction;
        if(this.canAfford(totalCost) && tile.getStatus() == 2){
            this.payCoins(totalCost);
            tile.addCrop(seed);
            return 1;
        }
        return 0;
    }

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
            if (crop instanceof Flower) finalHarvestPrice = finalHarvestPrice * 1.1; //flower condition
            this.addCoins(finalHarvestPrice); //add objectcoins to money
            this.addXP(crop.getXP()); //xp
            tile.resetTile(); //resets tile to unplowed

            return produce; //successful
        }
        return 0; //unsuccessful
    }

    public int registerForLevel(int type){
        if(this.farmertype < type && type <= 3){ //current registration is less than type registering for; type is also within registration types
            double fee = (type + 1) * 100;

            if(this.level >= (type * 5) && this.canAfford(fee)){ //level is sufficient for next type and farmer can afford
                this.farmertype = type; //change registration
                this.payCoins(fee); //pay fee
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

    public boolean canAfford(double cost){
        if(this.coins >= cost) 
            return true;
        return false;
    }

    public void addXP(double xp){
        this.xp+=xp;
        this.level = ((int) xp) / 100;
    }

    public void addCoins(double income){
        this.coins += income;
    }

    public int payCoins(double cost){
        if(this.canAfford(cost)){
            this.coins -= cost;
            return 1;
        }
        return 0;
    }

    public double getXP(){
        return this.xp;
    }

    public int getLevel(){
        return this.level;
    }

    public double getCoins(){
        return this.coins;
    }

    public int getFarmerType(){
        return this.farmertype;
    }

    public int getWaterLimit(){
        return this.waterLimitIncrease;
    }

    public int getFerLimit(){
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
