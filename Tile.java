package CCPROG3_MCO;

public class Tile {
    private Plant plant = null;
    //0 - rock; 1 - unplowed; 2 - plowed; 3 - plant; 4 - withered;
    private int status;
    private int water = 0;
    private int fertilizer = 0;

    public Tile(int status){
        this.status = status;
    }

    public void addPlant(Plant plant){
        if(this.status == 2){
            this.plant = plant;
            this.status = 3;
        }
    }

    public void killPlant(){
        if(this.hasPlant()){
            this.plant.DIE();
            this.status = 4;
        }
    }

    public void resetTile(){
        this.plant = null;
        this.status = 1;
    }

    public void plowTile(){
        this.status = 2;
    }

    public void nextDay(){
        if(this.status == 3){ //tile has plant
            if(this.isHarvestable()){ //plant was harvestable this day
                this.killPlant(); // kill plant
            }
            else{
                this.plant.growPlant();
                if(this.isHarvestable()){
                    if(this.fertilizer < this.plant.getFertilizer() || this.water < this.plant.getWater()){ //check if minimum requirements are met
                        this.killPlant();
                    }
                }
            }
        }
    }

    public boolean isHarvestable(){
        if(this.plant.getGrowth() == this.plant.getHarvestTime()){
            return true;
        }
        return false;
    }

    public boolean hasPlant(){
        if(this.plant != null){
            return true;
        }
        return false;
    }

    public void addWater(){
        water++;
    }

    public void addFertilizer(){
        fertilizer++;
    }

    public int getStatus(){
        return this.status;
    }

    public int getWater(){
        return this.water;
    }

    public int fertilizer(){
        return this.fertilizer;
    }
}
