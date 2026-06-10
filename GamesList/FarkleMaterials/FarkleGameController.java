package GamesList.FarkleMaterials;

import java.util.Arrays;

public class FarkleGameController {

    private int[] playerScores;
    private int players;
    public int currentPlayer;
    public int currentScore;
    private int minScore;
    private boolean[] haveStarted;
    private boolean[] haveFinished;

    public FarkleGameController(int players, int minScore) {
        this.players = players;
        this.minScore = minScore;
        this.currentPlayer = 1;
        this.currentScore = 0;
        this.playerScores = new int[players];
        this.haveStarted = new boolean[players];
        this.haveFinished = new boolean[players];
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
    
}
