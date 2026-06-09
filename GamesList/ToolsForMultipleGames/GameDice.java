package GamesList.ToolsForMultipleGames;

import java.util.Random;

public class GameDice {

    private final int maxRoll;
    private final int minRoll;

    public GameDice(int i) {
        minRoll = 1;
        maxRoll = i;
    }

    public int RollDice()
    {
        Random rand = new Random();
        int roll = rand.nextInt(maxRoll - minRoll + 1) + minRoll;
        return roll;
    }
    
}
