package CCPROG3_MCO;

public class Plant {
    private String name;
    private int harvestTime;
    private int waterNeeds;
    private int fertilizerNeeds;
    private int bonusLimit;
    private int prodUpperLimit;
    private int prodLowerLimit;
    private int cost;
    private int sellPrice;
    private double xp;
    private int growth = 1;

    private String nameArr[] = {"Turnip", "Carrot", "Potato", "Rose", "Tulips", "Sunflower", "Mango", "Apple"};
    private int harvestTimeArr[] = {2, 3, 5, 1, 2, 3, 10, 10};
    private int waterNeedsArr[] = {1, 1, 3, 1, 2, 2, 7, 7};
    private int fertilizerNeedsArr[] = {0, 0, 1, 0, 0, 1, 4, 5};
    private int lowerLimitArr[] = {1, 1, 1, 1, 1, 1, 5, 10};
    private int upperLimitArr[] = {2, 2, 10, 1, 1, 1, 15, 15};
    private int costArr[] = {5, 10, 20, 5, 10, 20, 100, 200};
    private int priceArr[] = {6, 9, 3, 5, 9, 19, 8, 5};
    private double xpArr[] = {5, 7.5, 12.5, 2,5, 5, 7.5, 25, 25};

    public Plant(String name){
        int index = 0;
        for(int i = 0; i < 8; i++){
            if(this.nameArr[i].equalsIgnoreCase(name)) {
                index = i;
                this.name = this.nameArr[i];
            }
        }

        if(index < 6) this.bonusLimit = 1;
        else this.bonusLimit = 0;

        this.harvestTime = this.harvestTimeArr[index];
        this.waterNeeds = this.waterNeedsArr[index];
        this.fertilizerNeeds = this.fertilizerNeedsArr[index];
        this.prodUpperLimit = this.upperLimitArr[index];
        this.prodLowerLimit = this.lowerLimitArr[index];
        this.cost = this.costArr[index];
        this.sellPrice = priceArr[index];
        this.xp = xpArr[index];
    }

    public void growPlant(){
        this.growth++;
    }

    public void DIE(){ //hehe
        this.growth = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getHarvestTime(){
        return this.harvestTime;
    }

    public int getWater(){
        return this.waterNeeds;
    }

    public int getFertilizer(){
        return this.fertilizerNeeds;
    }

    public int getBonus(){
        return this.bonusLimit;
    }

    public int getUpper(){
        return this.prodUpperLimit;
    }

    public int getLower(){
        return this.prodLowerLimit;
    }

    public int getCost(){
        return this.cost;
    }

    public int getPrice(){
        return this.sellPrice;
    }

    public double getXp(){
        return this.xp;
    }

    public int getGrowth(){
        return this.growth;
    }
}

