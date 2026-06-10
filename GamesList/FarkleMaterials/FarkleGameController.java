package GamesList.FarkleMaterials;

import GamesList.ToolsForMultipleGames.GameDice;
import java.util.Arrays;

public class FarkleGameController {

    private int[] playerScores;
    private int players;
    private int currentPlayer;
    public int currentScore;
    private int minScore;
    private boolean[] haveStarted;
    private boolean[] haveFinished;
    private GameDice[] gameDice;
    private int[] dieValues;
    private int remainingDice;
    private FarkleDiceRolls farkleScores;

    public FarkleGameController(int players, int minScore) {
        this.players = players;
        this.minScore = minScore;
        this.currentPlayer = 1;
        this.currentScore = 0;
        this.remainingDice = 6;
        this.playerScores = new int[players];
        this.haveStarted = new boolean[players];
        this.haveFinished = new boolean[players];
        this.gameDice = new GameDice[6];
        this.farkleScores = new FarkleDiceRolls();
        for(int i = 0; i < this.gameDice.length; i++)
        {
            gameDice[i] = new GameDice(6);
        }
        
    }

    public boolean IsGameOver() {
        for(int i = 0; i < haveFinished.length; i++)
        {
            if(!haveFinished[i])
                return false;
        }
        return true;
    }

    public void DisplayLeaderboard() {
        int[][] scoreboard = new int[playerScores.length][2];
        for(int i = 0; i < playerScores.length; i++)
        {
            scoreboard[i][0] = playerScores[i];
            scoreboard[i][1] = i;
        }

        Arrays.sort(scoreboard, (a, b) -> Integer.compare(a[0], b[0]));
        for(int i = 1; i <= scoreboard.length; i++)
        {
            System.out.println("#" + i + ": Player " + (scoreboard[scoreboard.length-i][1] + 1) + " with " + scoreboard[scoreboard.length-i][0] + " points.");
        }
    }

    public void ParseInput(String playString)
    {
        switch(playString){
            case "roll" -> {
                Roll(remainingDice);
                System.out.println("Roll Result: " + this.DisplayDice());
            }
            default -> {
                System.out.println("Not a valid input");
            }
        }
    }

    public int ChooseScore(String playString)
    {
        return 0;
    }

    public int GetCurrentPlayer()
    {
        return currentPlayer;
    }

    private void Roll(int remainingRolls) {
        for(int i = 0; i < remainingRolls; i++)
        {
            dieValues[i] = gameDice[i].RollDice();
        }

        int[] diceToCheck = new int[remainingRolls];
        for(int i = 0; i < diceToCheck.length; i++)
        {
            diceToCheck[i] = dieValues[i];
        }
        if(farkleScores.CheckZonk(diceToCheck))
        {
            System.out.println("Oh dear, seems like you've rolled a fumble. That means that your turn ends and you score nothing.");
        }
        else
        {
            String[] possibleScores = farkleScores.GetValidScores();
        }
    }

    private String DisplayDice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
