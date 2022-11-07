import java.util.ArrayList;

/**
 * This class represents the entire game process.
 * Tools and crop attributes defined in the MCO specs are assigned here.
 * Other game requirements and display are also seen here.
 */
public class GameSystem {    
    private ArrayList<Crop> cropList = new ArrayList<Crop>(); /** This game's crop data list. */
    private Farmer farmer; /** This game's farmer */
    private Farm farm; /** This game's farm. */
    private int day; /** The number of days this game has been active. */

    /**
     * Constructor of the class GameSystem. 
     * Creates instances of Crop and Tool and initializes their values according to the specifications.
     * @param status array of each Tile's status, which will be defined through file input in the next MCO phase.
     */
    public GameSystem(int status[]){
        //Add crop information to crop list.
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

        //Add tools to farmer inventory.
        this.farmer.addTool(new Tool("Plow", 'P', 0, 0.5, 1, 2));
        this.farmer.addTool(new Tool("Watering Can", 'W', 0, 0.5, 3, 3));
        this.farmer.addTool(new Tool("Fertilizer", 'F', 10, 4, 3, 3));
        this.farmer.addTool(new Tool("Pickaxe", 'A', 50, 15, 0, 1));
        this.farmer.addTool(new Tool("Shovel", 'S', 7, 2, 4, 1));
    }

    /**
     * Generates new seed for planting.
     * @param id the crop's id.
     * @return new seed is given id matches any of the crop ids, null if not.
     */
    public Crop generateSeed(char id){
        for(Crop c: this.cropList){
            if(c.getID() == id){
                Crop seed = new Crop(c.getName(), c.getID(), c.getType(), c.getHarvestTime(), c.getWater(), c.getFertilizer(),c.getBonus(), c.getProdMin(), c.getProdMax(), c.getSeedCost(), c.getSellPrice(), c.getXp());
                return seed;
            }
        }
        return null;
    }

    /**
     * Checks if tree can be planted on the given tile.
     * @param row the selected tile's row.
     * @param col the selected tile's column.
     * @return if tree is plantable in tile or not.
     */
    //checks if tree can be planted on the tile
    public boolean treePlantable(int row, int col){
        if(col != 1 && col != 5 && row != 1 && row != 10){ //not corner and not far side
            for(int i = -1; i < 2; i++){ //row above, below, and current
                for(int j = -1; j < 2; j++){ //column to left, right, and current
                    int tileStatus = this.farm.getTile(row+i, col+j).getStatus(); //gets status of tile
                    if(tileStatus != 1 && tileStatus != 2) return false; //has a surrounding tile thats occupied
                }
            }
            return true; //Can plant tree in tile.
        }
        return false; //Can't plant tree in tile.
    }

    /**
     * Plants seed on tile given row, column, and seed id.
     * @param row the tile's row.
     * @param col the tile's column.
     * @param id the seed's id.
     */
    public void plantSeed(int row, int col, char id){
        Crop seed = generateSeed(id); //Generates seed.
        Tile tile = this.farm.getTile(row, col);
        if(seed != null && tile != null){ //If both seed and tile are within bounds.
            int result = 0;
            String s = "";

            if(seed.getType() != "Fruit Tree" || (seed.getType() == "Fruit Tree" && this.treePlantable(row, col))){ //If the seed is not a fruit tree OR is a fruit tree and the tile is tree plantable.
                result = this.farmer.plantSeed(seed, tile);
            }

            if (result == 0) s = "not ";

            System.out.println("Planting of " + seed.getName()+ " was " + s + "successful."); //Displays result.
            if(result == 1){
                System.out.println("Tile will be harvestable in " + seed.getHarvestTime() + " days. (Day " + (day + seed.getHarvestTime()) + ")");
                System.out.println("The tile will need " + seed.getWater() + " GALLONS of water and " + seed.getFertilizer() + " METRIC TONS of fertilizer.");
            }
        }
        if(tile == null) System.out.println("Tile is out of bounds.");
        if(seed == null && id != 'X') System.out.println("We don't have that seed.");
    }

    /**
     * Harvests tile.
     * @param row the tile's row.
     * @param col the tile's column.
     */
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

    /**
     * Uses selected tool on selected tile.
     * @param row the tile's row.
     * @param col the tile's column.
     * @param id the tool's id.
     */
    public void useTool(int row, int col, char id){
        Tile tile = this.farm.getTile(row, col);
        Tool tool = this.farmer.getTool(id);
        if(tool != null){
            int result = this.farmer.useTool(id, tile);
            if(result > 0){ //If the use of tool was successful.
                System.out.println(tool.getName() + " was used on Tile (" + row + ", " + col + ").");
                if(tool.getID() == 'W'){ //If tool was watering can 'W', show new water level.
                    System.out.println("Tile Water Level: " + tile.getWater());
                }
                if(tool.getID() == 'F'){ //If tool was fertilizer 'F', show new fertilizer level.
                    System.out.println("Tile Fertilizer Level: " + tile.getFertilizer());
                }
            }
            else{ //If the use of tool was not successful.
                System.out.println(tool.getName() + " was not able to be used.");
            }
        }
        else if(id != 'X') System.out.println("We don't have that tool."); //If the given tool id did not have a tool assigned to it.
    }

    /**
     * Registers the farmer for the next level farmer type.
     */
    public void registerFarmer(){
        int nextType = this.farmer.getRegistration();
        int result = this.farmer.registerForLevel(nextType);

        if(result > 0){
            System.out.println("Successfully registered. You are now a " + this.farmer.getRegName() + " Farmer.");
        }
        else System.out.println("Registration was unsuccessful.");
    }

    /**
     * Updates farm and day for the next day.
     */
    public void nextDay(){
        this.farm.nextDay();
        this.day++;
    }

    /**
     * Checks if game over requirements are reached.
     * @return if the game ends or not.
     */
    public boolean checkGameOver(){
        int farmStatus = this.farm.getFarmStatus();
        if(farmStatus == 2){ //All crops are withered.
            if(!this.farmer.canAfford(this.farmer.getTool('S').getCost())) return true; //All crops are withered and the farmer can't afford using a shovel.
        }
        if(farmStatus == 1){ //There is no active crop.
            if(!this.farmer.canAfford(cropList.get(0).getSeedCost() - this.farmer.getCostReduction())) return true; //No crops are active and the farmer can't afford turnip (the cheapest root).
        }
        return false;
    }

    /**
     * Displays usable tools based on tile status.
     * @param row the tile's row.
     * @param col the tile's column.
     */
    public void displayToolOptions(int row, int col){
        Tile tile = this.farm.getTile(row, col); //Gets tile.
        if(tile != null){ //If the tile exists.
            System.out.println("\n[TOOL OPTIONS]");
            for(Tool t: this.farmer.getToolList()){
                if(t.tileCompatible(tile)) System.out.println("[" + t.getID() + "] " + t.getName() + " ($" + t.getCost() + ")");
            }
            System.out.println("[X] Back");
            this.inputPrompt();
        }
    }

    /**
     * Displayed the plantable crop name w/ cost.
     * @param row the tile's row.
     * @param col the tile's column.
     */
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

    /**
     * Displays the tile's information.
     * @param row the tile's row.
     * @param col the tile's column.
     */
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
    
    /**
     * Displays the tile options (use tool, plant, harvest, tile info).
     * @param row the tile's row.
     * @param col the tile's column.
     */
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

    /**
     * Displays all farmer details: level (xp), coins, registration, water limit increase, fert limit increase, bonus per produce, seed cost reduction.
     */
    public void displayFarmerDetails(){
        System.out.println("Level(XP): " + this.farmer.getLevel() + "(" + this.farmer.getXp() + ")");
        System.out.println("Objectcoins: " + this.farmer.getCoins());
        System.out.println("Farmer Type: " + this.farmer.getRegName() + " Farmer");
        System.out.println("Water Limit Increase: " + this.farmer.getWaterLimit());
        System.out.println("Fertilizer Limit Increase: " + this.farmer.getFertLimit());
        System.out.println("Bonus per Produce: $" + this.farmer.getBonus());
        System.out.println("Seed Cost Reduction: " + this.farmer.getCostReduction());

    }

    /**
     * Displays all farmer options.
     */
    public void displayFarmerOptions(){ //Register farmer, and more farmer details.
        System.out.println("\n[FARMER OPTIONS]");
        System.out.println("[1] Register Farmer ($" + ((this.farmer.getRegistration() + 2) * 100) + ")");
        System.out.println("[2] More Farmer Information");
        this.inputPrompt();
    }

    /**
     * Displays game name, level, coins, day.
     */
    public void displayHeader(){ //display game name, level, coins, day
        System.out.println("\n\nMy Farm");
        System.out.println("-------");
        System.out.print("Level: " + this.farmer.getLevel());
        System.out.print(" | Coins: " + this.farmer.getCoins());
        System.out.println(" | Day: " + this.day);
    }

    /**
     * Displays main options.
     */
    public void displayMainOptions(){
        System.out.println("\n[ACTIONS]");
        System.out.println("[1] Select Tile");
        System.out.println("[2] Next Day");
        System.out.println("[3] Farmer Options");
        this.inputPrompt();
    }

    /**
     * Prompts input.
     */
    public void inputPrompt(){ //input prompt
        System.out.print("\nInput: ");
    }

    /**
     * Displays the whole farm in 5 columns.
     */
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
