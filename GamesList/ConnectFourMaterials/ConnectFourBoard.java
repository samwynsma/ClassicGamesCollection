package GamesList.ConnectFourMaterials;

public class ConnectFourBoard {

    public ConnectFourBoard()
    {
        char[][] columns = new char[7][6];
        for(int i = 0; i < columns.length; i++)
        {
            for(int j = 0; j < columns[i].length; j++)
            {
                columns[i][j] = '-';
            }
        }
    }

    public void DropIntoColumn(String columnString, boolean player) {
        int colVal = -1;
        try{
            colVal = Integer.parseInt(columnString);
        }
        catch(Exception e)
        {
            System.out.println("Invalid input. Please type in a number between 1 and 7.");
        }
    }

}
