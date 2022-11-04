public class Farmer {
    private double xp;
    private int level;
    private double coins;
    private int registration;
    private int waterLimitIncrease;
    private int fertilizerLimitIncrease;
    private int bonusPerProduce;
    private int costReduction;

    public Farmer(){
        this.xp = 0;
        this.level = 0;
        this.coins = 100;
        this.registration = 0;
        this.waterLimitIncrease = 0;
        this.fertilizerLimitIncrease = 0;
        this.bonusPerProduce = 0;
        this.costReduction = 0;
    }

    public int registerForLevel(int type){
        if(this.registration < type && type <= 3){
            double fee = (type + 1) * 100;

            if(this.level >= (type * 5) && this.canAfford(fee)){
                this.registration = type;
                this.payCoins(fee);
                switch(type){
                    case 1:
                        this.bonusPerProduce = 1;
                        this.costReduction = 1;
                    break;
                    case 2:
                        this.bonusPerProduce =2;
                        this.costReduction = 2;
                        this.waterLimitIncrease = 1;
                    break;
                    case 3:
                        this.bonusPerProduce = 4;
                        this.costReduction = 3;
                        this.waterLimitIncrease = 2;
                        this.fertilizerLimitIncrease = 1;
                    break;
                }
                return 1; //successfully registered
            }
        }
        return 0; //unsuccessful register
    }

    public void addCoins(double income){
        this.coins+=income;
    }

    public void payCoins(double cost){
        this.coins-=cost;
    }

    public void addXp(double xp){
        this.xp+=xp;
        this.level = ((int) this.xp)/100;
    }

    public boolean canAfford(double cost){
        if(this.coins >= cost) return true;
        return false;
    }

    public double getXp(){
        return this.xp;
    }

    public int getLevel(){
        return this.level;
    }

    public double getCoins(){
        return this.coins;
    }

    public int getRegistration(){
        return this.registration;
    }

    public int getWaterIncrease(){
        return this.waterLimitIncrease;
    }

    public int getFertilizerIncrease(){
        return this.fertilizerLimitIncrease;
    }

    public int getBonusPerProduce(){
        return this.bonusPerProduce;
    }

    public int getCostReduction(){
        return this.costReduction;
    }
}