package Ver2;

public class Tile {
    private int water;
    private int fertilizer;
    private Crop crop;
    private int status; //0 - rock; 1 - unplowed; 2 - plowed; 3 - plant; 4 - withered

    public Tile(int status){
        this.water = 0;
        this.fertilizer = 0;
        this.crop = null;
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

    public void resetTile(){
        this.water = 0;
        this.fertilizer = 0;
        this.crop = null;
        this.status = 1;
    }

    public int getWater(){
        return this.water;
    }

    public int getFertilizer(){
        return this.fertilizer;
    }

    public Crop getCrop(){
        return this.crop;
    }

    public boolean hasCrop(){
        if(this.crop != null) return true;
        return false;
    }

    public void addWater(){
        if(this.status > 1) this.water++;
    }

    public void addFertilizer(){
        if(this.status > 1) this.fertilizer++;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public boolean canHarvest(){
        if(!hasCrop()) return false;
        if(this.crop.getGrowth() == this.crop.getHarvestTime()) return true;
        return false;
    }

    public void addCrop(Crop seed){
        this.crop = seed;
        this.status = 3;
    }

    public void killCrop(){
        this.crop.DIE();
        this.status = 4;
    }

    public void nextDay(){
        if(this.hasCrop()){
            if(this.canHarvest()){ //crop was harvestable today but was not
                this.killCrop(); //kill the darn thing
            }
            else{
                this.crop.grow();
                if(this.canHarvest()){
                    if(this.fertilizer < this.crop.getFertilizer() || this.water < this.crop.getWater()){ //check if minimum requirements weren't met
                        this.killCrop(); //kill it :3
                    }
                }
            }

        }
    }
}
