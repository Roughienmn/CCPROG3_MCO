package Ver2;
import java.util.ArrayList;

public class Farm {
    public ArrayList<Tile> tileList;

    public Farm(int status[]){
        tileList = new ArrayList<Tile>();
        for(int i : status){
            this.tileList.add(new Tile(i));
        }
    }

    public int getFarmStatus(){
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
        for(Tile t: this.tileList){
            t.nextDay();
        }
    }

    public Tile getTile(int row, int col){
        int index = ((row - 1) * 5) + col - 1;
        Tile tile = this.tileList.get(index);
        if (tile == null) System.out.println("Tile is out of bounds.");
        return tile;
    }

    public int getTileCount(){
        return this.tileList.size();
    }
}
