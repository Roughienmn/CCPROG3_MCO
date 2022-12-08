package Crop;
public class Tulips extends Crop {
    public Tulips () {
        super();

        super.name = "Tulip";
        super.id = 'L';
        super.type = "Flower";
        super.harvestTime = 2;
        super.water = 2;
        super.fertilizer = 0;
        super.bonus = 1;
        super.prodMin = 1;
        super.prodMax = 1;
        super.seedCost = 10;
        super.sellPrice = 9;
        super.xp = 5;
        super.tree = false;
    }
}
