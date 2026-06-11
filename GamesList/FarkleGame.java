package GamesList;
import GamesList.FarkleMaterials.FarkleGameController;
import java.util.Scanner;

public class FarkleGame {

    public void PlayGame(Scanner menuPrompt) {
        System.out.println("Welcome to the classic dice game, Farkle. This is a game of pushing your luck.");
        System.out.println("Farkle is a game where you keep scoring points based on the results of your roll. The goal is to make it to 10000 and win.");
        System.out.println("First things first, we need to set up the game by determining the rules.");
        int players = 0;
        int minScore = 0;
        String playString = "";
        while(players < 2 || players > 10)
        {
            System.out.println("How many players do you want to have in this game? Please select a number between 2 and 10.");
            playString = menuPrompt.nextLine().toLowerCase();
            try
            {
                players = Integer.parseInt(playString);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please type in a number between 2 and 10");
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage() + " something went wrong.");
            }
        }
        while(minScore < 300 || minScore > 2500 || minScore % 100 != 0)
        {
            System.out.println("How many points is the minimum to get on the board as your first roll? Please select a number between 300 and 2500 that is divisible by 100. Only set it above 1000 if you are brave.");
            playString = menuPrompt.nextLine().toLowerCase();
            try
            {
                minScore = Integer.parseInt(playString);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please type in a number between 300 and 2500, and make it divisible by 100");
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage() + " something went wrong.");
            }
        }
        FarkleGameController gameInfo = new FarkleGameController(players, minScore);
        while(!playString.equals("quit") && !gameInfo.IsGameOver())
        {
            System.out.println("Alright, " + gameInfo.GetCurrentPlayer() + ", you current score is " + gameInfo.currentScore + ". Do you want to roll or bank your points?");
            playString = menuPrompt.nextLine().toLowerCase();
            gameInfo.ParseInput(playString, menuPrompt);
        }
        System.out.println("Game end has not been implemented yet.");
        gameInfo.DisplayLeaderboard();
    }

}
