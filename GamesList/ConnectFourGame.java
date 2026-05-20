package GamesList;
import java.util.Scanner;

import GamesList.ConnectFourMaterials.ConnectFourBoard;
import GamesList.ConnectFourMaterials.ConnectFourGameController;

public class ConnectFourGame {

    public void PlayGame(Scanner columnPrompt) {
        System.out.println("Welcome to Connect 4. In this game, the goal is to get four elements in a row.");
        boolean isGameOver = false;
        boolean isPlayerOne = true;
        ConnectFourBoard board = new ConnectFourBoard();
        ConnectFourGameController gameInfo = new ConnectFourGameController();
        while(!isGameOver)
        {
            if(isPlayerOne)
            {
                System.out.println("Please select a column to drop your tile into, player 1.");
            }
            else
            {
                System.out.println("Please select a column to drop your tile into, player 2.");
            }
            board.DisplayBoard();
            String columnString = columnPrompt.nextLine().toLowerCase();
            if(columnString.equals("quit"))
            {
                System.out.println("Game has been aborted.");
                break;
            }
            isPlayerOne = board.DropIntoColumn(columnString, isPlayerOne);
            isGameOver = gameInfo.CheckVictory(board, isPlayerOne);
        }

        System.out.println("The game is over.");
        board.DisplayBoard();
    }

}
