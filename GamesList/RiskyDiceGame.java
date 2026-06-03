package GamesList;

import java.util.Scanner;

import GamesList.RiskyDiceMaterials.RiskyDiceGameController;

public class RiskyDiceGame {

    public void PlayGame(Scanner menuPrompt) {
        System.out.println("Welcome to risky dice. This is a game played by any number of players, but must have at least two players.");
        System.out.println("In this game, you go until you quit or lose. Then, the next player plays and whoever scores the most points wins.");
        System.out.println("Sometimes, you gain the ability to move onto the next level of dice. This means that the rewards get higher, but so does the risk. You'll be given the choice to proceed if you wish.");
        int players = 0;
        String playString = "";
        while(players < 2 || players > 10)
        {
            System.out.println("How many players will be playing risky dice? Please enter a number between 2 and 10.");
            playString = menuPrompt.nextLine().toLowerCase();
            try
            {
                players = Integer.parseInt(playString);
            }
            catch(Exception e)
            {
                System.out.println("Please enter a number between 2 and 10");
            }
        }
        RiskyDiceGameController gameInfo = new RiskyDiceGameController(players);
        while(!playString.equals("quit") && !gameInfo.IsGameOver())
        {
            System.out.println("Do you want to continue, player " + gameInfo.GetCurrentPlayer() + "? (Type roll to continue or pass to stop. Type quit to end the game early.)");
            playString = menuPrompt.nextLine().toLowerCase();
            gameInfo.ParseInput(playString);
        }
        System.out.println(gameInfo.DetermineWinner() + " is/are the winner(s).");
    }
    
}
