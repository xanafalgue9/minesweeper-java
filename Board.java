import java.util.Random;
import java.lang.StringBuilder;

public class Board 
{
    private Tile[][] board;                 // 2D array containing Tile objects that constitute board
    private int length;                     // number of rows in board
    private int width;                      // number of columns in board
    private int difficulty;                 // integer representing difficulty; 0 = easy, 1 = medium, 2 = hard
    private int remainingBombs;             // integer representing bombs yet to be found.
    // should be total bombs? or maybe number of flags that can be used? TO-DO


    /** Creates and fills with Tiles a Board of given size and difficulty. */
    public Board(int width, int length, int difficulty)
    {
        if ( difficulty < 0 || difficulty > 2 ) {   // default to easy difficulty if given invalid int
            this.difficulty = 0; 
        }
        else { this.difficulty = difficulty; }

        // TO-DO: make length and width dependent on difficulty?
        board = new Tile[length][width];
        this.length = board.length;
        this.width = board[0].length;

        // fill board with tiles
        createTiles();

        // for each tile on board, gets adj tiles and calculates num
        initializeTiles();
    }


    /** Creates a Tile at each location on Board, randomizing the bomb state of each. */
    private void createTiles()
    {
        Random rand = new Random(); // RNG used for determining bomb state

        // iterate through each tile in each row
        for (int y = 0; y < length; y++)
        {
            for (int x = 0; x < width; x++)
            {
                boolean bomb = ( rand.nextInt(7) == 0 );        // 1/7 chance of being bomb
                board[y][x] = new Tile(x, y, bomb);             // create tile at current location
                
                // TO-DO: make number of bombs dependent on difficulty?
            }
        }
    }


    /** Checks whether a Tile exists at given coordinates. If so, returns Tile. Otherwise, returns null. */
    private Tile getTile(int x, int y)
    {
        if ( ( x >= 0 && x < width ) && ( y >= 0 && y < length ) )
        {
            return board[y][x];
        }
        else { return null; }
    }


    /** Initializes all Tiles on Board by passing references to adjacent Tiles and using those 
        to calculate number of bombs in periphery. */
    private void initializeTiles()
    {
        // iterate through each tile in each row
        for (int y = 0; y < length; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile t = board[y][x];

                Tile uL = getTile(x-1, y-1);    // upper left tile (one left, one up)
                Tile uC = getTile(x, y-1);      // upper center tile (one up)
                Tile uR = getTile(x+1, y-1);    // upper right tile (one right, one up)
                Tile cL = getTile(x-1, y);      // center left tile (one left)
                Tile cR = getTile(x+1, y);      // center right tile (one right)
                Tile bL = getTile(x-1, y+1);    // bottom left tile (one left, one down)
                Tile bC = getTile(x, y+1);      // bottom center tile (one down)
                Tile bR = getTile(x+1, y+1);    // bottom right tile (one right, one down)

                t.initialize(uL, uC, uR, cL, cR, bL, bC, bR);
            }
        }
    }


    /** Returns String representation of board. */
    public String getLayout()
    {
        StringBuilder layout = new StringBuilder( ( width * 2 ) * length ); // ( ((width*2)-1) * length ) + width

        for (int y = 0; y < length; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Tile t = board[y][x];

                if ( t.checkBomb() ) { layout.append("@"); }            // use @ if tile has bomb
                else if ( t.checkFlag() ) { layout.append(">"); }       // use > if tile has flag
                else if ( t.hasSelection() ) { layout.append("o"); }    // use o if tile has been selected !!FOR TESTING PURPOSES!!
                else { layout.append( t.getNum() ); }                       // else use number
                //else { layout.append("*"); }                            // use * if tile has not yet been selected

                if ( x != width-1 ) { layout.append(" "); }             // if column is not last in row, append space
                else { layout.append("\n"); }                           // if column is at end of row, add new line
            }
        }

        return layout.toString();
    }


    /* TO-DO: create method to translate given positions (e.g., in form 'A4') to coordinates (e.g., in form 'y=0, x=3') */
    /* TO-DO: change 2D array structure for board to tree-like structure (only maintain reference to first tile) */
}
