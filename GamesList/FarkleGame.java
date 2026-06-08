package GamesList;
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
    }

}
