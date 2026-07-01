package GamesList;

import GamesList.GomokuMaterials.GomokuBoard;
import GamesList.GomokuMaterials.GomokuGameController;
import java.util.Scanner;

public class GomokuGame {

    public void PlayGame(Scanner menuPrompt) {
        System.out.println("Welcome to the game of Gomoku. This game takes place on a 15x15 board");
        System.out.println("The goal in this game is to get a certain number of tiles in a row before your opponent");
        System.out.println("The default number is five, but to allow for interesting and different games, I have set rules for any number between three and seven");

        int inARow = 0;
        String playString = "";
        boolean isGameOver = false;
        boolean isPlayerOne = true;
        GomokuBoard board = new GomokuBoard();
        GomokuGameController gameInfo = new GomokuGameController();
        while(inARow < 3 || inARow > 7)
        {
            System.out.println("How many tiles in a row do you want for victory? Five is the traditional number");
            playString = menuPrompt.nextLine().toLowerCase();
            try
            {
                inARow = Integer.parseInt(playString);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please type in a number between 3 and 7");
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage() + " something went wrong.");
            }
        }
        gameInfo.SetWinCondition(inARow);

        while(!isGameOver)
        {
            if(isPlayerOne)
            {
                System.out.println("Please select a place to place your tile, player 1");
            }
            else
            {
                System.out.println("Please select a place to place your tile, player 2");
            }
            board.DisplayBoard();
            playString = menuPrompt.nextLine().toLowerCase();
            if(playString.equals("quit"))
            {
                System.out.println("Game has been aborted.");
                break;
            }
            String play1 = playString;
            playString = menuPrompt.nextLine().toLowerCase();
            if(playString.equals("quit"))
            {
                System.out.println("Game has been aborted.");
                break;
            }
            String play2 = playString;
            isPlayerOne = board.PlaceTile(play1, play2, isPlayerOne);
            isGameOver = gameInfo.CheckVictory(board);
        }

        System.out.println("The game is over.");
        System.out.println(gameInfo.getWinner() + " is the winner.");
        board.DisplayBoard();
        
    }
    
}
