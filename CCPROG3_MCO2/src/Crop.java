import java.util.Random;

public abstract class Crop {
    private String name;
    private char id;
    private int harvestTime;
    private int water;
    private int fertilizer;
    private int bonus;
    private int prodMin;
    private int prodMax;
    private int seedCost;
    private int sellPrice;
    private double xp;
    private int growth;

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

    public void grow(){
        this.growth++;
    }

    public void die(){
        this.growth = -1;
    }

    public String getName(){
        return this.name;
    }

    public char getID(){
        return this.id;
    }

    public int getHarvestTime(){
        return this.harvestTime;
    }

    public int getWater(){
        return this.water;
    }

    public int getFertilizer(){
        return this.fertilizer;
    }

    public int getWaterLimit(){
        return this.water + this.bonus;
    }

    public int getFertLimit(){
        return this.fertilizer + this.bonus;
    }

    public int getSeedCost(){
        return this.seedCost;
    }

    public int getSellPrice(){
        return this.sellPrice;
    }

    public double getXP(){
        return this.xp;
    }

    public int getGrowth(){
        return this.growth;
    }

    public int generateProduce(){
        Random rand = new Random();

        return (rand.nextInt(this.prodMax - this.prodMin) + this.prodMin);
    }

    public int getBonus(){
        return this.bonus;
    }

    public int getProdMin(){
        return this.prodMin;
    }

    public int getProdMax(){
        return this.prodMax;
    }
}
