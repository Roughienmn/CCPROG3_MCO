public abstract class RootCrop extends Crop {
    public RootCrop(String name, char id, int harvestTime, int water, int fertilizer, int prodMax,
                    int seedCost, int sellPrice, double xp){
        super(name, id, harvestTime, water, fertilizer, 1, 1, prodMax, seedCost, sellPrice, xp);
    }
}
