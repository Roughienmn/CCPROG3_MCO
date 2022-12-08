package Crop;
public class Turnip extends Crop {
    public Turnip () {
        super();

        super.name = "Turnip";
        super.id = 'T';
        super.type = "Root";
        super.harvestTime = 2;
        super.water = 1;
        super.fertilizer = 0;
        super.bonus = 1;
        super.prodMin = 1;
        super.prodMax = 2;
        super.seedCost = 5;
        super.sellPrice = 6;
        super.xp = 5.0;
        super.tree = false;
    }
}
