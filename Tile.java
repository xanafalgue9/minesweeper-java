public class Tile 
{
    private int x;                      // column on board
    private int y;                      // row on board
    private int num;                    // number of bombs in adjacent tiles (adj)
    private boolean hasBomb;            // whether tile contains a bomb
    private boolean hasBeenSelected;    // whether tile has been selected
    private boolean hasFlag;            // whether tile has a flag
    private Tile[] adj;                 // array of adjacent tiles

    /* Creates Tile object with given x-coordinate, y-coordinate, and bomb state. */
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

    /* Passes adjacent Tiles to current Tile and counts how many are bombs. */
    public void initialize(Tile uL, Tile uC, Tile uR, 
                           Tile mL, Tile mR, 
                           Tile bL, Tile bC, Tile bR)
    {
        if ( ! bomb )
        {
            Tile[] temp = {uL, uC, uR, mL, mR, bL, bC, bR};
            adj = temp;
            temp = null;

            for ( Tile t : adj ) {
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

    /* TO-DO: Add method that automatically checks adjacent tiles (for starting move) */
}
