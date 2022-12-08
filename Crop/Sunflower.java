package Crop;
public class Sunflower extends Crop {
    public Sunflower () {
        super();

        super.name = "Sunflower";
        super.id = 'S';
        super.type = "Flower";
        super.harvestTime = 3;
        super.water = 2;
        super.fertilizer = 1;
        super.bonus = 1;
        super.prodMin = 1;
        super.prodMax = 1;
        super.seedCost = 20;
        super.sellPrice = 19;
        super.xp = 7.5;
        super.tree = false;
    }
}
