public class Board 
{
    private Tile[][] board;     // 2D array containing Tile objects that constitute board
    private int length;         // number of rows in board
    private int width;          // number of columns in board
    private int difficulty;     // integer representing difficulty; 0 = easy, 1 = medium, 2 = hard

    public Board(int length, int width, int difficulty)
    {
        if ( difficulty < 0 || difficulty > 2 ) {   // default to easy difficulty if given invalid int
            this.difficulty = 0; 
        }
        else { this.difficulty = difficulty; }

        // TO-DO: make length and width dependent on difficulty?
        board = new Tile[width][length];
        this.width = board.length;
        this.length = board[0].length;

        for (int y = 0; y < board.length; y++)
        {
            for (int x = 0; x < board[0].length; x++)
            {
                board[y][x] = new Tile(x, y, false);
                // TO-DO: randomize bomb state
                // TO-DO: make number of bombs dependent on difficulty?
            }
        }
    }

    /* TO-DO: create method to translate given positions (e.g., in form 'A4') to coordinates (e.g., in form 'y=0, x=3') */
    /* TO-DO: change 2D array structure for board to tree-like structure (only maintain reference to first tile) */
}
