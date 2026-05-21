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
        else if(victoryToken == 'O')
        {
            winner = "Player 2";
        }
        else
        {
            return false;
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
                    isWin = checkVertical(boardInfo, rowVal, colVal, token);
                    if(isWin)
                        break;
                }
                if(colVal <= 3)
                {
                    isWin = checkHorizontal(boardInfo, rowVal, colVal, token, true);
                    if(isWin)
                        break;
                }
                if(colVal >= 3)
                {
                    isWin = checkHorizontal(boardInfo, rowVal, colVal, token, false);
                    if(isWin)
                        break;
                }
                if(rowVal < 3 && colVal <= 3)
                {
                    isWin = checkDiagonal(boardInfo, rowVal, colVal, token, true);
                    if(isWin)
                        break;
                }
                if(rowVal < 3 && colVal >= 3)
                {
                    isWin = checkDiagonal(boardInfo, rowVal, colVal, token, false);
                    if(isWin)
                        break;
                }
            }
            if(isWin)
                break;
        }
        isDraw = true;
        int[] dropCols = board.GetDropLocations();
        for(int i = 0; i < dropCols.length; i++)
        {
            if(dropCols[i] >= 0)
            {
                isDraw = false;
                break;
            }
        }
        if(isDraw && !isWin)
        {
            winner = "Nobody";
        }
        return isWin || isDraw;
    }

    public boolean checkVertical(char[][] board, int row, int col, char token)
    {
        boolean isWin = true;
        for(int i = 0; i < 4; i++)
        {
            if(board[col][row+i] != token)
            {
                isWin = false;
                break;
            }
        }
        return isWin;
    }

    public boolean checkHorizontal(char[][] board, int row, int col, char token, boolean isRight)
    {
        boolean isWin = true;
        for(int i = 0; i < 4; i++)
        {
            if(isRight)
            {
                if(board[col+i][row] != token)
                {
                    isWin = false;
                    break;
                }
            }
            else
            {
                if(board[col-i][row] != token)
                {
                    isWin = false;
                    break;
                }
            }
        }
        return isWin;

    }

    public boolean checkDiagonal(char[][] board, int row, int col, char token, boolean isRight)
    {
        boolean isWin = true;
        for(int i = 0; i < 4; i++)
        {
            if(isRight)
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
