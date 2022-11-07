import java.util.ArrayList;

/**
 * This class represents the user/player.
 * Contains all player information and properties.
 */
public class Farmer {
    private ArrayList<Tool> toolList; /** This farmer's tool inventory. */
    private double xp; /** This farmer's experience (XP). */
    private int level; /** This farmer's level. */
    private double coins; /** This farmer's wallet; number of coins. */
    private int registration; /** This farmer's registration index. */
    private int waterLimitIncrease; /** This farmer's water limit increase. */
    private int fertilizerLimitIncrease; /** This farmer's fertilizer limit increase. */
    private int bonusPerProduce; /** This farmer's bonus coins per produce. */
    private int costReduction; /** This farmer's seed cost reduction. */
    private String regName; /** This farmer's registration title/type. */

     /**
     * Constructor for class Farmer.
     * This constructor already initializes this farmer's attributes.
     */
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

    /**
     * Gets specific tool according to input
     * @param id input tool id
     * @return tool if corresponding to id, null if input does not match any id.
     */
    public Tool getTool(char id){
        for(Tool t: this.toolList){
            if(t.getID() == id) return t;
        }
        return null;
    }

    /**
     * Gets the farmer's list of tools.
     * @return this farmer's tool list.
     */
    public ArrayList<Tool> getToolList(){ 
        return this.toolList;
    }

    /**
     * Uses tool on tile.
     * @param id the tool's id.
     * @param tile tile on which the tool is to be used on.
     * @return if this farmer can use tool or not.
     */
    public int useTool(char id, Tile tile){
        Tool tool = this.getTool(id);
        if(tool.tileCompatible(tile) && this.canAfford(tool.getCost())){ //If tool can be used on tile and farmer can afford cost of use.
            this.payCost(tool.getCost());
            this.addXp(tool.getXp());

            if(id == 'P'){ //If tile is plowed 'P', set tile status to plowed.
                tile.setStatus(2);
            }
            if(id == 'W'){ //If tile is watered 'W', add water to tile.
                tile.addWater();
            }
            if(id == 'F'){ //If fertilizer 'F' is used, add fertilizer to tile.
                tile.addFertilizer();
            }
            if(id == 'S' || id == 'A'){ //Shovel 'S' and pickaxes 'A' set tile to unplowed with no water and fertilizer.
                tile.resetTile();
            }
            return 1;
        }
        return 0;
    }

    /**
     * Plants crop seed in tile.
     * @param seed input crop's seed.
     * @param tile input tile on which seed is to be planted in.
     * @return if this farmer can plant the seed or not.
     */
    public int plantSeed(Crop seed, Tile tile){ //If tree, assume that it's allowed to plant.
        double totalCost = seed.getSeedCost() - this.costReduction; /** Gets the total cost of planting the seed. */
        if(this.canAfford(totalCost) && tile.getStatus() == 2){ //Checks if this farmer can afford seed and if the tile is plowed.
            this.payCost(totalCost);
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
        if(tile.canHarvest()){//Check if tile can already be harvested.
            //Gets crop data.
            Crop crop = tile.getCrop(); /** Gets and stores crop. */
            int maxWater = crop.getWaterLimit() + this.waterLimitIncrease; /** Highest allowed amount of water */
            int maxFertilizer = crop.getFertLimit() + this.fertilizerLimitIncrease; /** Highest allowed amount of fertilizer. */

            //Computes income.
            int produce = crop.generateProduce(); /** Number of produce; randomly generated. */
            double harvestTotal = produce * (crop.getSellPrice() + this.bonusPerProduce); /** Price from harvesting tile, not including bonuses. */
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater() - 1); /** This farmer's water bonus from harvesting tile. */
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer()); /** This farmer's fertilizer bonus from harvesting tile. */

            //Adds to stats.
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus; /** Final price from harvesting tile, including bonuses. */
            if (crop.getType().equals("Flower")) finalHarvestPrice = finalHarvestPrice * 1.1; //Sets price according to flower bonus/condition.
            this.addCoins(finalHarvestPrice); //Add objectcoins to money.
            this.addXp(crop.getXp()); //Adds gained XP.
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
        if(this.registration < type && type <= 3){ //If current registration is less than type registering for and if type is also within registration types.
            double fee = (type + 1) * 100;

            if(this.level >= (type * 5) && this.canAfford(fee)){ //If level is sufficient for next type and farmer can afford.
                this.registration = type; //Change registration.
                this.payCost(fee); //Pay fee.
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
     * Adds tool to this farmer's tool list.
     * @param tool tool to be added.
     */
    public void addTool(Tool tool){
        toolList.add(tool);
    }

    /**
     * Checks if the farmer can afford the given fee.
     * @param cost the given fee.
     * @return if this farmer can pay the cost or not.
     */
    public boolean canAfford(double cost){
        if(this.coins >= cost) return true;
        return false;
    }

    /**
     * Subtracts fee from current objectcoins.
     * @param cost given fee for certain actions.
     */
    public void payCost(double cost){
        this.coins -= cost;
    }

    /**
     * Adds to the farmer's current XP.
     * @param xp experience to be added.
     */
    public void addXp(double xp){
        this.xp += xp;
        this.level = ((int) xp) / 100;
    }

    /**
     * Adds income to wallet.
     * @param income number of coins to be added to the wallet.
     */
    public void addCoins(double income){
        this.coins += income;
    }

    /**
     * Gets current farmer experience.
     * @return this farmer's current XP.
     */
    public double getXp(){
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
    public int getRegistration(){
        return this.registration;
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
    public int getFertLimit(){
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
