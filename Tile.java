public class Tile {
    private Crop crop = null;
    private int status; //0 - rock; 1 - unplowed; 2 - plowed; 3 - has plant; 4 - withered plant;
    private int water = 0;
    private int fertilizer = 0;
    
    public Tile(int status){
        this.status = status;
    }

    public void addCrop(Crop crop){
        this.crop = crop;
        this.status = 3;
    }

    public void killCrop(){
        this.crop.DIE();
        this.status = 4;
    }

    public void resetTile(){
        this.status = 1;
        this.crop = null;
        this.water = 0;
        this.fertilizer = 0; 
    }

    public void plowTile(){
        this.status = 2;
    }

    public boolean hasCrop(){
        if(this.crop != null) return true;
        return false;
    }

    public boolean isHarvestable(){
        if(this.crop == null) return false;
        if(this.crop.getDaysAlive() == this.crop.getHarvestTime()) return true;
        return false;
    }

    public void nextDay(){
        if(this.hasCrop()){
            if(this.isHarvestable()){ //crop was harvestable today but was not
                this.killCrop(); //kill the darn thing
            }
            else{
                this.crop.grow();
                if(this.isHarvestable()){
                    if(this.fertilizer < this.crop.getFertilizer() || this.water < this.crop.getWater()){ //check if minimum requirements weren't met
                        this.killCrop(); //kill it :3
                    }
                }
            }

        }
    }

    public void addWater(){
        this.water++;
    }

    public void addFertilizer(){
        this.fertilizer++;
    }

    public int getStatus(){
        return this.status;
    }

    public Crop getCrop(){
        return this.crop;
    }

    public int getWater(){
        return this.water;
    }

    public int getFertilizer(){
        return this.fertilizer;
    }
}
