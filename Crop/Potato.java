package Crop;
public class Potato extends Crop {
    public Potato () {
        super();

        super.name = "Potato";
        super.id = 'P';
        super.type = "Root";
        super.harvestTime = 5;
        super.water = 3;
        super.fertilizer = 1;
        super.bonus = 1;
        super.prodMin = 1;
        super.prodMax = 10;
        super.seedCost = 20;
        super.sellPrice = 3;
        super.xp = 12.5;
        super.tree = false;
    }
}
