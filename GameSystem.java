import java.util.ArrayList;

public class GameSystem {
    private ArrayList<Tool> toolList;
    private Farmer farmer;
    private Farm farm;
    private ArrayList<Crop> cropList;
    private int day;

    public GameSystem(int status[]){
        toolList = new ArrayList<Tool>();
        cropList = new ArrayList<Crop>();

        toolList.add(new Tool("Plow", 0, 0.5, 1, 2, "P"));
        toolList.add(new Tool("Watering Can", 0, 0.5, 3, 3, "W"));
        toolList.add(new Tool("Fertilizer", 10, 4, 3, 3, "F"));
        toolList.add(new Tool("Pickaxe", 50, 15, 0, 1, "A"));
        toolList.add(new Tool("Shovel", 7, 2, 4, 1, "S"));

        cropList.add(new Crop("Turnip", "Root", 2, 1, 0, 1, 1, 2, 5, 6, 5, "T"));
        cropList.add(new Crop("Carrot", "Root", 3, 1, 0, 1, 1, 2, 10, 9, 7.5, "C"));
        cropList.add(new Crop("Potato", "Root", 5, 3, 1, 1, 1, 10, 20, 3, 12.5, "P"));
        cropList.add(new Crop("Rose", "Flower", 1, 1, 0, 1, 1, 1, 5, 5, 2.5, "R"));
        cropList.add(new Crop("Tulips", "Flower", 2, 2, 0, 1, 1, 1, 10, 9, 5, "L"));
        cropList.add(new Crop("Sunflower", "Flower", 3, 2, 1, 1, 1, 1, 20, 19, 7.5, "S"));
        cropList.add(new Crop("Mango", "Fruit Tree", 10, 7, 4, 0, 5, 15, 100, 8, 25, "M"));
        cropList.add(new Crop("Apple", "Fruit Tree", 10, 7, 5, 0, 10, 15, 200, 5, 25, "A"));

        farmer = new Farmer();
        farm = new Farm(status);
        day = 1;
    }

    public Crop generateSeed(String index){
        int i = 0;
        for(Crop c : this.cropList){
            if(c.getIndex().equals(index)) i = cropList.indexOf(c);
        }

        Crop seed = new Crop(cropList.get(i).getName(), cropList.get(i).getType(), cropList.get(i).getHarvestTime(), cropList.get(i).getWater(), cropList.get(i).getFertilizer(),
                    cropList.get(i).getBonusLimit(), cropList.get(i).getMinProduce(), cropList.get(i).getMaxProduce(), cropList.get(i).getSeedCost(), cropList.get(i).getSellPrice(),
                    cropList.get(i).getXp(), cropList.get(i).getIndex());

        return seed;
    }

    public void nextDay(){
        this.farm.nextDay();
        this.day++;
    }

    public int plantSeed(int row, int col, Crop seed){
        Tile tile = farm.getTile(row, col);
        if(seed == null) return 0;

        if(tile.getStatus() == 2 && this.farmer.canAfford(seed.getSeedCost())){ //tile is plowed
            if(seed.getType() != "Fruit Tree" || treePlantable(row, col)){ //crop to be added fits
                tile.addCrop(seed);
                this.farmer.payCoins(seed.getSeedCost());
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

            System.out.println("Successfully harvested. " + produce + " " + crop.getName() + " was/were harvested.");
            return 1; //tile harvest successful
        }
        System.out.println("Harvest unsuccessful.");
        return 0; //not successful
    }

    public Tool getTool(String index){
        for(Tool t : this.toolList){
            if(t.getIndex().equalsIgnoreCase(index)) return t;
        }
        return null;
    }

    public Tool getTool(int index){
        return this.toolList.get(index);
    }

    public int useTool(int row, int col, String toolIndex){
        Tile tile = farm.getTile(row, col);
        Tool tool = getTool(toolIndex);
        if(tool == null) return 0;
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
                    int tileStatus = this.getTileStatus(row+i, col+j);
                    if(tileStatus != 1 && tileStatus != 2) return false; //has a surrounding tile thats occupied
                }
            }
            return true;
        }
        return false;
    }

    public void registerFarmer(){
        int nextReg = this.farmer.getRegistration() + 1;

        int result = this.farmer.registerForLevel(nextReg);
        if(result == 1) {
            String typeName = "bruh";
            switch(nextReg){
                case 1:
                    typeName = "Registered";
                break;
                case 2:
                    typeName = "Distinguished";
                break;
                case 3:
                    typeName = "Legendary";
                break;
            }
            System.out.print("Sucessfully registered. Now a " + typeName + " Farmer.");
        }
        else System.out.println("Could not register for next Farmer Type.");
    }

    public void displayStart(){
        System.out.println("My Farm");
        System.out.println("[1] Start");
        System.out.println("[2] Exit");
    }

    public void displayHeader(){ //display game name, level, coins, day
        System.out.println("\n\nMy Farm");
        System.out.println("-------");
        System.out.print("Level: " + farmer.getLevel());
        System.out.print(" | Coins: " + farmer.getCoins());
        System.out.println(" | Day: " + this.day);
    }

    public void displayFarm(){ //display tiles in 10x5 grid
        System.out.println("R - rock | G - Growing | U - unplowed | P - Plowed | H - Harvestable | W - Withered");
        System.out.println("   | 1 | 2 | 3 | 4 | 5 |");
        for(int x = 1; x <= 10; x++){
            System.out.print("  ----------------------\n");
            if(x != 10) System.out.print(" ");
            System.out.print(x + " |");
            for(int y = 1; y <= 5; y++){
                Tile t = farm.getTile(x, y);
                String s = "-";
                switch(t.getStatus()){
                    case 0:
                        s = "R";
                    break;
                    case 1:
                        s = "U";
                    break;
                    case 2:
                        s = "P";
                    break;
                    case 3:
                        if(!t.isHarvestable()) s = "G";
                        else s = "H";
                    break;
                    case 4:
                        s = "W";
                    break;
                }
                System.out.print(" " + s + " |");
            }
            System.out.print("\n   |");
            for(int y = 1; y <= 5; y++){
                Tile t = farm.getTile(x, y);
                String s = "-";
                if(t.getStatus() == 3 || t.getStatus() == 4){
                    s = t.getCrop().getIndex();
                }
                System.out.print(" " + s + " |");
            }
            System.out.print("\n");
        }
    }
    public void displayMainOptions(){ //select tile, select farmer, tool information, crop information, next day
        System.out.println("\n[ACTIONS]");
        System.out.println("[1] Select Tile");
        System.out.println("[2] Next Day");
        System.out.println("[3] Farmer Options");
        this.inputPrompt();
    }

    public void displayTileOptions(int row, int col){ //display possible tile interactions based on tile status (use tool, plant crop, harvest crop)
        //T - tool | P - plant seed  | H - harvest
        Tile t = this.farm.getTile(row, col);
        System.out.println("\n[TILE OPTIONS]");
        System.out.println("[T] Use tool");
        if(t.getStatus() == 2) System.out.println("[P] Plant seed");
        if(t.isHarvestable()) System.out.println("[H] Harvest");
        this.inputPrompt();
    }

    public void displayToolOptions(int row, int col){ //display usable tools on tile
        Tile tile = this.farm.getTile(row, col);
        System.out.println("\n[TOOL OPTIONS]");
        for(Tool t : this.toolList){
            if(t.canUseOnTile(tile)){
                System.out.println("[" + t.getIndex() + "] " + t.getName() + "(¢" + t.getCost() + ")");
            }
        }
        this.inputPrompt();
    }

    public int displayCropOptions(int row, int col){ //display plantable crops w/ cost based on tile
        if(this.getTileStatus(row, col) == 2){
            System.out.println("\n[SELECT A CROP TO PLANT]");
            for(Crop c : this.cropList){
                if(!c.getType().equals("Fruit Tree")){
                    System.out.println("[" + c.getIndex() + "] " + c.getName() + "(¢" + c.getSeedCost() + ")");
                }
                else{
                    if(treePlantable(row, col)){
                        System.out.println("[" + c.getIndex() + "] " + c.getName() + "(¢" + c.getSeedCost() + ")");
                    }
                }
            }
            this.inputPrompt();
            return 1;
        }
        return 0;
    }

    public void displayFarmerOptions(){ //register farmer, see more farmer details
        System.out.println("\n[FARMER OPTIONS]");
        System.out.println("[1] Register Farmer");
        System.out.println("[2] Display More Farmer Stats");
    }

    public int getTileStatus(int row, int col){
        return this.farm.getTile(row, col).getStatus();
    }

    public void displayFarmerStats(){
        int reg = this.farmer.getRegistration();
        String regType = "";
        switch(reg){
            case 1:
            regType = "Registered";
            break;
            case 2:
            regType = "Distinguished";
            break;
            case 3:
            regType = "Legendary";
            break;
        }
        System.out.println("\n[MORE FARMER STATS]");
        System.out.println("XP: " + this.farmer.getXp());
        System.out.println("Level: " + this.farmer.getLevel());
        System.out.println("Objectcoins: " + this.farmer.getCoins());
        System.out.println("Registration: " + regType + " Farmer");
        System.out.println("Bonus Earnings per Produce: +" + this.farmer.getBonusPerProduce());
        System.out.println("Water Limit Increase: +" + this.farmer.getWaterIncrease());
        System.out.println("Fertilizer Limit Increase: +" + this.farmer.getFertilizerIncrease());
        System.out.println("Seed Cost Reduction: -" + this.farmer.getCostReduction());
        
    }

    /*
    public void displayToolInformation(){

    }
    
    public void displayCropInformation(){

    }
    */
    public void inputPrompt(){
        System.out.print("\nInput: ");
    }
}
