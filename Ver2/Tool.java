package Ver2;

public class Tool {
    private String name; //bruh
    private char id; //index/id 
    private int cost; //cost per use
    private double xp; //xp
    private int tileReq; //tiles it can be used on
    private int tileChange; //tile tool makes it become

    public Tool(String name, char id, int cost, double xp, int tileReq, int tileChange){
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.xp = xp;
        this.tileReq = tileReq;
        this.tileChange = tileChange;
    }

    //checks if the tool can be used depending on the status of the tile
    public boolean tileCompatible(Tile tile){
        if(tile.getStatus() == this.tileReq) return true;
        if(this.name.equals("Shovel")) return true;
        if(tile.getStatus() > 1){
            if(this.name.equals("Watering Can") || this.name.equals("Fertilizer")) return true;
        }
        return false;
    }

    //returns tool name
    public String getName(){
        return this.name;
    }

    //returns tool id
    public char getID(){
        return this.id;
    }

    //returns tool use cost
    public int getCost(){
        return this.cost;
    }

    //returns tool use xp gain
    public double getXp(){
        return this.xp;
    }

    //returns tile that tool makes tile become
    public int getChange(){
        return this.tileChange;
    }
}
