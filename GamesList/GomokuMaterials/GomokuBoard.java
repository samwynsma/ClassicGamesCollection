package GamesList.GomokuMaterials;

public class GomokuBoard {
    private char[][] gameBoard;
    private int[] mostRecentMove;

    public GomokuBoard()
    {
        gameBoard = new char[15][15];
        mostRecentMove = new int[2];
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                gameBoard[i][j] = '.';
            }
        }
    }

    public boolean PlaceTile(String column, String row, boolean player)
    {
        int colVal = -1;
        int rowVal = -1;
        try
        {
            colVal = Integer.parseInt(column);
            if(colVal < 1 || colVal > 15)
                throw new NumberFormatException("Column must be between 1 and 15");
            rowVal = Integer.parseInt(row);
            if(rowVal < 1 || rowVal > 15)
                throw new NumberFormatException("Row must be between 1 and 15");
        }
        catch(Exception e)
        {
            System.out.println("Invalid input. Please type in a number between 1 and 7.");
            return player;
        }

        if(gameBoard[colVal][rowVal] != '.')
        {
            System.out.println("Invalid input. There is already a tile there.");
        }
        else if(player)
        {
            gameBoard[colVal][rowVal] = 'X';
            mostRecentMove[0] = colVal;
            mostRecentMove[1] = rowVal;
            player = false;
        }
        else
        {
            gameBoard[colVal][rowVal] = 'O';
            mostRecentMove[0] = colVal;
            mostRecentMove[1] = rowVal;
            player = true;
        }
        return player;
    }

    public void DisplayBoard()
    {
        StringBuilder board = new StringBuilder("");
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                board.append(gameBoard[i][j]);
            }
            board.append("\n");
        }
        System.out.print(board);
    }

    public char[][] GetBoard()
    {
        return gameBoard;
    }

    public int[] GetMostRecentMove()
    {
        return mostRecentMove;
    }
}
