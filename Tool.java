public class Tool {
    private String name; /** This tool's name. */
    private char id; /** This tool's index/id. */
    private int cost; /** This tool's cost per use. */
    private double xp; /** Experience gained from using this tool. */
    private int tileReq; /** Tiles on which this tool can be used on. */
    private int tileChange; /** Tile status after this tool is used. */

    /**
     * Constructor of class Tool. parameters define the tool's values for the variables declared above.
     * This constructor is used to create the different tools which have different properties/characteristics.
     * @param name 
     * @param id
     * @param cost
     * @param xp
     * @param tileReq
     * @param tileChange
     */
    public Tool(String name, char id, int cost, double xp, int tileReq, int tileChange){
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.xp = xp;
        this.tileReq = tileReq;
        this.tileChange = tileChange;
    }

    /**
     * Checks if the tool can be used depending on the tile status.
     * @param tile the tile on which this tool is to be used.
     * @return if the tool can be used or not.
     */
    public boolean tileCompatible(Tile tile){
        if(tile.getStatus() == this.tileReq) return true;
        if(this.name.equals("Shovel")) return true;
        if(tile.getStatus() > 1){
            if(this.name.equals("Watering Can") || this.name.equals("Fertilizer")) return true;
        }
        return false;
    }

    /**
     * Gets the tool name.
     * @return this tool's name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the tool id.
     * @return this tool's id.
     */
    public char getID(){
        return this.id;
    }

    /**
     * Gets the cost to use the tool.
     * @return this tool's use cost.
     */
    public int getCost(){
        return this.cost;
    }

    /**
     * Gets the experience gained from using the tool.
     * @return this tool's use XP gain.
     */
    public double getXp(){
        return this.xp;
    }

    /**
     * Gets the tile status which the tile have after the tool is used.
     * @return the tile's supposed status after this tool's use.
     */
    public int getChange(){
        return this.tileChange;
    }
}
