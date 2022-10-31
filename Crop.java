import java.util.Random;

public class Crop {
    private String name;
    private int harvestTime;
    private int water;
    private int fertilizer;
    private int bonusLimit;
    private int prodMin;
    private int prodMax;
    private int seedCost;
    private int sellPrice;
    private double xp;
    private int growth; //days in ground
    private String type;
    private int index;

    public Crop(String name, String type, int harvestTime, int water, int fertilizer, int bonusLimit, int prodMin,
                int prodMax, int seedCost, int sellPrice, double xp, int index){
        this.name = name;
        this.harvestTime = harvestTime;
        this.water = water;
        this.fertilizer = fertilizer;
        this.bonusLimit = bonusLimit;
        this.prodMin = prodMin;
        this.prodMax = prodMax;
        this.seedCost = seedCost;
        this.sellPrice = sellPrice;
        this.xp = xp;
        this.growth = 0;
        this.type = type;
    }

    public void grow(){
        this.growth++;
    }

    public void DIE(){//hehe
        this.growth = -1;
    }

    public int generateProduce(){
        Random rand = new Random();

        return rand.nextInt(this.prodMax - this.prodMin) + this.prodMin;
    }

    public String getName(){
        return this.name;
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

    public int getBonusLimit(){
        return this.bonusLimit;
    }

    public int getMinProduce(){
        return this.prodMin;
    }

    public int getMaxProduce(){
        return this.prodMax;
    }

    public int getSeedCost(){
        return this.seedCost;
    }

    public int getSellPrice(){
        return this.sellPrice;
    }

    public double getXp(){
        return this.xp;
    }
    
    public int getDaysAlive(){
        return this.growth;
    }

    public String getType(){
        return this.type;
    }

    public int getIndex(){
        return this.index;
    }
}
