package GamesList.YachtGameMaterials;

import GamesList.ToolsForMultipleGames.GameDice;

public class YachtGameController {

    private GameDice[] dice;
    private int score;
    private int[] dieValues;
    private int rollsInARow;
    private YachtDiceRolls yachtScores;

    public boolean[] forbiddenRolls;

    public YachtGameController()
    {
        dice = new GameDice[5];
        dieValues = new int[5];
        forbiddenRolls = new boolean[5];
        rollsInARow = 0;
        yachtScores = new YachtDiceRolls(1);
        score = 0;
        for(int i = 0; i < dice.length; i++)
        {
            dice[i] = new GameDice(6);
        }
    }

    public void ParseInput(String playString) {
        switch(playString)
        {
            case "roll": case "r": case "": case "roll the dice":
                Roll(forbiddenRolls);
                System.out.println("Dice roll result: " + this.DisplayDice());
                break;
            case "ones": case "twos": case "threes": case "fours": case "fives": case "sixes": case "two pair": case "free": case "yacht": case "full house":
                AssignCategory(playString);
                break;
            case "three of a kind": case "four of a kind":
                AssignCategory(playString);
                break;
            case "four straight": case "small straight":
                AssignCategory("four straight");
                break;
            case "five straight": case "large straight": case "full straght":
                AssignCategory("five straight");
                break;
            case "current score":
                System.out.println("Your current score is " + score);
            default:
                System.out.println("Invalid command.");
                break;
        }
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
            forbiddenRolls = new boolean[6];
            score += rollScore;
            rollsInARow = 0;
            if(rollScore > 0)
            {
                System.out.println("Your " + category + " roll scores you " + rollScore + " points");
            }
            else
            {
                System.out.println("Tough luck: 0 points.");
            }
            return true;
        }
        return false;
    }

    public void Roll(boolean[] forbidden)
    {
        if(rollsInARow >= 3)
        {
            System.out.println("You have rolled the dice three times in a row. Please select a score category before continuing.");
            return;
        }
        for(int i = 0; i <= 4; i++)
        {
            if(!forbidden[i])
            {
                dieValues[i] = dice[i].RollDice();
            }
        }
        forbiddenRolls = new boolean[6];
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

    public String DisplayDice(){
        StringBuilder dice = new StringBuilder("");
        for(int i = 0; i < dieValues.length; i++)
        {
            dice.append(dieValues[i]);
            if(i + 1 < dieValues.length)
                dice.append(" ");
        }
        return dice.toString();
    }
    

}
