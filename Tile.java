public class Tile 
{
    private int x;                      // column on board
    private int y;                      // row on board
    private int num;                    // number of bombs in adjacent tiles (adj)
    private boolean hasBomb;            // whether tile contains a bomb
    private boolean hasBeenSelected;    // whether tile has been selected
    private boolean hasFlag;            // whether tile has a flag
    private Tile[] adj;                 // array of adjacent tiles, in order: {uL, uC, uR, mL, mR, bL, bC, bR}


    /** Creates Tile object with given x-coordinate, y-coordinate, and bomb state. */
    public Tile(int x, int y, boolean bomb)
    {
        this.x = x;
        this.y = y;
        hasBomb = bomb;

        num = 0;
        hasBeenSelected = false;
        hasFlag = false;
        adj = null;
    }


    /** Passes adjacent Tiles to current Tile and counts how many are bombs. */
    public void initialize(Tile uL, Tile uC, Tile uR, 
                           Tile mL, /* .. */ Tile mR, 
                           Tile bL, Tile bC, Tile bR)
    {
        if ( ! hasBomb )   // bombs don't need num
        {
            Tile[] temp = {uL, uC, uR, mL, mR, bL, bC, bR};
            adj = temp;
            temp = null;

            for ( Tile t : adj ) {
                // increase num for each tile in adj that is extant (i.e., not null) and a bomb
                if ( t != null && t.checkBomb() ) { num++; } 
            }
        }
    }


    public int getX() { return x; }
    public int getY() { return y; }
    public int getNum() { return num; }

    public boolean checkBomb() { return hasBomb; }
    public boolean hasSelection() { return hasBeenSelected; }
    public boolean checkFlag() { return hasFlag; }


    /** Selects Tile. Returns 0 if Tile has bomb, 1 if no bomb and not yet selected, 
        -1 if no bomb and already selected. */
    public int select()
    {
        if ( hasBomb ) { return 0; }
        else if ( ! hasBeenSelected ) { return 1; }
        else { return -1; }
    }


    /** Toggles flag. Tile is given flag if it does not have one, and vice versa. */
    public void toggleFlag() { 
        hasFlag = ! hasFlag;
    }


    /* TO-DO: Add method that automatically checks adjacent tiles (for starting move) */
}
