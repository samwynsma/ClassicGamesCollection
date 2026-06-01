package GamesList;

import java.util.Scanner;

import GamesList.YachtGameMaterials.YachtGameController;

public class YachtGame {

    public void PlayGame(Scanner menuPrompt) {
        System.out.println("Welcome to Yacht. This game is played with dice.");
        System.out.println("The goal is to score as many points as possible. You can re-roll your dice up to two times, or take your roll.");
        System.out.println("After you take your roll, you can select a category. The categories are 1s, 2s, 3s, 4s, 5s, 6s, two pair, three of a kind, four straight, five straight, full house, four of a kind, free move, and yacht (five of a kind)");
        System.out.println("At basic rules, you can only select each category once. At advanced rules, you can do so three times.");
        String playString = "";
        YachtGameController gameInfo = new YachtGameController();

        while(!playString.equals("quit") && !gameInfo.CheckGameOver())
        {
            System.out.println("Roll the dice, or quit to end the game");
            playString = menuPrompt.nextLine().toLowerCase();
            if(!playString.equals("quit") && !playString.equals("lock"))
            {
                gameInfo.ParseInput(playString);
            }
            else if(playString.equals("lock"))
            {
                System.out.println("Which dice do you wish to lock? Enter the position numbers from 1 to 6");
                String lockString = menuPrompt.nextLine().toLowerCase();
                try{
                    int forbiddenNums = Integer.parseInt(lockString);
                    if(forbiddenNums >= 100000)
                    {
                        throw new Exception("Too high a number");
                    }
                    while(forbiddenNums > 0)
                    {
                        int remainder = forbiddenNums % 10;
                        gameInfo.forbiddenRolls[remainder] = true;
                        forbiddenNums /= 10;
                    }
                }
                catch(Exception exception)
                {
                    System.out.println("Invalid input. Type in up to 5 different numbers from 1 to 6.");
                }
                
                
            }
        }

        System.out.println("Your final score was " + gameInfo.GetScore());
    }

}
