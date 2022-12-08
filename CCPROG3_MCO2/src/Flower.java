public abstract class Flower extends Crop {
    public Flower(String name, char id, int harvestTime, int water, int fertilizer,
                    int seedCost, int sellPrice, double xp){
        super(name, id, harvestTime, water, fertilizer, 1, 1, 1, seedCost, sellPrice,
        xp);
    }
}
