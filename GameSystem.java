import java.util.ArrayList;
import java.util.Arrays;

public class GameSystem {
    private ArrayList<Tool> toolList;
    private Farmer farmer;
    private Farm farm;
    private ArrayList<Crop> cropReferenceList;

    //crop data
    private String cropNameArr[] = {"Turnip", "Carrot", "Potato", "Rose", "Tulips", "Sunflower", "Mango", "Apple"};
    private String cropTypeArr[] = {"Root", "Root", "Root", "Flower", "Flower", "Flower", "Fruit Tree", "Fruit Tree"};
    private int harvestTimeArr[] = {2, 3, 5, 1, 2, 3, 10, 10};
    private int waterNeedsArr[] = {1, 1, 3, 1, 2, 2, 7};
    private int ferilizerNeedsArr[] = {0, 0, 1, 0, 0, 1, 4, 5};
    private int bonusLimitArr[] = {1, 1, 1, 1, 1, 1, 0, 0};
    private int prodMinArr[] = {1, 1, 1, 1, 1, 1, 5, 10};
    private int prodMaxArr[] = {2, 2, 10, 1, 1, 1, 15, 15};
    private int seedCostArr[] = {5, 10, 20, 5, 10, 20, 100, 200};
    private int sellPriceArr[] = {6, 9, 3, 5, 9, 19, 8, 5};
    private double cropXpArr[] = {5, 7.5, 12.5, 2.5, 5, 7.5, 25, 25};

    //tool data
    private String toolNameArr[] = {"Plow", "Watering Can", "Fertilizer", "Pickaxe", "Shovel"};
    private int toolCostArr[] = {0, 0, 10, 50, 7};
    private double toolXpArr[] = {0.5, 0.5, 4, 15, 2};
    private int tileRequirementArr[] = {1, 3, 3, 0, 4};
    private int tileChangeArr[] = {2, 3, 3, 1, 1};

    public GameSystem(ArrayList<Integer> status){
        for(int i = 0; i < 5; i++){
            toolList.add(new Tool(toolNameArr[i], toolCostArr[i], toolXpArr[i], tileRequirementArr[i], tileChangeArr[i]));
        }
        for(int i = 0; i < 8; i++){
            cropReferenceList.add(new Crop(cropNameArr[i], cropTypeArr[i], harvestTimeArr[i], waterNeedsArr[i], ferilizerNeedsArr[i], 
            bonusLimitArr[i], prodMinArr[i], prodMaxArr[i], seedCostArr[i], sellPriceArr[i], cropXpArr[i], i));
        }

        farmer = new Farmer();
        farm = new Farm(status);
    }

    public Crop generateSeed(String name){
        int i = Arrays.asList(this.cropNameArr).indexOf(name);

        Crop seed = new Crop(cropNameArr[i], cropTypeArr[i], harvestTimeArr[i], waterNeedsArr[i], ferilizerNeedsArr[i], 
        bonusLimitArr[i], prodMinArr[i], prodMaxArr[i], seedCostArr[i], sellPriceArr[i], cropXpArr[i], i);

        return seed;
    }

    public void nextDay(){
        this.farm.nextDay();
    }

    public void plantSeed(Tile tile, Crop seed){

    }

    public void harvestTile(Tile tile){
        if(tile.isHarvestable()){
            //get crop data
            Crop crop = tile.getCrop();
            int maxWater = crop.getWater() + crop.getBonusLimit() + this.farmer.getWaterIncrease();
            int maxFertilizer = crop.getFertilizer() + crop.getBonusLimit() + this.farmer.getFertilizerIncrease();

            //compute income
            int produce = crop.generateProduce();
            double harvestTotal = produce * (crop.getSellPrice() + this.farmer.getBonusPerProduce());
            double waterBonus = harvestTotal * 0.2 * Math.min(maxWater - 1, tile.getWater() - 1);
            double fertilizerBonus = harvestTotal * 0.5 * Math.min(maxFertilizer, tile.getFertilizer());

            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (crop.getType().equals("Flower")) finalHarvestPrice = finalHarvestPrice * 1.1;

            //add income and xp
            farmer.addCoins(finalHarvestPrice);
            farmer.addXp(crop.getXp());
            tile.resetTile();
        }
    }

    public void useTool(Tile tile, Tool tool){

    }

    public void displayHeader(){

    }

    public void displayPossibleActions(){ //displays actions possible on tile

    }

    public void displayUsableTools(){

    }

    public void displayPlantableCrops(){

    }

    public static void main(String[] args){

    }
}
