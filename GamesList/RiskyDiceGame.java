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
        RiskyDiceGameController gameInfo = new RiskyDiceGameController(players);
    }
    
}
