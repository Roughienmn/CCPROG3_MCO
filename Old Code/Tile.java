package CCPROG3_MCO;
import java.util.Random;
public class Tile {
    private Plant plant = null;
    //0 - rock; 1 - unplowed; 2 - plowed; 3 - plant; 4 - withered;
    private int status;
    private int water = 0;
    private int fertilizer = 0;

    public Tile(int status){
        this.status = status;
    }

    public void updateStatus(int status){
        if(status == 1){
            this.resetTile();
        }
        else if(status == 2){
            this.plowTile();
        }
        else if(status == 4){
            this.killPlant();
        }
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
        this.water = 0;
        this.fertilizer = 0;
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

    public int getFertilizer(){
        return this.fertilizer;
    }

    public Plant getPlant(){
        return this.plant;
    }
}
