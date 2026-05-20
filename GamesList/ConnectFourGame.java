package GamesList;
import java.util.Scanner;

import GamesList.ConnectFourMaterials.ConnectFourBoard;

public class ConnectFourGame {

    public void PlayGame(Scanner columnPrompt) {
        System.out.println("Welcome to Connect 4. In this game, the goal is to get four elements in a row.");
        boolean isGameOver = false;
        boolean isPlayerOne = true;
        ConnectFourBoard board = new ConnectFourBoard();
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
            String columnString = columnPrompt.nextLine().toLowerCase();
            if(columnString.equals("quit"))
            {
                System.out.println("Game has been aborted.");
                break;
            }
            board.DropIntoColumn(columnString, isPlayerOne);
        }
    }

}
