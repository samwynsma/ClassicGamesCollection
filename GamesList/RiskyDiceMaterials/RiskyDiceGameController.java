package GamesList.RiskyDiceMaterials;

public class RiskyDiceGameController {

    private int[] playerScores;
    private int players;
    private int currentPlayer;
    private int currentDie;
    private int currentRolls;
    
    public RiskyDiceRolls diceRolls;

    public RiskyDiceGameController(int players) {
        this.players = players;
        this.playerScores = new int[players];
        this.currentPlayer = 1;
        this.currentDie = 1;
        this.currentRolls = 0;
        this.diceRolls = new RiskyDiceRolls();
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
        switch(playString)
        {
            case "score":
                System.out.println("Your current score is " + GetCurrentScore());
                break;
            case "stop":
                currentPlayer++;
                currentDie = 1;
                currentRolls = 0;
                break;
            case "roll":
                playerScores[currentPlayer-1] += diceRolls.RollDice(currentDie);
                if(diceRolls.HasLost())
                {
                    playerScores[currentPlayer-1] = 0;
                    currentPlayer++;
                    currentDie = 1;
                    currentRolls = 0;
                }
            case "quit":
                break;
        }
    }
    
}
