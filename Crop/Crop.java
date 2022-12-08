package Crop;
import java.util.Random;

public abstract class Crop {
    protected String name; //name
    protected char id; //single char id "code"
    protected String type; //type of crop
    protected int harvestTime; //in days
    protected int water; //water requirements of crop
    protected int fertilizer; //fertilizer requirements
    protected int bonus; //bonus limit
    protected int prodMin; //minimum produce
    protected int prodMax; //maxmimum produce
    protected int seedCost; //cost of seed
    protected int sellPrice; //income from crop
    protected double xp; //xp gain from crop
    protected int growth; //days since planting
    protected boolean tree; //is crop a tree

    public Crop(){
        this.growth = 0;
    }

    public void grow(){ //add day to growth
        this.growth++;
    }

    public void DIE(){ //kill plant
        this.growth = -1;
    }

    public String getName(){ //returns name of plant
        return this.name;
    }

    public char getID(){ //returns char id
        return this.id;
    }
    
    public String getType(){ //return type of plant
        return this.type;
    }

    public int getHarvestTime(){ //returns days required before harvesting
        return this.harvestTime;
    }

    public int getWater(){ //returns water needs
        return this.water;
    }

    public int getFertilizer(){ //returns fertilizer needs
        return this.fertilizer;
    }

    public int getWaterLimit(){ //returns cap for bonus
        return this.water + this.bonus;
    }

    public int getFertLimit(){
        return this.fertilizer + this.bonus;
    }

    public int getSeedCost(){ //returns seed cost
        return this.seedCost;
    }

    public int getSellPrice(){ //returns sell price
        return this.sellPrice;
    }

    public double getXp(){ //returns crop xp gain
        return this.xp;
    }

    public int getGrowth(){ //returns current crop growth
        return this.growth;
    }

    public int generateProduce(){ //return number of produce between specified range
        Random rand = new Random();

        return (rand.nextInt(this.prodMax - this.prodMin) + this.prodMin);
    }

    public boolean isTree(){ //returns if the the crop is a tree
        return tree;
    }

    public int getBonus(){ //returns bonus limit
        return this.bonus;
    }

    public int getProdMin(){ //returns minimum amount of produce
        return this.prodMin;
    }

    public int getProdMax(){ //returns maximum amount of produce
        return this.prodMax;
    }
}
