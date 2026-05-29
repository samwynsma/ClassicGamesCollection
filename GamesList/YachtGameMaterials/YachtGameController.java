package GamesList.YachtGameMaterials;

import GamesList.ToolsForMultipleGames.GameDice;

public class YachtGameController {

    private GameDice[] dice;
    private int score;
    private YachtDiceRolls yachtScores;

    public YachtGameController()
    {
        dice = new GameDice[6];
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

}
