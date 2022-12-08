import Farm.Tile;
package Tool;
public abstract class Tool {
    protected String name; //bruh
    protected char id; //index/id 
    protected int cost; //cost per use
    protected double xp; //xp
    protected int tileReq; //tiles it can be used on
    protected int tileChange; //tile tool makes it become

    public Tool () {
    }

    //checks if the tool can be used depending on the status of the tile
    public boolean tileCompatible (Tile tile){
        if(tile.getStatus() == this.tileReq) return true;
        if(this.name.equals("Shovel")) return true;
        if(tile.getStatus() > 1){
            if(this.name.equals("Watering Can") || this.name.equals("Fertilizer")) return true;
        }
        return false;
    }

    public void toolFunction (Tile tile) {
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
