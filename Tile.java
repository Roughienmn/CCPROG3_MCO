/**
 * This class represents the Tile on which a crop is to be planted in.
 * This class also contains information on all activity done on a Tile.
 * There are multiple Tiles in a Farm.
 */
public class Tile {
    private int water; /** This tile's water level. */
    private int fertilizer; /** This tile's fertilizer level. */
    private Crop crop; /** The crop on this tile. */
    private int status; /** This tile's status. Can be: 
                         * 0, for rock; 1, for unplowed; 2, for plowed; 3, for with plant; and 4, for withered.
                         */

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
        if(this.crop != null) return true;
        return false;
    }

    /**
     * Adds water to tile given that the tile is plowed. 
     */
    public void addWater(){
        if(this.status > 1) this.water++;
    }

    /**
     * Adds fertilizer to tile given that the tile is plowed.
     */
    public void addFertilizer(){
        if(this.status > 1) this.fertilizer++;
    }

    /**
     * Sets the status of the tile.
     * @param status status to be set.
     */
    public void setStatus(int status){
        this.status = status;
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
        this.crop.DIE();
        this.status = 4;
    }

    /**
     * Updates tile for the next day.
     */
    public void nextDay(){
        if(this.hasCrop()){
            if(this.canHarvest()){ //Checks if the crop was harvestable on the current day but was not harvested.
                this.killCrop(); //Calls killCrop(), which sets the crop status to withered.
            }
            else{
                this.crop.grow();
                if(this.canHarvest()){
                    if(this.fertilizer < this.crop.getFertilizer() || this.water < this.crop.getWater()){ //Check if crop's minimum growth requirements weren't met
                        this.killCrop(); //Set crop status to withered.
                    }
                }
            }

        }
    }
}
