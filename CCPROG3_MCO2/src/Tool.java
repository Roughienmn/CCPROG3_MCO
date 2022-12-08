public abstract class Tool {
    private String name;
    private char id;
    private int cost;
    private double xp;
    private int tileReq;

    public Tool(String name, char id, int cost, double xp, int tileReq){
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.xp = xp;
        this.tileReq = tileReq;
    }

    public boolean tileCompatible(Tile tile){
        if(tile.getStatus() == this.tileReq)
            return true;
        return false;
    }

    public String getName(){
        return this.name;
    }

    public char getID(){
        return this.id;
    }

    public int getCost(){
        return this.cost;
    }

    public double getXP(){
        return this.xp;
    }

    public abstract int updateTile(Tile tile);
}
