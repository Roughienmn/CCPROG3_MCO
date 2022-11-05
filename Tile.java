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

    //get status of tile
    public int getStatus(){
        return this.status;
    }

    //reset tile to default (unplowed no rock)
    public void resetTile(){
        this.water = 0;
        this.fertilizer = 0;
        this.crop = null;
        this.status = 1;
    }

    //get water level
    public int getWater(){
        return this.water;
    }

    //get fertilizer level
    public int getFertilizer(){
        return this.fertilizer;
    }

    //get crop
    public Crop getCrop(){
        return this.crop;
    }

    //check if tile has a crop
    public boolean hasCrop(){
        if(this.crop != null) return true;
        return false;
    }

    //add water to tile if tile is plowed 
    public void addWater(){
        if(this.status > 1) this.water++;
    }

    //add water to tile if tile is plowed 
    public void addFertilizer(){
        if(this.status > 1) this.fertilizer++;
    }

    //set status of tile
    public void setStatus(int status){
        this.status = status;
    }

    //checks if crop can be harvested
    public boolean canHarvest(){
        if(!hasCrop()) return false;
        if(this.crop.getGrowth() == this.crop.getHarvestTime()) return true;
        return false;
    }

    //adds crop to tile and sets status to withPlant
    public void addCrop(Crop seed){
        this.crop = seed;
        this.status = 3;
    }

    //kills the crop and sets status to withered
    public void killCrop(){
        this.crop.DIE();
        this.status = 4;
    }

    //updates tile for next day
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
