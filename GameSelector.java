import java.util.Scanner;

import GamesList.BlackJackGame;
import GamesList.ConnectFourGame;
import GamesList.MasterMindGame;

public class GameSelector {

    public GameSelector()
    {

    }

    public void ChooseGame(String menuString, Scanner menuPrompt) {
        switch(menuString)
        {
            case "1":
                ConnectFourGame connectFour = new ConnectFourGame();
                connectFour.PlayGame(menuPrompt);
                break;
            case "2":
                MasterMindGame mastermind = new MasterMindGame();
                mastermind.PlayGame(menuPrompt);
            case "3":
                BlackJackGame blackjack = new BlackJackGame();
                blackjack.PlayGame(menuPrompt);
            default:
                System.out.println("Invalid game selection. Please select a number on the list. Further games might exist later.");
                break;
        }
    }
}
