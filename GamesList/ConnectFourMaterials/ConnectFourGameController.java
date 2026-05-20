package GamesList.ConnectFourMaterials;

public class ConnectFourGameController {

    private String winner;
    public ConnectFourGameController() {
        winner = "";
    }

    public boolean CheckVictory(ConnectFourBoard board, boolean isPlayerOne) {

        char[][] boardInfo = board.GetColumns();
        int[] move = board.GetMostRecentMove();
        int col = move[0];
        int row = move[1];
        char victoryToken = boardInfo[col][row];
        if(row < 3)
        {
            boolean isWin = true;
            for(int i = row; i < row + 4; i++)
            {
                char token = boardInfo[col][i];
                if(token != victoryToken)
                {
                    isWin = false;
                    break;
                }
            }
            if(isWin)
            {
                return true;
            }
        }
        if(col >= 3)
        {
            boolean isWin = true;
            for(int i = col; i > col - 4; i--)
            {
                char token = boardInfo[i][row];
                if(token != victoryToken)
                {
                    isWin = false;
                    break;
                }
            }
            if(isWin)
            {
                return true;
            }
        }
        if(col <= 3)
        {
            boolean isWin = true;
            for(int i = col; i < col + 4; i++)
            {
                char token = boardInfo[i][row];
                if(token != victoryToken)
                {
                    isWin = false;
                    break;
                }
            }
            if(isWin)
            {
                return true;
            }
        }
        return false;
    }

    public String getWinner(){
        return winner;
    }

}
