package GamesList.ConnectFourMaterials;

public class ConnectFourBoard {

    private char[][] columns;
    private int[] dropLocations;
    private int[] mostRecentMove;

    public ConnectFourBoard()
    {
        columns = new char[7][6];
        dropLocations = new int[7];
        mostRecentMove = new int[2];
        for(int i = 0; i < columns.length; i++)
        {
            for(int j = 0; j < columns[i].length; j++)
            {
                columns[i][j] = '-';
            }
            dropLocations[i] = 5;
        }
    }

    public boolean DropIntoColumn(String columnString, boolean player) {
        int colVal = -1;
        try{
            colVal = Integer.parseInt(columnString);
            if(colVal > 7 || colVal < 1)
                throw new Exception("Out of range");
        }
        catch(Exception e)
        {
            System.out.println("Invalid input. Please type in a number between 1 and 7.");
            return player;
        }

        if(dropLocations[colVal-1] < 0)
        {
            System.out.println("Invalid input. Column is already full and cannot be added to.");
        }
        else if(player)
        {
            columns[colVal-1][dropLocations[colVal-1]] = 'X';
            mostRecentMove[0] = colVal-1;
            mostRecentMove[1] = dropLocations[colVal-1];
            dropLocations[colVal-1]--;
            player = false;
        }
        else
        {
            columns[colVal-1][dropLocations[colVal-1]] = 'O';
            mostRecentMove[0] = colVal-1;
            mostRecentMove[1] = dropLocations[colVal-1];
            dropLocations[colVal-1]--;
            player = true;
        }
        return player;
    }

    public void DisplayBoard() {
        StringBuilder board = new StringBuilder();
        for(int j = 0; j < 6; j++)
        {
            for(int i = 0; i < 7; i++)
            {
                board.append(columns[i][j]);
            }
            board.append("\n");
        }
        System.out.print(board);
    }

    public char[][] GetColumns(){
        return columns;
    }

    public int[] GetMostRecentMove(){
        return mostRecentMove;
    }

}
