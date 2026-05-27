package GamesList;

import java.util.Scanner;

import GamesList.BlackJackMaterials.BlackJackGameController;

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

        while(!playString.equals("no"))
        {
            BlackJackGameController gameInfo = new BlackJackGameController();
            while(!gameInfo.isGameOver)
            {
                System.out.println("The opponent's visible total is " + gameInfo.dealer.GetVisibleCardValue() + ".");
                System.out.println("Your total is " + gameInfo.player.GetCardValue() + ".");
                System.out.println("Would you like to draw another card, or would you like to fold?");
                String continueString = menuPrompt.nextLine().toLowerCase();
                if(continueString == "draw" || continueString == "yes")
                {
                    gameInfo.PlayerDraw();
                }
                else if(continueString == "fold" || continueString == "no")
                {
                    gameInfo.PlayerQuits();
                    while(gameInfo.dealer.GetCardValue() < 17)
                    {
                        gameInfo.DealerDraw();
                    }
                    gameInfo.DealerQuits();
                }

                if(gameInfo.dealer.GetCardValue() < 17 && !gameInfo.player.hasBusted())
                {
                    gameInfo.DealerDraw();
                }
                else
                {
                    gameInfo.DealerQuits();
                }
                
            }
            System.out.println("Would you like to play again (yes or no)?");
            playString = menuPrompt.nextLine().toLowerCase();
        }

        System.out.println("Your final record is " + wins + " wins and " + losses + " losses.");
    }

}
