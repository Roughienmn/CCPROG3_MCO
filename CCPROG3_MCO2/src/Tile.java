public class Tile {
    private int water; //tile water level
    private int fertilizer; //tile fertilizer level
    private Crop crop; //crop on tile
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
        if(this.crop != null) 
            return true;
        return false;
    }

    public void addWater(){
        if(this.hasCrop())
            this.water++;
    }

    public void addFertilizer(){
        if(this.hasCrop())
            this.fertilizer++;
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
        this.crop.die();
        this.status = 4;
    }

    public void nextDay(){
        if(this.hasCrop()){
            if(this.canHarvest()){ //crop was harvestable today but was not
                this.killCrop(); //kill the crop
            }
            else{
                this.crop.grow();
                if(this.canHarvest()){
                    if(this.fertilizer < this.crop.getFertilizer() || this.water < this.crop.getWater()){
                        this.killCrop();
                    }
                }
            }
        }
    }

    public void setStatus(int status){
        this.status = status;
    }
}
