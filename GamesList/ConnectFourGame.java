package GamesList;
import java.util.Scanner;

import GamesList.ConnectFourMaterials.ConnectFourBoard;

public class ConnectFourGame {

    public void PlayGame() {
        System.out.println("Welcome to Connect 4. In this game, the goal is to get four elements in a row.");
        boolean isGameOver = false;
        boolean isPlayerOne = true;
        Scanner columnScanner = new Scanner(System.in);
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
            String columnString = columnScanner.nextLine().toLowerCase();
            if(columnString == "quit")
            {
                break;
            }
            board.DropIntoColumn(columnString, isPlayerOne);
        }

        columnScanner.close();
    }

}
