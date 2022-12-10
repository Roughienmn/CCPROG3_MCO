/**
 * This class represents the user/player.
 * Contains all player information and properties.
 */
public class Farmer {
    private double coins; // This farmer's wallet; number of coins.
    private int level; // This farmer's level.
    private double xp; // This farmer's experience (XP).
    private int farmertype; // This farmer's registration index.
    private int waterLimitIncrease; // This farmer's water limit increase.
    private int fertilizerLimitIncrease; // This farmer's fertilizer limit increase.
    private int bonusPerProduce; // This farmer's bonus coins per produce.
    private int costReduction; // This farmer's seed cost reduction.
    private String regName; // This farmer's registration title/type.

    /**
     * Constructor for class Farmer.
     * This constructor already initializes this farmer's attributes.
     */
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

    /**
     * Uses tool on tile.
     * @param tool the tool that will be used.
     * @param tile tile on which the tool is to be used on.
     * @return if the tool was successfully used on the tile or not.
     */
    public int useTool(Tool tool, Tile tile){
        //if the tool is compatible with the tile status and can be afforded
        if(tool.tileCompatible(tile) && this.canAfford(tool.getCost())){
            this.addXP(tool.getXP());
            this.payCoins(tool.getCost());
            tool.updateTile(tile);

            return 1;
        }
        return 0;
    }

    /**
     * Plants crop seed in tile.
     * @param seed input crop's seed.
     * @param tile input tile on which seed is to be planted in.
     * @return if this farmer successfully planted the seed or not.
     */
    public int plantSeed(Crop seed, Tile tile){
        double totalCost = seed.getSeedCost() - this.costReduction; // Gets the final cost of planting the seed.
        if(this.canAfford(totalCost) && tile.getStatus() == 2){ //Checks if this farmer can afford seed and if the tile is plowed.
            this.payCoins(totalCost);
            tile.addCrop(seed);
            return 1;
        }
        return 0;
    }

    /**
     * Harvests tile.
     * @param tile tile that contains the crop to be harvested.
     * @return if harvesting is successful or not.
     */
    public int harvestTile(Tile tile){
        if(tile.canHarvest()){//Check if tile can be harvested.
            //Gets crop data.
            Crop crop = tile.getCrop(); // Gets and stores crop.
            int maxWater = crop.getWaterLimit() + this.waterLimitIncrease; // Largest allowed amount of water
            int maxFertilizer = crop.getFertLimit() + this.fertilizerLimitIncrease; // Largest allowed amount of fertilizer.

            //Computes income.
            int produce = crop.generateProduce(); // Number of produce; randomly generated.
            double harvestTotal = produce * (crop.getSellPrice() + this.bonusPerProduce); // Price from harvesting tile, not including bonuses.
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater() - 1); // This farmer's water bonus from harvesting tile.
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer()); // This farmer's fertilizer bonus from harvesting tile.

            //Adds to stats.
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus; // Final price from harvesting tile, including bonuses.
            if (crop instanceof Flower) finalHarvestPrice = finalHarvestPrice * 1.1; //Sets price according to flower bonus/condition.
            this.addCoins(finalHarvestPrice); //Add objectcoins to money.
            this.addXP(crop.getXP()); //Adds gained XP.
            tile.resetTile(); //Resets tile to unplowed.

            return produce; //If harvesting is successful.
        }
        return 0; //If tile cannot be harvested.
    }

    /**
     * Registers for farmer type based on numerical equivalent,
     * and updates farmer attributes according to type.
     * @param type type/title this farmer will be registering for.
     * @return if registration is successful or not.
     */
    public int registerForLevel(int type){
        if(this.farmertype < type && type <= 3){ //If current registration is less than type registering for and if type is also within registration types.
            double fee = (type + 1) * 100;

            if(this.level >= (type * 5) && this.canAfford(fee)){ //If level is sufficient for next type and farmer can afford.
                this.farmertype = type; //Change registration.
                this.payCoins(fee); //Pay fee.
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
                return 1; //Farmer successfully registered.
            }
        }
        return 0; //Farmer cannot register.
    }

    /**
     * Checks if the farmer can afford the given fee.
     * @param cost the given fee.
     * @return if this farmer can pay the cost or not.
     */
    public boolean canAfford(double cost){
        if(this.coins >= cost) 
            return true;
        return false;
    }

    /**
     * Adds to the farmer's current XP.
     * @param xp experience to be added.
     */
    public void addXP(double xp){
        this.xp+=xp;
        this.level = ((int) xp) / 100; //Updates level according to gained XP.
    }

    /**
     * Adds income to wallet.
     * @param income number of coins to be added to the wallet.
     */
    public void addCoins(double income){
        this.coins += income;
    }

    /**
     * Subtracts fee from current objectcoins.
     * @param cost given fee for certain actions.
     */
    public int payCoins(double cost){
        if(this.canAfford(cost)){
            this.coins -= cost;
            return 1;
        }
        return 0;
    }

    /**
     * Gets current farmer experience.
     * @return this farmer's current XP.
     */
    public double getXP(){
        return this.xp;
    }

    /**
     * Gets current farmer level.
     * @return this farmer's current level.
     */
    public int getLevel(){
        return this.level;
    }

    /**
     * Gets the farmer's amount of objectcoins in "wallet".
     * @return this farmer's amount of objectcoins.
     */
    public double getCoins(){
        return this.coins;
    }

    /**
     * Gets the number of the registration type.
     * @return the number equivalent of the registration.
     */
    public int getFarmerType(){
        return this.farmertype;
    }

    /**
     * Gets the water limit increase according to the registration type.
     * @return the water limit increase.
     */
    public int getWaterLimit(){
        return this.waterLimitIncrease;
    }

    /**
     * Gets the fertilizer limit increase according to the registration type.
     * @return the fertilizer limit increase.
     */
    public int getFerLimit(){
        return this.fertilizerLimitIncrease;
    }

    /**
     * Gets the bonus income per produce according to the registration type.
     * @return the bonus earnings per produce.
     */
    public int getBonus(){
        return this.bonusPerProduce;
    }

    /**
     * Gets the cost reduced per seed according to the registration type.
     * @return the seed cost reduction.
     */
    public int getCostReduction(){
        return this.costReduction;
    }

    /**
     * Gets the farmer type/title according to the registration type.
     * @return this farmer's title/type.
     */
    public String getRegName(){
        return this.regName;
    }
}
