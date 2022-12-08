package Crop;
public class Mango extends Crop {
    public Mango () {
        super();

        super.name = "Mango";
        super.id = 'M';
        super.type = "Fruit Tree";
        super.harvestTime = 10;
        super.water = 7;
        super.fertilizer = 4;
        super.bonus = 0;
        super.prodMin = 5;
        super.prodMax = 15;
        super.seedCost = 100;
        super.sellPrice = 8;
        super.xp = 25;
        super.tree = true;
    }
}
