package GamesList.FarkleMaterials;

import GamesList.ToolsForMultipleGames.GameDice;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    private List<String> currentValidOptions;

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
        this.dieValues = new int[6];
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

    public void ParseInput(String playString, Scanner menuString)
    {
        switch(playString){
            case "roll" -> {
                int possibleItems = Roll(remainingDice);
                int[][] determineScores = farkleScores.ParseScores(currentValidOptions);
                if(possibleItems == 0)
                {
                    currentPlayer++;
                    currentScore = 0;
                    remainingDice = 6;
                    if(currentPlayer > players)
                        currentPlayer = 1;
                }
                else
                {
                    String play = "";
                    boolean isValid = false;
                    while(!isValid)
                    {
                        play = menuString.nextLine().toLowerCase();
                        try
                        {
                            int inputVal = Integer.parseInt(play);
                            if(inputVal < 1 || inputVal > possibleItems)
                            {
                                throw new ArithmeticException("Number outside of range.");
                            }
                            currentScore += determineScores[inputVal-1][0];
                            remainingDice -= determineScores[inputVal-1][1];
                            if(remainingDice == 0)
                            {
                                remainingDice = 6;
                            }
                            isValid = true;
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Please enter a number corresponding to your choice.");
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a number between 1 and " + possibleItems + ".");
                        }
                    }
                }
            }
            case "dice" -> {
                System.out.println("You currently have " + remainingDice + " dice remaining.");
            }
            case "bank" -> {
                if(playerScores[currentPlayer-1] > 0 || currentScore >= minScore)
                {
                    playerScores[currentPlayer-1] += currentScore;
                    System.out.println("Player " + currentPlayer + " banked " + currentScore + " points. Their new score is " + playerScores[currentPlayer-1] + ".");
                    currentScore = 0;
                    currentPlayer++;
                    remainingDice = 6;
                    if(currentPlayer > players)
                        currentPlayer = 1;
                }
                else
                {
                    System.out.println("You don't have enough points yet to bank. Do something else.");
                }
            }
            case "quit" -> {

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

    public int GetCurrentPlayerScore()
    {
        return playerScores[currentPlayer-1];
    }

    private int Roll(int remainingRolls) {
        int possiblePts = 0;
        for(int i = 0; i < remainingRolls; i++)
        {
            dieValues[i] = gameDice[i].RollDice();
        }

        int[] diceToCheck = new int[remainingRolls];
        for(int i = 0; i < diceToCheck.length; i++)
        {
            diceToCheck[i] = dieValues[i];
        }
        System.out.println(DisplayDice(diceToCheck));
        if(farkleScores.CheckZonk(diceToCheck))
        {
            System.out.println("Oh dear, seems like you've rolled a fumble. That means that your turn ends and you score nothing.");
        }
        else
        {
            int[] organizedDice = farkleScores.GetDiceNums(diceToCheck);
            currentValidOptions = farkleScores.GetValidScores(organizedDice);
            possiblePts = currentValidOptions.size();
            for(int i = 0; i < currentValidOptions.size(); i++)
            {
                System.out.print((i+1) + ". " + currentValidOptions.get(i) + " ");
            }
            System.out.println("");
            
        }
        return possiblePts;
    }

    private String DisplayDice(int[] dice) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < dice.length; i++)
        {
            sb.append(dice[i]);
            if(i + 1 < dice.length)
                sb.append(" ");
        }
        return sb.toString();
    }
    
}
