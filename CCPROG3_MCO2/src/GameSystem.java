import java.util.ArrayList;

/**
 * This class contains the game's logic and entire game process.
 * Tools, Crops, Player Information, and other details are stored and processed here.
 * Is also able to display via console the logic of the game.
 */
public class GameSystem {
    private ArrayList<Crop> cropList; //List of all crops available in the game
    private ArrayList<Tool> toolList; //List of all tools in the game
    private Farmer farmer; //The game's farmer
    private Farm farm; //The game's farm
    private int day; //The number of days the game has been active

    /**
     * Constructor of the class GameSystem.
     * Creates a new game, which creates a farm, a farmer, sets the day to 1,
     * and creates a copy of all the available crops and tools' information.
     * @param status an array which determines if a tile has a rock or not
     */
    public GameSystem(int status[]){
        //Creates new farm and farmer, and sets day to 1
        this.farmer = new Farmer();
        this.farm = new Farm(status);
        this.day = 1;

        //Adds crop information to crop list
        this.cropList = new ArrayList<Crop>();
        this.cropList.add(new Turnip());
        this.cropList.add(new Carrot());
        this.cropList.add(new Potato());
        this.cropList.add(new Rose());
        this.cropList.add(new Tulips());
        this.cropList.add(new Sunflower());
        this.cropList.add(new Mango());
        this.cropList.add(new Apple());

        //Adds tool information to tool List
        this.toolList = new ArrayList<Tool>();
        this.toolList.add(new Plow());
        this.toolList.add(new WateringCan());
        this.toolList.add(new Fertilizer());
        this.toolList.add(new Pickaxe());
        this.toolList.add(new Shovel());
    }

    /**
     * Generates a new Crop seed.
     * @param id the crop's id.
     * @return new seed is given id matches any of the crop ids, null if not.
     */
    public Crop generateSeed(char id){
        Crop c = null;
        switch(id){
            case 'T':
                c = new Turnip();
                break;
            case 'C':
                c = new Carrot();
                break;
            case 'P':
                c = new Potato();
                break;
            case 'R':
                c = new Rose();
                break;
            case 'L':
                c = new Tulips();
                break;
            case 'S':
                c = new Sunflower();
                break;
            case 'M':
                c = new Mango();
                break;
            case 'A':
                c = new Apple();
                break;
        }
        return c;
    }

    /**
     * Gets a Tool according to the specified id
     * @param id the id of the tool that is being selected
     * @return tool that contains the tool id, null if is not applicable
     */
    public Tool getTool(char id){
        for(Tool t: toolList){
            if(t.getID() == id) return t;
        }
        return null;
    }

    /**
     * Checks if tree can be planted on the given tile.
     * @param row the selected tile's row.
     * @param col the selected tile's column.
     * @return if tree is plantable in tile or not.
     */
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

    /**
     * Plants seed on tile given row, column, and seed id.
     * @param row the tile's row.
     * @param col the tile's column.
     * @param id the seed's id.
     */
    public void plantSeed(int row, int col, char id){
        Crop seed = generateSeed(id); //generate seed
        Tile tile = this.farm.getTile(row, col);
        if(seed != null && tile != null){ //If both the seed and tile are defined
            int result = 0;
            String s = "";

            if(!(seed instanceof Tree) || this.treePlantable(row, col)){
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
        if(seed == null && id != 'Z') System.out.println("We don't have that seed.");
        
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
        Tool tool = this.getTool(id);

        if(tool != null){
            int result = this.farmer.useTool(tool, tile);
            if(result > 0){ //If the tool use was successful.
                System.out.println(tool.getName() + " was used on Tile (" + row + ", " + col + ").");
                if(tool instanceof WateringCan) //Displays water level if watering can was used
                    System.out.println("Tile Water Level: " + tile.getWater());
                if(tool instanceof Fertilizer) //Displays fertilizer level if fertilizer was used
                    System.out.println("Tile Fertilizer Level: " + tile.getFertilizer());
            }
            else
                System.out.println(tool.getName() + "was not able to be used.");
        }
        else if(id != 'Z') System.out.println("We don't have that tool.");
    }

    /**
     * Registers the farmer for the next level farmer type.
     */
    public void registerFarmer(){
        int nextType = this.farmer.getFarmerType();
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
        if(farmStatus == 2){ //all withered
            if(!this.farmer.canAfford(this.getTool('S').getCost())) return true;
        }
        if(farmStatus == 1){ //no active crops
            if(!this.farmer.canAfford(5 - this.farmer.getCostReduction())) return true; //cant afford cheapest crop
        }
        return false;
    }

    /**
     * Prompts input.
     */
    public void inputPrompt(){ //input prompt
        System.out.print("\nInput: ");
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
                    if(this.farmer.canAfford(c.getSeedCost()) && (!(c instanceof Tree) || this.treePlantable(row, col))){
                        System.out.println("[" + c.getID() + "] " + c.getName() + " ($" + c.getSeedCost() + ")");
                    }
                }
            }
            System.out.println("[Z] Back");
            this.inputPrompt();
        }
    }

    /**
     * Displays usable tools based on tile status.
     * @param row the tile's row.
     * @param col the tile's column.
     */
    public void displayToolOptions(int row, int col){
        Tile tile = this.farm.getTile(row, col); //get tile
        if(tile != null){ //tile exists
            System.out.println("\n[TOOL OPTIONS]");
            for(Tool t: this.toolList){
                if(t.tileCompatible(tile)) System.out.println("[" + t.getID() + "] " + t.getName() + " ($" + t.getCost() + ")");
            }
            System.out.println("[Z] Back");
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
    public void displayFarmerDetails(){ //level (xp), coins, registration, waterlimit inc, fert limit inc, bonus per produce, seed csot reduction, 
        System.out.println("Level(XP): " + this.farmer.getLevel() + "(" + this.farmer.getXP() + ")");
        System.out.println("Objectcoins: " + this.farmer.getCoins());
        System.out.println("Farmer Type: " + this.farmer.getRegName() + " Farmer");
        System.out.println("Water Limit Increase: " + this.farmer.getWaterLimit());
        System.out.println("Fertilizer Limit Increase: " + this.farmer.getFerLimit());
        System.out.println("Bonus per Produce: $" + this.farmer.getBonus());
        System.out.println("Seed Cost Reduction: " + this.farmer.getCostReduction());

    }

    /**
     * Displays all farmer options.
     */
    public void displayFarmerOptions(){ //register farmer, more farmer details
        System.out.println("\n[FARMER OPTIONS]");
        System.out.println("[1] Register Farmer ($" + ((this.farmer.getFarmerType() + 2) * 100) + ")");
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
