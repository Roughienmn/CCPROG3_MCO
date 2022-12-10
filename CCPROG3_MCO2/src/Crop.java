import java.util.Random;

/**
 * An abstract class representing various Crops and Crop Types.
 * Gets planted in and grows on Tiles.
 */
public abstract class Crop {
    private String name; // This crop's name.
    private char id; // This crop's id "code"; single char.
    private int harvestTime; // This crop's time needed before harvesting in days.
    private int water; // This crop's water requirements.
    private int fertilizer; //This crop's fertilizer requirements.
    private int bonus; /// This crop's bonus limit.
    private int prodMin; // This crop's minimum number of products produced.
    private int prodMax; // This crop's maximum number of products produced.
    private int seedCost; // This crop's seed cost.
    private int sellPrice; // This crop's selling price.
    private double xp;// This crop's experience yield.
    private int growth; // The number of days since this crop was planted.

    /**
     * Constructor for class Crop; parameters define the crop's values for the variables declared above.
     * This constructor is used to create the different crops which have different properties/characteristics.
     * 
     * @param name          the name of the crop
     * @param id            the unique character id of the crop
     * @param harvestTime   the number of days required before the crop can be harvested
     * @param water         the amount of water needed by the crop
     * @param fertilizer    the amount of fertilizer needed by the crop
     * @param bonus         the bonus limit of the crop
     * @param prodMin       the minimum amount of produce a crop can make
     * @param prodMax       the maximum amount of a produce a crop can make
     * @param seedCost      the cost of planting the crop
     * @param sellPrice     the price of a crop's produce
     * @param xp            the experience yield of the crop
     */
    public Crop(String name, char id, int harvestTime, int water, int fertilizer, int bonus,
                int prodMin, int prodMax, int seedCost, int sellPrice, double xp){
        this.name = name;
        this.id = id;
        this.harvestTime = harvestTime;
        this.water = water;
        this.fertilizer = fertilizer;
        this.bonus = bonus;
        this.prodMin = prodMin;
        this.prodMax = prodMax;
        this.seedCost = seedCost;
        this.sellPrice = sellPrice;
        this.xp = xp;
        this.growth = 0;
    }

    /**
     * This method adds a day to the crop's growth.
     */
    public void grow(){
        this.growth++;
    }

    /**
     * This method sets the crop's growth value to -1, 
     * meaning that the crop has withered.
     */
    public void die(){
        this.growth = -1;
    }

    /**
     * Gets the crop's name.
     * @return this crop's name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the crop's "id".
     * @return this crop's char id.
     */
    public char getID(){
        return this.id;
    }

    /**
     * Gets the number of days required before the crop can be harvested.
     * @return this crop's harvest time.
     */
    public int getHarvestTime(){
        return this.harvestTime;
    }

    /**
     * Gets the water requirements of the crop.
     * @return this crop's water needs.
     */
    public int getWater(){
        return this.water;
    }

    /**
     * Gets the fertilizer requirements of the crop.
     * @return this crop's fertilizer needs.
     */
    public int getFertilizer(){
        return this.fertilizer;
    }

    /**
     * Gets the limit for the crop's water bonus.
     * @return this crop's water bonus limit by adding this crop's bonus to its water requirement.
     */
    public int getWaterLimit(){
        return this.water + this.bonus;
    }

    /**
     * Gets the limit for the crop's fertilizer bonus.
     * @return this crop's fertilizer bonus limit by adding this crop's bonus to its fertilizer requirement.
     */
    public int getFertLimit(){
        return this.fertilizer + this.bonus;
    }

    /**
     * Gets the crop's seed cost.
     * @return this crop's seed cost.
     */
    public int getSeedCost(){
        return this.seedCost;
    }

    /**
     * Gets the crop's selling price.
     * @return this crop's sell price.
     */
    public int getSellPrice(){
        return this.sellPrice;
    }

    /**
     * Gets the experience gained from the crop.
     * @return this crop's experience yield.
     */
    public double getXP(){
        return this.xp;
    }

    /**
     * Gets the crop's current days of growth.
     * @return this crop's current growth.
     */
    public int getGrowth(){
        return this.growth;
    }

    /**
     * Randomly generates the number of products produced by a crop.
     * Uses the crop's minimum and maximum number of produce as range.
     * @return this crop's number of produce between specified range.
     */
    public int generateProduce(){
        Random rand = new Random();

        return (rand.nextInt(this.prodMax - this.prodMin) + this.prodMin);
    }

    /**
     * Gets the crop's bonus limit.
     * @return this crop's bonus limit.
     */
    public int getBonus(){
        return this.bonus;
    }

    /**
     * Gets the crop's minimum number of products produced.
     * @return this crop's minimum amount of produce.
     */
    public int getProdMin(){
        return this.prodMin;
    }

    /**
     * Gets the crop's maximum number of products produced.
     * @return this crop's maximum amount of produce.
     */
    public int getProdMax(){
        return this.prodMax;
    }
}
