package Crop;
public class Rose extends Crop {
    public Rose () {
        super();

        super.name = "Rose";
        super.id = 'R';
        super.type = "Flower";
        super.harvestTime = 1;
        super.water = 1;
        super.fertilizer = 0;
        super.bonus = 1;
        super.prodMin = 1;
        super.prodMax = 1;
        super.seedCost = 5;
        super.sellPrice = 5;
        super.xp = 2.5;
        super.tree = false;
    }
}
