import java.util.ArrayList;

public class GameSystem {
    private ArrayList<Tool> toolList;
    private Farmer farmer;
    private Farm farm;
    private ArrayList<Crop> cropList;

    public GameSystem(ArrayList<Integer> status){
        toolList.add(new Tool("Plow", 0, 0.5, 1, 2));
        toolList.add(new Tool("Watering Can", 0, 0.5, 3, 3));
        toolList.add(new Tool("Fertilizer", 10, 4, 3, 3));
        toolList.add(new Tool("Pickaxe", 50, 15, 0, 1));
        toolList.add(new Tool("Shovel", 7, 2, 4, 1));

        cropList.add(new Crop("Turnip", "Root", 2, 1, 0, 1, 1, 2, 5, 6, 5));
        cropList.add(new Crop("Carrot", "Root", 3, 1, 0, 1, 1, 2, 10, 9, 7.5));
        cropList.add(new Crop("Potato", "Root", 5, 3, 1, 1, 1, 10, 20, 3, 12.5));
        cropList.add(new Crop("Rose", "Flower", 1, 1, 0, 1, 1, 1, 5, 5, 2.5));
        cropList.add(new Crop("Tulips", "Flower", 2, 2, 0, 1, 1, 1, 10, 9, 5));
        cropList.add(new Crop("Sunflower", "Flower", 3, 2, 1, 1, 1, 1, 20, 19, 7.5));
        cropList.add(new Crop("Mango", "Fruit Tree", 10, 7, 4, 0, 5, 15, 100, 8, 25));
        cropList.add(new Crop("Apple", "Fruit Tree", 10, 7, 5, 0, 10, 15, 200, 5, 25));

        farmer = new Farmer();
        farm = new Farm(status);
    }

    public Crop generateSeed(String name){
        int i = 0;
        for(Crop c : this.cropList){
            if(c.getName().equals(name)) i = cropList.indexOf(c);
        }

        Crop seed = new Crop(cropList.get(i).getName(), cropList.get(i).getType(), cropList.get(i).getHarvestTime(), cropList.get(i).getWater(), cropList.get(i).getFertilizer(),
                    cropList.get(i).getBonusLimit(), cropList.get(i).getMinProduce(), cropList.get(i).getMaxProduce(), cropList.get(i).getSeedCost(), cropList.get(i).getSellPrice(),
                    cropList.get(i).getXp());

        return seed;
    }

    public void nextDay(){
        this.farm.nextDay();
    }

    public int plantSeed(int row, int col, Crop seed){
        Tile tile = farm.getTile(row, col);
        if(tile.getStatus() == 2){ //tile is plowed
            if(seed.getType() != "Fruit Tree" || treePlantable(row, col)){ //crop to be added fits
                tile.addCrop(seed);
            }
            return 1; //seed was planted
        }
        return 0; //unable to plant seed
    }

    public int harvestTile(int row, int col){
        Tile tile = farm.getTile(row, col);
        if(tile.isHarvestable()){
            //get crop data
            Crop crop = tile.getCrop();
            int maxWater = crop.getWater() + crop.getBonusLimit() + this.farmer.getWaterIncrease();
            int maxFertilizer = crop.getFertilizer() + crop.getBonusLimit() + this.farmer.getFertilizerIncrease();

            //compute income
            int produce = crop.generateProduce();
            double harvestTotal = produce * (crop.getSellPrice() + this.farmer.getBonusPerProduce());
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater() - 1);
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer());

            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (crop.getType().equals("Flower")) finalHarvestPrice = finalHarvestPrice * 1.1;

            //add income and xp
            farmer.addCoins(finalHarvestPrice);
            farmer.addXp(crop.getXp());
            tile.resetTile();

            return 1; //tile harvest successful
        }
        return 0; //not successful
    }

    public Tool getTool(String name){
        for(Tool t : toolList){
            if(t.getName().equalsIgnoreCase(name)) return t;
        }
        return null;
    }

    public int useTool(int row, int col, Tool tool){
        Tile tile = farm.getTile(row, col);
        if (tool.canUseOnTile(tile) && farmer.canAfford(tool.getCost())){
            switch(tool.getName()){
                case "Plow":
                    tile.plowTile();
                break;
                case "Watering Can":
                    tile.addWater();
                break;
                case "Fertilizer":
                    tile.addFertilizer();
                break;
                case "Pickaxe":
                case "Shovel":
                    tile.resetTile();
                break;
            }

            farmer.addXp(tool.getXp());
            farmer.payCoins(tool.getCost());

            return 1; //successful tool use
        }
        return 0;
    }

    public boolean checkGameOver(){
        int farmStatus = this.farm.getFarmStatus();
        if(farmStatus == 2){ //all withered
            if(!this.farmer.canAfford(this.getTool("Shovel").getCost())) return true;
        }
        if(farmStatus == 1){
            if(!this.farmer.canAfford(cropList.get(1).getSeedCost() - this.farmer.getCostReduction())) return true;
        }
        return false;
    }


    public boolean treePlantable(int row, int col){
        if(col != 1 && col != 5 && row != 1 && row != 10){ //not corner and not far side
            for(int i = -1; i < 2; i++){
                for(int j = -1; j < 2; j++){
                    int tileStatus = farm.getTile(row+i, col+j).getStatus();
                    if(tileStatus != 1 && tileStatus != 2) return false; //has a surrounding tile thats occupied
                }
            }
            return true;
        }
        return false;
    }

    public void displayStart(){

    }
}
