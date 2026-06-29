package GamesList.GomokuMaterials;

public class GomokuGameController {

    private String winner;
    public GomokuGameController()
    {
        winner = "";
    }

    public boolean CheckVictory(GomokuBoard board)
    {
        char[][] boardInfo = board.GetBoard();
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


        return false;
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
