package Crop;
public class Carrot extends Crop {
    public Carrot () {
        super();

        super.name = "Carrot";
        super.id = 'C';
        super.type = "Root";
        super.harvestTime = 3;
        super.water = 1;
        super.fertilizer = 0;
        super.bonus = 1;
        super.prodMin = 1;
        super.prodMax = 2;
        super.seedCost = 19;
        super.sellPrice = 9;
        super.xp = 7.5;
        super.tree = false;
    }
}
