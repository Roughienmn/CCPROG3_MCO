package Ver2;
import java.util.ArrayList;

public class Farm {
    public ArrayList<Tile> tileList; //tile storage

    public Farm(int status[]){
        tileList = new ArrayList<Tile>();
        for(int i : status){
            this.tileList.add(new Tile(i));
        }
    }

    //returns if farm tiles are all withered or doesn't have any active crops
    public int getFarmStatus(){
        boolean hasActive = false; //has active crops
        boolean allWithered = true; //all tiles have a withered crop
        for(Tile t : this.tileList){
            if(t.getStatus() == 3) hasActive = true;
            if(t.getStatus() != 4) allWithered = false;
        }

        if(allWithered) return 2;
        if(!hasActive) return 1;
        return 0;
    }

    //does what plants do per tile when next day
    public void nextDay(){
        for(Tile t: this.tileList){
            t.nextDay();
        }
    }

    //returns tile based on input row and col
    public Tile getTile(int row, int col){
        int index = ((row - 1) * 5) + col - 1;
        Tile tile = null;
        if(index < tileList.size()) tile = this.tileList.get(index);
        if (tile == null) System.out.println("Tile is out of bounds.");
        return tile;
    }

    //returns farm size
    public int getTileCount(){
        return this.tileList.size();
    }
}
