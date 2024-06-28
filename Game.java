public class Game 
{
    public static void main(String[] args) 
    {
        Board b = new Board(10, 5, 0);
        
        System.out.println( "\n" + b.getLayout() );
    }
}
