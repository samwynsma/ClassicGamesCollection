package GamesList;

import java.util.Scanner;

import GamesList.MasterMindMaterials.MasterMindController;

public class MasterMindGame {

    public void PlayGame(Scanner menuPrompt) {
        MasterMindController gameInfo = new MasterMindController();
        System.out.println("Welcome to Mastermind. This is a single player game. \n In this game, you type in a four digit code with numbers from 0 to 4 (easy), 5 (medium), 6 (hard), 7 (brutal), and 9 (impossible).");
        System.out.println("If you are correct, you will win the game. \n If you are incorrect, you will be told how many you have that are correct, as well as how many are in the wrong place.");
        System.out.println("You have to figure out for yourself where the wrong ones are.");
        System.out.println("First, let's hear what difficulty you want to play on.");
        boolean isValidDifficulty = false;
        while(!isValidDifficulty)
        {
            String difficulty = menuPrompt.nextLine().toLowerCase();
            isValidDifficulty = gameInfo.SetDifficulty(difficulty);
        }
        gameInfo.SetUpGame();
        boolean isSolved = false;
        while(!isSolved)
        {
            System.out.println("Please enter a four digit code.");
            String code = menuPrompt.nextLine().toLowerCase();
            if(code.equals("quit"))
            {
                System.out.println("Game has been quit. The correct code was " + gameInfo.GetCode() + ".");
                break;
            }
            if(!gameInfo.CheckValidity(code))
            {
                continue;
            }
            isSolved = gameInfo.CheckIfSolved(code);
            if(!isSolved)
            {
                System.out.println("TODO");
            }
        }
    }

}
