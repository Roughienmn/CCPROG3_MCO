/**
 * An abstract class representing Flower type Crops.
 * Gets sold for more determined by a multiplier.
 */
public abstract class Flower extends Crop {
    /**
     * Constructor of the class Flower. Parameters define the Flower's values.
     * @param name          the flower's name
     * @param id            the flower's unique character id
     * @param harvestTime   the amount of days needed for the flower to be harvested
     * @param water         the amount of water needed by the flower
     * @param fertilizer    the amount of fertilizer needed by the flower
     * @param seedCost      the cost of planting the flower
     * @param sellPrice     the selling price of the flower
     * @param xp            the experience yield from harvesting the flower
     */
    public Flower(String name, char id, int harvestTime, int water, int fertilizer,
                    int seedCost, int sellPrice, double xp){
        super(name, id, harvestTime, water, fertilizer, 1, 1, 1, seedCost, sellPrice,
        xp);
    }
}
