package Ver2;

import java.util.Random;

public class Crop {
    private String name; //name
    private char id; //single char id "code"
    private String type; 
    private int harvestTime; //in days
    private int water;
    private int fertilizer;
    private int bonus; //
    private int prodMin;
    private int prodMax;
    private int seedCost;
    private int sellPrice;
    private double xp;
    private int growth; //days since planting
    private boolean tree;

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

    public String getName(){
        return this.name;
    }

    public char getID(){ //returns char id
        return this.id;
    }
    
    public String getType(){ //return type of plant
        return this.type;
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

    public double getXp(){
        return this.xp;
    }

    public int getGrowth(){
        return this.growth;
    }

    public int generateProduce(){ //return number of produce between specified range
        Random rand = new Random();

        return rand.nextInt(this.prodMax - this.prodMin) + this.prodMin;
    }

    public boolean isTree(){
        return tree;
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
