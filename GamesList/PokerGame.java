package GamesList;

import GamesList.PokerMaterials.PokerGameController;
import java.util.Scanner;

public class PokerGame {

    public void PlayGame(Scanner menuPrompt) {
        System.out.println("Welcome to the game of Poker.");
        System.out.println("This version of Poker is played with up to six players.");
        System.out.println("A player can get rid of up to five cards from their hand, then they will compare hands with everyone else.");
        System.out.println("Game will go until you choose to quit or say no when asked to play again.");
        String playString = "";
        int players = 0;

        while(players < 2 || players > 5)
        {
            System.out.println("How many players do you want to have in this game? Please select a number between 2 and 5.");
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
        
        while(!playString.equals("no"))
        {
            PokerGameController gameInfo = new PokerGameController(players);
            playString = "no";
        }
    }
    
}
