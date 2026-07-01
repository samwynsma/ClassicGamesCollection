package GamesList.GomokuMaterials;

public class GomokuGameController {

    private String winner;
    private int numToWin;
    public GomokuGameController()
    {
        winner = "";
        numToWin = 5;
    }

    public void SetWinCondition(int winNumber)
    {
        numToWin = winNumber;
    }

    public boolean CheckVictory(GomokuBoard board)
    {
        char[][] boardInfo = board.GetBoard();
        int[] move = board.GetMostRecentMove();
        int row = move[0];
        int col = move[1];
        char victoryToken = boardInfo[row][col];
        switch (victoryToken) {
            case 'X' -> winner = "Player 1";
            case 'O' -> winner = "Player 2";
            default -> {
                return false;
            }
        }
        boolean isWin = false;
        boolean isDraw = true;

        for(int i = 0; i < boardInfo.length; i++)
        {
            for(int j = 0; j < boardInfo[i].length; j++)
            {
                if(boardInfo[i][j] == '.')
                {
                    isDraw = false;
                    continue;
                }
                isWin |= checkVertical(boardInfo, row, col, victoryToken);
                isWin |= checkHorizontal(boardInfo, row, col, victoryToken);
                isWin |= checkDiagonal(boardInfo, row, col, victoryToken, true);
                isWin |= checkDiagonal(boardInfo, row, col, victoryToken, false);
            }
            if(!isDraw && isWin)
                break;
        }

        if(!isWin)
        {
            winner = "Nobody";
        }
        return isDraw || isWin;
    }

    public boolean checkVertical(char[][] board, int row, int col, char token)
    {
        if(row + numToWin >= 15)
            return false;

        for(int i = 0; i < numToWin; i++)
        {
            if(board[row+i][col] != token)
                return false;
        }
        return true;
    }

    public boolean checkHorizontal(char[][] board, int row, int col, char token)
    {
        if(col + numToWin >= 15)
            return false;

        for(int i = 0; i < numToWin; i++)
        {
            if(board[row][col+i] != token)
                return false;
        }
        return true;

    }

    public boolean checkDiagonal(char[][] board, int row, int col, char token, boolean moveRight)
    {
        if(row + numToWin >= 15)
            return false;
        if(moveRight)
        {
            if(col + numToWin >= 15)
                return false;
        }
        else if(col < numToWin)
            return false;

        for(int i = 0; i < numToWin; i++)
        {
            if(moveRight)
            {
                if(board[row+i][col+i] != token)
                    return false;
            }
            else
            {
                if(board[row+i][col-i] != token)
                    return false;
            }
        }
        return true;
    }

    public String getWinner(){
        return winner;
    }
}
