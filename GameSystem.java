import java.util.ArrayList;

public class GameSystem {    
    private ArrayList<Crop> cropList = new ArrayList<Crop>(); //list of crops/crop data
    private Farmer farmer; //farmer
    private Farm farm; //farm
    private int day; //days active

    public GameSystem(int status[]){
        //add crop info to crop list
        this.cropList.add(new Crop("Turnip", 'T', "Root", 2, 1, 0, 1, 1, 2, 5, 6, 5.0));
        this.cropList.add(new Crop("Carrot", 'C', "Root", 3, 1, 0, 1, 1, 2, 10, 9, 7.5));
        this.cropList.add(new Crop("Potato", 'P', "Root", 5, 3, 1, 1, 1, 10, 20, 3, 12.5));
        this.cropList.add(new Crop("Rose", 'R', "Flower", 1, 1, 0, 1, 1, 1, 5, 5, 2.5));
        this.cropList.add(new Crop("Tulips", 'L', "Flower", 2, 2, 0, 1, 1, 1, 10, 9, 5));
        this.cropList.add(new Crop("Sunflower", 'S', "Flower", 3, 2, 1, 1, 1, 1, 20, 19, 7.5));
        this.cropList.add(new Crop("Mango", 'M', "Fruit Tree", 10, 7, 4, 0, 5, 15, 100, 8, 25));
        this.cropList.add(new Crop("Apple", 'A', "Fruit Tree", 10, 7, 5, 0, 10, 15, 200, 5, 25));

        this.farmer = new Farmer();
        this.farm = new Farm(status);
        this.day = 1;

        //add tools to farmer inventory
        this.farmer.addTool(new Tool("Plow", 'P', 0, 0.5, 1, 2));
        this.farmer.addTool(new Tool("Watering Can", 'W', 0, 0.5, 3, 3));
        this.farmer.addTool(new Tool("Fertilizer", 'F', 10, 4, 3, 3));
        this.farmer.addTool(new Tool("Pickaxe", 'A', 50, 15, 0, 1));
        this.farmer.addTool(new Tool("Shovel", 'S', 7, 2, 4, 1));
    }

    //generates new seed for planting
    public Crop generateSeed(char id){
        for(Crop c: this.cropList){
            if(c.getID() == id){
                Crop seed = new Crop(c.getName(), c.getID(), c.getType(), c.getHarvestTime(), c.getWater(), c.getFertilizer(),c.getBonus(), c.getProdMin(), c.getProdMax(), c.getSeedCost(), c.getSellPrice(), c.getXp());
                return seed;
            }
        }
        return null;
    }

    //checks if tree can be planted on the tile
    public boolean treePlantable(int row, int col){
        if(col != 1 && col != 5 && row != 1 && row != 10){ //not corner and not far side
            for(int i = -1; i < 2; i++){ //row above, below, and current
                for(int j = -1; j < 2; j++){ //column to left, right, and current
                    int tileStatus = this.farm.getTile(row+i, col+j).getStatus(); //gets status of tile
                    if(tileStatus != 1 && tileStatus != 2) return false; //has a surrounding tile thats occupied
                }
            }
            return true; //can plant tree in tile
        }
        return false; //cant plant tree in tile
    }

    //plants seed on tile given row, col, and seed id
    public void plantSeed(int row, int col, char id){
        Crop seed = generateSeed(id); //generates seed
        Tile tile = this.farm.getTile(row, col);
        if(seed != null && tile != null){ //both seed and tile were within bounds
            int result = 0;
            String s = "";

            if(seed.getType() != "Fruit Tree" || (seed.getType() == "Fruit Tree" && this.treePlantable(row, col))){ //seed is not a fruit tree or is and the tile is tree plantable
                result = this.farmer.plantSeed(seed, tile);
            }

            if (result == 0) s = "not ";

            System.out.println("Planting of " + seed.getName()+ " was " + s + "successful."); //display result
            if(result == 1){
                System.out.println("Tile will be harvestable in " + seed.getHarvestTime() + " days. (Day " + (day + seed.getHarvestTime()) + ")");
                System.out.println("The tile will need " + seed.getWater() + " GALLONS of water and " + seed.getFertilizer() + " METRIC TONS of fertilizer.");
            }
        }
        if(tile == null) System.out.println("Tile is out of bounds.");
        if(seed == null && id != 'X') System.out.println("We don't have that seed.");
    }

    //harvests tile
    public void harvestTile(int row, int col){
        Tile tile = this.farm.getTile(row, col);
        if(tile != null){
            String cropName = tile.getCrop().getName();
            int result = this.farmer.harvestTile(tile);

            if(result > 0){
                System.out.println("Harvest was successful. " + result + " " + cropName + "/s was/were produced.");
            }
            else System.out.println("Harvest was not successful.");
        }
    }

    //uses tool on tile given row and col of tile and id of tool
    public void useTool(int row, int col, char id){
        Tile tile = this.farm.getTile(row, col);
        Tool tool = this.farmer.getTool(id);
        if(tool != null){
            int result = this.farmer.useTool(id, tile);
            if(result > 0){ //use of tool was successful
                System.out.println(tool.getName() + " was used on Tile (" + row + ", " + col + ").");
                if(tool.getID() == 'W'){ //if tool was watering can, show new water level
                    System.out.println("Tile Water Level: " + tile.getWater());
                }
                if(tool.getID() == 'F'){ //if tool was fertilizer, show new fertilizer level
                    System.out.println("Tile Fertilizer Level: " + tile.getFertilizer());
                }
            }
            else{ //use of tool was not successful
                System.out.println(tool.getName() + " was not able to be used.");
            }
        }
        else if(id != 'X') System.out.println("We don't have that tool."); //given tool id did not have tool assigned to it
    }

    //registers farmer for next farmer type
    public void registerFarmer(){
        int nextType = this.farmer.getRegistration();
        int result = this.farmer.registerForLevel(nextType);

        if(result > 0){
            System.out.println("Successfully registered. You are now a " + this.farmer.getRegName() + " Farmer.");
        }
        else System.out.println("Registration was unsuccessful.");
    }

    //updates farm and day for next day
    public void nextDay(){
        this.farm.nextDay();
        this.day++;
    }

    //checks if game over requirements are reached
    public boolean checkGameOver(){
        int farmStatus = this.farm.getFarmStatus();
        if(farmStatus == 2){ //all withered
            if(!this.farmer.canAfford(this.farmer.getTool('S').getCost())) return true; //all withered and cant afford using a shovel
        }
        if(farmStatus == 1){ //no active crop
            if(!this.farmer.canAfford(cropList.get(0).getSeedCost() - this.farmer.getCostReduction())) return true; //no crops are active and cant afford turnip (cheapest root)
        }
        return false;
    }

    //displays usable tools based on tile status
    public void displayToolOptions(int row, int col){
        Tile tile = this.farm.getTile(row, col); //get tile
        if(tile != null){ //tile exists
            System.out.println("\n[TOOL OPTIONS]");
            for(Tool t: this.farmer.getToolList()){
                if(t.tileCompatible(tile)) System.out.println("[" + t.getID() + "] " + t.getName() + " ($" + t.getCost() + ")");
            }
            System.out.println("[X] Back");
            this.inputPrompt();
        }
    }

    //displays plantable crop name w/ cost
    public void displayCropOptions(int row, int col){
        Tile tile = this.farm.getTile(row, col);
        if(tile != null){
            System.out.println("\n[CROP OPTIONS]");
            for(Crop c : this.cropList){
                if(tile.getStatus() == 2){
                    if(this.farmer.canAfford(c.getSeedCost()) && (!c.isTree() || this.treePlantable(row, col))){
                        System.out.println("[" + c.getID() + "] " + c.getName() + " ($" + c.getSeedCost() + ")");
                    }
                }
            }
            System.out.println("[X] Back");
            this.inputPrompt();
        }
    }

    //displays info of tile
    public void displayTileInfo(int row, int col){
        Tile tile = this.farm.getTile(row, col);
        if(tile != null){
            String s = "";
            switch(tile.getStatus()){
                case 0:
                    s = "Rock";
                    break;
                case 1:
                    s = "Unplowed";
                    break;
                case 2:
                    s = "Plowed";
                    break;
                case 3:
                    if(tile.canHarvest()) s = "Plant (Harvestable)";
                    else s = "Plant (Growing)";
                    break;
                case 4:
                    s = "Plant (Withered)";
                    break;
                
            }
            System.out.println("\n[TILE INFO] (" + row + ", " + col + ")");
            System.out.println("Status: " + s);
            if(tile.getStatus() > 2) {
                System.out.println("Crop: " + tile.getCrop().getName());
            }
            System.out.println("Water Level: " + tile.getWater());
            System.out.println("Fertilizer Level: " + tile.getFertilizer());
        }
    }
    
    //displays tile options (use tool, plant, harvest, tile info)
    public void selectTileOptions(int row, int col){ 
        Tile tile = this.farm.getTile(row, col);
        if(tile != null){   
            System.out.println("\n[TILE OPTIONS]");
            System.out.println("[T] Use Tool");
            if(tile.getStatus() == 2) System.out.println("[P] Plant Seed");
            if(tile.canHarvest()) System.out.println("[H] Harvest Tile");
            System.out.println("[I] Tile Info");
            System.out.println("[X] Back");
            this.inputPrompt();
        }
    }

    public void displayFarmerDetails(){ //level (xp), coins, registration, waterlimit inc, fert limit inc, bonus per produce, seed csot reduction, 
        System.out.println("Level(XP): " + this.farmer.getLevel() + "(" + this.farmer.getXp() + ")");
        System.out.println("Objectcoins: " + this.farmer.getCoins());
        System.out.println("Farmer Type: " + this.farmer.getRegName() + " Farmer");
        System.out.println("Water Limit Increase: " + this.farmer.getWaterLimit());
        System.out.println("Fertilizer Limit Increase: " + this.farmer.getFertLimit());
        System.out.println("Bonus per Produce: $" + this.farmer.getBonus());
        System.out.println("Seed Cost Reduction: " + this.farmer.getCostReduction());

    }

    public void displayFarmerOptions(){ //register farmer, more farmer details
        System.out.println("\n[FARMER OPTIONS]");
        System.out.println("[1] Register Farmer ($" + ((this.farmer.getRegistration() + 2) * 100) + ")");
        System.out.println("[2] More Farmer Information");
        this.inputPrompt();
    }

    public void displayHeader(){ //display game name, level, coins, day
        System.out.println("\n\nMy Farm");
        System.out.println("-------");
        System.out.print("Level: " + this.farmer.getLevel());
        System.out.print(" | Coins: " + this.farmer.getCoins());
        System.out.println(" | Day: " + this.day);
    }

    //displays main options
    public void displayMainOptions(){
        System.out.println("\n[ACTIONS]");
        System.out.println("[1] Select Tile");
        System.out.println("[2] Next Day");
        System.out.println("[3] Farmer Options");
        this.inputPrompt();
    }

    public void inputPrompt(){ //input prompt
        System.out.print("\nInput: ");
    }

    //displays whole farm in 5 columns
    public void displayFarm(){
        int tileCount = this.farm.getTileCount();
        int rows = tileCount/5;
        if((tileCount % 5) != 0) rows++;
        System.out.println("R - rock | G - Growing | U - unplowed | P - Plowed | H - Harvestable | W - Withered");
        System.out.println("   | 1 | 2 | 3 | 4 | 5 |");
        for(int x = 1; x <= rows; x ++){
            System.out.print("  ----------------------\n");
            if(x < 10) System.out.print(" ");
            System.out.print(x + " |");
            for(int y = 1; y <= 5; y++){
                String s = "-";
                if(((5 * (x-1)) + (y)) <= tileCount){
                    Tile t = this.farm.getTile(x, y);
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
                            if(!t.canHarvest()) s = "G";
                            else s = "H";
                        break;
                        case 4:
                            s = "W";
                        break;
                    }
                }
                System.out.print(" " + s + " |");
            }
            System.out.print("\n   |");
            for(int y = 1; y <= 5; y++){
                char s = '-';
                if(((5 * (x-1)) + (y)) <= tileCount){
                    Tile t = this.farm.getTile(x, y);
                    if(t.getStatus() == 3 || t.getStatus() == 4){
                        s = t.getCrop().getID();
                    }
                }
                System.out.print(" " + s + " |");
            }
            System.out.print("\n");
        }
    }
}
