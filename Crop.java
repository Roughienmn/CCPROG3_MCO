import java.util.Random;

public class Crop {
    private String name; //name
    private char id; //single char id "code"
    private String type; //type of crop
    private int harvestTime; //in days
    private int water; //water requirements of crop
    private int fertilizer; //fertilizer requirements
    private int bonus; //bonus limit
    private int prodMin; //minimum produce
    private int prodMax; //maxmimum produce
    private int seedCost; //cost of seed
    private int sellPrice; //income from crop
    private double xp; //xp gain from crop
    private int growth; //days since planting
    private boolean tree; //is crop a tree

    public Crop(String name, char id, String type, int harvestTime, int water, int fertilizer, int bonus,
                int prodMin, int prodMax, int seedCost, int sellPrice, double xp){
        this.name = name;
        this.id = id;
        this.type = type;
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
        if(type.equals("Fruit Tree")) tree = true;
        else tree = false;
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
