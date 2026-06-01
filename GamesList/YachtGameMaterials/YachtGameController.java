package GamesList.YachtGameMaterials;

import GamesList.ToolsForMultipleGames.GameDice;

public class YachtGameController {

    private GameDice[] dice;
    private int score;
    private int[] dieValues;
    private int rollsInARow;
    private YachtDiceRolls yachtScores;

    public YachtGameController()
    {
        dice = new GameDice[6];
        dieValues = new int[6];
        rollsInARow = 0;
        yachtScores = new YachtDiceRolls(1);
        score = 0;
        for(int i = 0; i < dice.length; i++)
        {
            dice[i] = new GameDice(6);
        }
    }

    public void ParseInput(String playString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ParseInput'");
    }

    public boolean CheckGameOver() {
        boolean isGameOver = true;
        for(int i = 0; i < yachtScores.rollScores.length; i++)
        {
            for(int j = 0; j < yachtScores.rollScores[i].length; j++)
            {
                if(yachtScores.rollScores[i][j] == -1)
                {
                    isGameOver = false;
                    break;
                }
            }
        }
        return isGameOver;
    }

    public int GetScore() {
        return score;
    }

    public boolean AssignCategory(String category)
    {
        int rollScore = yachtScores.ScoreRoll(category, dieValues, TotalAllDice());
        if(rollScore >= 0)
        {
            score += rollScore;
            rollsInARow = 0;
            return true;
        }
        return false;
    }

    public void Roll(String forbidden)
    {
        if(rollsInARow >= 3)
        {
            System.out.println("You have rolled the dice three times in a row. Please select a score category before continuing.");
            return;
        }
        for(int i = 0; i <= 5; i++)
        {
            if(!forbidden.contains(""+i))
            {
                dice[i].RollDice();
            }
        }
        rollsInARow++;
        if(rollsInARow == 3)
        {
            System.out.println("Please select a category to score (ones, twos, threes, fours, fives, sixes, two pair, three of a kind, four of a kind, four straight, five straight, full house, free, or yacht)");
        }
        else
        {
            System.out.println("Either roll again, or select a category.");
        }
    }

    private int TotalAllDice() {
        int total = 0;
        for(int i = 0; i < dieValues.length; i++)
        {
            total += dieValues[i];
        }
        return total;
    }

    

}
