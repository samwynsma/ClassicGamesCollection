package GamesList.ConnectFourMaterials;

public class ConnectFourGameController {

    private String winner;
    public ConnectFourGameController() {
        winner = "";
    }

    public boolean CheckVictory(ConnectFourBoard board) {

        char[][] boardInfo = board.GetColumns();
        int[] move = board.GetMostRecentMove();
        int col = move[0];
        int row = move[1];
        char victoryToken = boardInfo[col][row];
        if(victoryToken == 'X')
        {
            winner = "Player 1";
        }
        else
        {
            winner = "Player 2";
        }
        boolean isWin = false;
        boolean isDraw = false;
        for(int colVal = 0; colVal < 7; colVal++)
        {
            for(int rowVal = 0; rowVal < 6; rowVal++)
            {
                char token = boardInfo[colVal][rowVal];
                if(token != victoryToken)
                {
                    continue;
                }
                if(rowVal < 3)
                {
                    isWin = true;
                    for(int i = rowVal; i < rowVal + 4; i++)
                    {
                        if(boardInfo[colVal][i] != token)
                        {
                            isWin = false;
                            break;
                        }
                    }
                    if(isWin)
                        break;
                }
                if(colVal <= 3)
                {
                    isWin = true;
                    for(int i = colVal; i < colVal + 4; i++)
                    {
                        if(boardInfo[i][rowVal] != token)
                        {
                            isWin = false;
                            break;
                        }
                    }
                    if(isWin)
                        break;
                }
                if(colVal >= 3)
                {
                    isWin = true;
                    for(int i = colVal; i > colVal - 4; i--)
                    {
                        if(boardInfo[i][rowVal] != token)
                        {
                            isWin = false;
                            break;
                        }
                    }
                    if(isWin)
                        break;
                }
            }
            if(isWin)
                break;
        }
        return isWin || isDraw;
    }

    public String getWinner(){
        return winner;
    }

}
