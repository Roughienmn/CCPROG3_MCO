public class Tool {
    private String name;
    private int cost;
    private double xp;
    private int tileRequirement;
    private int tileChange; //tile type that tool makes it become

    public Tool(String name, int cost, double xp, int tileRequirement, int tileChange){
        this.name = name;
        this.cost = cost;
        this.xp = xp;
        this.tileRequirement = tileRequirement;
        this.tileChange = tileChange;
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

    public int getTileRequirement(){
        return this.tileRequirement;
    }

    public int getTileChange(){
        return this.tileChange;
    }
}
