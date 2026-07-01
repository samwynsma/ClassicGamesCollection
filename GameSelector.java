import java.util.Scanner;

import GamesList.BlackJackGame;
import GamesList.ConnectFourGame;
import GamesList.FarkleGame;
import GamesList.GomokuGame;
import GamesList.MasterMindGame;
import GamesList.PokerGame;
import GamesList.RiskyDiceGame;
import GamesList.YachtGame;

public class GameSelector {

    public GameSelector()
    {

    }

    public void ChooseGame(String menuString, Scanner menuPrompt) {
        switch(menuString)
        {
            case "1" -> {
                ConnectFourGame connectFour = new ConnectFourGame();
                connectFour.PlayGame(menuPrompt);
            }
            case "2" -> {
                MasterMindGame mastermind = new MasterMindGame();
                mastermind.PlayGame(menuPrompt);
            }
            case "3" -> {
                BlackJackGame blackjack = new BlackJackGame();
                blackjack.PlayGame(menuPrompt);
            }
            case "4" -> {
                YachtGame yacht = new YachtGame();
                yacht.PlayGame(menuPrompt);
            }
            case "5" -> {
                RiskyDiceGame riskyDice = new RiskyDiceGame();
                riskyDice.PlayGame(menuPrompt);
            }
            case "6" -> {
                FarkleGame farkle = new FarkleGame();
                farkle.PlayGame(menuPrompt);
            }
            case "7" -> {
                GomokuGame gomoku = new GomokuGame();
                gomoku.PlayGame(menuPrompt);
            }
            case "8" -> {
                PokerGame poker = new PokerGame();
                poker.PlayGame(menuPrompt);
            }
            case "quit" -> {
            }
            default -> System.out.println("Invalid game selection. Please select a number on the list. Further games might exist later.");
        }
    }
}
