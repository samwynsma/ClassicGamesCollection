package GamesList.YachtGameMaterials;

import GamesList.ToolsForMultipleGames.GameDice;

public class YachtGameController {

    private GameDice[] dice;
    private int score;
    private int[] dieValues;
    private YachtDiceRolls yachtScores;

    public YachtGameController()
    {
        dice = new GameDice[6];
        dieValues = new int[6];
        yachtScores = new YachtDiceRolls(1);
        score = 0;
        for(int i = 0; i < dice.length; i++)
        {
            dice[i] = new GameDice(6);
        }
    }

    public boolean CheckGameOver() {
        return false;
    }

    public int GetScore() {
        return score;
    }

    public boolean AssignCategory(String category)
    {
        int rollScore = YachtDiceRolls.ScoreRoll(category);
        if(rollScore >= 0)
        {
            score += rollScore;
            return true;
        }
        return false;
    }

}
