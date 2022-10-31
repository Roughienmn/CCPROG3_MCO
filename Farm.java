package CCPROG3_MCO;
import java.util.ArrayList;

public class Farm {
    private ArrayList<Tile> tileList;
    private int day;

    public Farm(ArrayList<Integer> status){
        for(int i : status){
            this.tileList.add(new Tile(i));
        }
    }

    public int checkFarmStatus(){
        boolean hasActive = false;
        boolean allWithered = true;
        for(Tile t : this.tileList){
            if(t.getStatus() == 3) hasActive = true;
            if(t.getStatus() != 4) allWithered = false;
        }
        
        if(allWithered) return 2;
        if(!hasActive) return 1;
        return 0;
    }

    public void nextDay(){
        for (Tile t: this.tileList){
            t.nextDay();
        }
        day++;
    }

    public Tile getTile(int index){
        return this.tileList.get(index-1);
    }

    public Tile getTile(int row, int col){
        int index = (row-1)*5;
        index+=col-1;
        return this.tileList.get(index);
    }

    public int getDay(){
        return day;
    }
}
