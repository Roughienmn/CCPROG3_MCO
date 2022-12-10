import java.util.ArrayList;

/**
 * This class contains all the Tiles used in the game.
 * This class also stores and updates all Tile activity information.
 */
public class Farm {
    public ArrayList<Tile> tileList; //storage of tiles.

    /**
     * Constructor for class Farm.
     * 
     * @param status Tile status.
     */
    public Farm(int status[]){
        tileList = new ArrayList<Tile>();
        for(int i : status){
            this.tileList.add(new Tile(i));
        }
    }

    /**
     * Gets status on whether all farm tiles are withered or not,
     * or whether all farm tiles don't have active crops.
     * @return
     */
    public int getFarmStatus(){
        boolean hasActive = false; // Signifies if this farm has active crops.
        boolean allWithered = true; // Signifies if all tiles of this farm have withered crops.
        for(Tile t : this.tileList){
            if(t.getStatus() == 3) hasActive = true;
            if(t.getStatus() != 4) allWithered = false;
        }

        if(allWithered) return 2;
        if(!hasActive) return 1;
        return 0;
    }

    /**
     * Calls on nextDay(), which updates each tile according to what crops do when it's the next day.
     */
    public void nextDay(){
        for(Tile t: this.tileList){
            t.nextDay();
        }
    }

    /**
     * Gets tile based on user input.
     * @param row row of the tile.
     * @param col column of the tile.
     * @return this farm's tile based on the input row and column.
     */
    public Tile getTile(int row, int col){
        int index = ((row - 1) * 5) + col - 1;
        Tile tile = null;
        if(index < tileList.size()) tile = this.tileList.get(index);
        if (tile == null) System.out.println("Tile is out of bounds.");
        return tile;
    }

    /**
     * Gets the farm size.
     * @return this farm's size.
     */
    public int getTileCount(){
        return this.tileList.size();
    }
}
