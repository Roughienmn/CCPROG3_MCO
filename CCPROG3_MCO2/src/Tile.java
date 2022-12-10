/**
 * This class represents the Tile on which a crop is to be planted in.
 * This class also contains information on all activity done on a Tile.
 * There are multiple Tiles in a Farm.
 */
public class Tile {
    private int water; //tile water level
    private int fertilizer; //tile fertilizer level
    private Crop crop; //crop on tile
    private int status; //0 - rock; 1 - unplowed; 2 - plowed; 3 - plant; 4 - withered

    /**
     * Constructor for class Tile. 
     * This constructor initializes this tile's values.
     * @param status this tile's status. Represented numerically.
     */
    public Tile(int status){
        this.water = 0;
        this.fertilizer = 0;
        this.crop = null;
        this.status = status;
    }

    /**
     * Gets the status of the tile.
     * @return this tile's status.
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * Resets tile to default, which is unplowed and without rock.
     */
    public void resetTile(){
        this.water = 0;
        this.fertilizer = 0;
        this.crop = null;
        this.status = 1;
    }

    /**
     * Gets the water level. 
     * @return this tile's water level.
     */
    public int getWater(){
        return this.water;
    }

    /**
     * Gets the fertilizer level.
     * @return this tile's fertilizer level.
     */
    public int getFertilizer(){
        return this.fertilizer;
    }

    /**
     * Gets the crop in tile.
     * @return this tile's crop.
     */
    public Crop getCrop(){
        return this.crop;
    }

    /**
     * Checks if the tile contains a crop.
     * @return whether this tile has a crop or not.
     */
    public boolean hasCrop(){
        if(this.crop != null) 
            return true;
        return false;
    }

    /**
     * Adds water to tile given that the tile is plowed. 
     */
    public void addWater(){
        if(this.hasCrop())
            this.water++;
    }

    /**
     * Adds fertilizer to tile given that the tile is plowed.
     */
    public void addFertilizer(){
        if(this.hasCrop())
            this.fertilizer++;
    }

    /**
     * Checks if the crop on the tile can be harvested.
     * @return whether the crop can be harvested or not.
     */
    public boolean canHarvest(){
        if(!hasCrop()) return false;
        if(this.crop.getGrowth() == this.crop.getHarvestTime()) return true;
        return false;
    }

    /**
     * Adds a crop to the tile and sets the tile status to with plant.
     * @param seed crop's seed.
     */
    public void addCrop(Crop seed){
        this.crop = seed;
        this.status = 3;
    }

    /**
     * "Kills the crop" and sets the tile status to withered.
     */
    public void killCrop(){
        this.crop.die();
        this.status = 4;
    }

    /**
     * Updates tile for the next day.
     */
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
