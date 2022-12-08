package Crop;
public class Apple extends Crop {
    public Apple () {
        super();
        
        super.name = "Apple";
        super.id = 'A';
        super.type = "Fruit Tree";
        super.harvestTime = 10;
        super.water = 7;
        super.fertilizer = 5;
        super.bonus = 0;
        super.prodMin = 10;
        super.prodMax = 15;
        super.seedCost = 200;
        super.sellPrice = 5;
        super.xp = 25;
        super.tree = true;
    }
}
