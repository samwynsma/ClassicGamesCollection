package GamesList.RiskyDiceMaterials;

public class RiskyDiceGameController {

    private final int[] playerScores;
    private final int players;
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
                int adjustmentValue = diceRolls.RollDice(currentDie);
                if(diceRolls.HasLost())
                {
                    playerScores[currentPlayer-1] = 0;
                    currentPlayer++;
                    currentDie = 1;
                    currentRolls = 0;
                }
                else if(!diceRolls.multiplier && !diceRolls.divider)
                {
                    playerScores[currentPlayer-1] += adjustmentValue;
                }
                else if(diceRolls.multiplier)
                {
                    diceRolls.multiplier = false;
                    playerScores[currentPlayer-1] *= adjustmentValue;
                }
                else if(diceRolls.divider)
                {
                    diceRolls.divider = false;
                    playerScores[currentPlayer-1] /= adjustmentValue;
                }
                else if(diceRolls.setScore)
                {
                    diceRolls.setScore = false;
                    playerScores[currentPlayer-1] = adjustmentValue;
                }

                if(diceRolls.everyOneElseGetsPoints)
                {
                    for(int i = 0; i < playerScores.length; i++)
                    {
                        if(i != currentPlayer-1)
                        {
                            playerScores[i] += adjustmentValue;
                        }
                        else
                        {
                            playerScores[i] -= adjustmentValue;
                        }
                    }
                    diceRolls.everyOneElseGetsPoints = false;
                }

                if(diceRolls.goToSecretDie)
                {
                    currentDie = 6;
                    diceRolls.goToSecretDie = false;
                }
                break;
            case "advance":
                if(currentRolls >= 10 || diceRolls.CanAdvance())
                {
                    if(currentDie == 5)
                    {
                        System.out.println("Cannot advance: on final die already.");
                    }
                    else
                    {
                        currentDie++;
                        currentRolls = (0 - (5 * (currentDie-1)));
                        diceRolls.SetAdvance(false);
                    }
                }
            case "quit":
                break;
        }
    }
    
}
