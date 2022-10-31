package CCPROG3_MCO;

public class Tool {
    private String name;
    private int cost;
    private double xp;
    private int tileRequirement;
    private int newTile; //tile type that the tool makes it become

    public Tool(String name, int cost, double xp, int tileRequirement, int newTile){
        this.name = name.toLowerCase();
        this.cost = cost;
        this.xp = xp;
        this.tileRequirement = tileRequirement;
        this.newTile = newTile;
    }

    public String getName(){
        return this.name;
    }

    public int getCost(){
        return this.cost;
    }

    public double getXp(){
        return this.xp;
    }

    public int getTile(){
        return this.tileRequirement;
    }

    public int getNewTile(){
        return this.newTile;
    }
}
