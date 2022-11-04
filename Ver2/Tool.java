package Ver2;

public class Tool {
    private String name;
    private char id;
    private int cost;
    private double xp;
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

    public boolean tileCompatible(Tile tile){
        if(tile.getStatus() == this.tileReq) return true;
        if(this.name.equals("Shovel")) return true;
        if(tile.getStatus() > 1){
            if(this.name.equals("Watering Can") || this.name.equals("Fertilizer")) return true;
        }
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

    public double getXp(){
        return this.xp;
    }

    public int getChange(){
        return this.tileChange;
    }
}
