package GamesList.RiskyDiceMaterials;

import java.util.Arrays;

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
            case "score" -> System.out.println("Player " + GetCurrentPlayer() + ", your current score is " + GetCurrentScore());
            case "stop" -> {
                currentPlayer++;
                currentDie = 1;
                currentRolls = 0;
            }
            case "roll" -> {
                currentRolls++;
                int adjustmentValue = diceRolls.RollDice(currentDie);
                if(diceRolls.HasLost())
                {
                    playerScores[currentPlayer-1] = 0;
                    currentPlayer++;
                    currentDie = 1;
                    currentRolls = 0;
                    diceRolls.isLoss = false;
                    return;
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
                
                if(diceRolls.setScore)
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

                if(playerScores[currentPlayer - 1] < 0)
                {
                    playerScores[currentPlayer - 1] = 0;
                    System.out.println("Score cannot go below zero");
                }
            }
            case "advance" -> {
                if(currentRolls >= 10 || diceRolls.CanAdvance())
                {
                    if(currentDie >= 5)
                    {
                        System.out.println("Cannot advance: on final die already.");
                    }
                    else
                    {
                        currentDie++;
                        currentRolls = (0 - (5 * (currentDie-1)));
                        System.out.println("Now you are on die #" + currentDie);
                        diceRolls.SetAdvance(false);
                    }
                }
                else
                {
                    System.out.println("Cannot advance. Either roll an advance, or roll enough times. You need to roll " + (10 - currentRolls) + " more times to get a free advance.");
                }
            }
            case "help" -> System.out.println("Commands: \n \"Roll\" to roll the die. \n \"Stop\" to end your turn and keep your current score. \n \"Score\" to see your current score. \n \"Advance\" to advance to the next die if you have rolled enough times on the current die or rolled the right rolls to advance. \n \"Quit\" to quit the game.");
            case "cheat give me defense" -> {
                diceRolls.isDefended = true;
                System.out.println("I hope that you're doing this to debug the game.");
            }
            case "quit" -> {
            }
        }
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
