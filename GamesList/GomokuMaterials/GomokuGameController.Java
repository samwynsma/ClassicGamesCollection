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
                    break;
                }
            }
            if(!isDraw)
                break;
        }

        isWin |= checkVertical(boardInfo, row, col, victoryToken, true);

        if(!isWin)
        {
            winner = "Nobody";
        }
        return isDraw || isWin;
    }

    public boolean checkVertical(char[][] board, int row, int col, char token, boolean moveUp)
    {
        if(moveUp)
        {
            if(row < numToWin)
            {
                return false;
            }
        }
        else if(row + numToWin >= 15)
        {
            return false;
        }

        for(int i = 0; i < numToWin; i++)
        {
            if(moveUp)
            {
                if(board[row-i][col] != token)
                    return false;
            }
            else
            {
                if(board[row+i][col] != token)
                    return false;
            }
        }
        return true;
    }

    public boolean checkHorizontal(char[][] board, int row, int col, char token, boolean moveRight)
    {
        if(moveRight)
        {
            if(col + numToWin >= 15)
            {
                return false;
            }
        }
        else if(col < numToWin)
        {
            return false;
        }
        for(int i = 0; i < numToWin; i++)
        {
            if(moveRight)
            {
                if(board[row][col+i] != token)
                    return false;
            }
            else
            {
                if(board[row][col-i] != token)
                    return false;
            }
        }
        return true;

    }

    public boolean checkDiagonal(char[][] board, int row, int col, char token, boolean moveUp, boolean moveRight)
    {
        boolean isWin = true;
        for(int i = 0; i < 4; i++)
        {
            if(moveRight)
            {
                if(board[col+i][row+i] != token)
                {
                    isWin = false;
                    break;
                }
            }
            else
            {
                if(board[col-i][row+i] != token)
                {
                    isWin = false;
                    break;
                }
            }
        }
        return isWin;
    }

    public String getWinner(){
        return winner;
    }
}
