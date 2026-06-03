package GamesList.RiskyDiceMaterials;

public class RiskyDiceGameController {

    private int[] playerScores;
    private int players;
    private int currentPlayer;

    public RiskyDiceGameController(int players) {
        this.players = players;
        this.playerScores = new int[players];
        this.currentPlayer = 1;
    }

    public boolean IsGameOver()
    {
        return currentPlayer > players;
    }

    public int GetCurrentPlayer() {
        return currentPlayer;
    }

    public int GetCurrentScore() {
        return playerScores[currentPlayer-1];
    }

    public String DetermineWinner() {
        String winnerDetails = "Nobody wins.";
        int maxScore = 0;
        for(int i = 0; i < playerScores.length; i++)
        {
            if(maxScore < playerScores[i])
            {
                maxScore = playerScores[i];
                winnerDetails = "Player " + (i+1);
            }
            else if(maxScore == playerScores[i])
            {
                winnerDetails += " and " + (i+1);
            }
        }
        return winnerDetails;
    }

    public void ParseInput(String playString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ParseInput'");
    }
    
}
