package GamesList;

import java.util.Scanner;

public class BlackJackGame {

    private int wins;
    private int losses;
    public BlackJackGame()
    {
        wins = 0;
        losses = 0;
    }

    public void PlayGame(Scanner menuPrompt) {
        System.out.println("Welcome to Blackjack. This game uses a deck of 52 cards. The goal is to score higher than the dealer.");
        System.out.println("But watch out! If you go bust, the dealer wins automatically. Player always beats dealer on ties.");
        System.out.println("The dealer will always draw until they reach 17 or higher. The player can choose to stop drawing at any time.");
        System.out.println("Players alternate draws. This game is initially one player, but I will add a two player feature later.");
        System.out.println("Game will keep track of wins and losses until the player decided to quit.");
        String playString = "";
        while(playString != "no")
        {
            System.out.println("Would you like to play again?");
            playString = menuPrompt.nextLine().toLowerCase();
        }

        System.out.println("Your final record is " + wins + " wins and " + losses + " losses.");
    }

}
