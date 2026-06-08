import GamesList.BlackJackGame;
import GamesList.ConnectFourGame;
import GamesList.FarkleGame;
import GamesList.MasterMindGame;
import GamesList.RiskyDiceGame;
import GamesList.YachtGame;
import java.util.Scanner;

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
            case "quit" -> {
            }
            default -> System.out.println("Invalid game selection. Please select a number on the list. Further games might exist later.");
        }
    }
}
