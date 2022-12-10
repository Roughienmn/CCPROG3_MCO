/**
 * An abstract class representing the Tools in the game
 * Has various effects on Tiles
 */
public abstract class Tool {
    private String name; //the tool's name
    private char id; //the tool's char id
    private int cost; //the cost of using the tool
    private double xp; //the experience yield from using the tool
    private int tileReq; //the required status of the tile for the tool to be used

    /**
     * Constructor for the class Tool. parameter define the tool's values.
     * 
     * @param name the tool's name
     * @param id the tool's id
     * @param cost the cost of using the tool
     * @param xp the experience yield from using the tool
     * @param tileReq the required status of the tile for the tool to be used
     */
    public Tool(String name, char id, int cost, double xp, int tileReq){
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.xp = xp;
        this.tileReq = tileReq;
    }

    /**
     * Checks if the tool can be used on the tile.
     * 
     * @param tile the tile that the tool will be used on
     * @return if the tool can be used on the tile
     */
    public boolean tileCompatible(Tile tile){
        if(tile.getStatus() == this.tileReq)
            return true;
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
    public double getXP(){
        return this.xp;
    }

    /**
     * abstract function the represents the change in the tile
     * @param tile the tile that will be changed
     * @return 1 if the tool was successfully used on the tile, 0 if not
     */
    public abstract int updateTile(Tile tile);
}
