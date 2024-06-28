public class Board 
{
    private Tile[][] board;
    private int length;
    private int width;
    private int difficulty;

    public Board(int length, int width, int difficulty)
    {
        this.difficulty = difficulty;

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
}
