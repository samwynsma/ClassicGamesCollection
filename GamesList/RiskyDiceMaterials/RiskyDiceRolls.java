package GamesList.RiskyDiceMaterials;

import GamesList.ToolsForMultipleGames.GameDice;
import java.util.Random;

public class RiskyDiceRolls {

    public boolean isLoss;
    public boolean canAdvance;
    public boolean multiplier;
    public boolean divider;
    private final GameDice[] diceSet;

    public RiskyDiceRolls(){
        diceSet = new GameDice[5];
        diceSet[0] = new GameDice(6);
        diceSet[1] = new GameDice(8);
        diceSet[2] = new GameDice(10);
        diceSet[3] = new GameDice(15);
        diceSet[4] = new GameDice(20);
        isLoss = false;
        canAdvance = false;
        multiplier = false;
        divider = false;
    }

    public int RollDice(int currentDie) {
        switch(currentDie)
        {
            case 1 -> {
                return DieOne();
            }
            case 2 -> {
                return DieTwo();
            }
            case 3 -> {
                return DieThree();
            }
            case 4 -> {
                return DieFour();
            }
            default -> {
                return DieFive();
            }
        }
    }

    private int DieOne() {
        int rollValue = diceSet[0].RollDice();
        int score = 0;
        Random rand = new Random();
        switch(rollValue)
        {
            case 1 -> {
                isLoss = true;
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose.");
            }
            case 2 -> {
                score = - (rand.nextInt(5) + 1);
                System.out.println("You rolled a tiny loss. That means that you lost " + score + " points.");
            }
            case 3 -> {
                score = rand.nextInt(5) + 1;
                System.out.println("You rolled a tiny gain. That means that you gained " + score + " points.");
            }
            case 4 -> {
                score = rand.nextInt(10) + 5;
                System.out.println("You rolled a small gain. That means that you gained " + score + " points.");
            }
            case 5 -> {
                score++;
                System.out.println("You rolled a single point gain. You gained 1 point.");
            }
            case 6 -> {
                canAdvance = true;
                System.out.println("You rolled an advance. That means that you can advance to the next die whenever you want.");
            }
        }
        return score;
    }

    private int DieTwo(){
        int rollValue = diceSet[1].RollDice();
        int score = 0;
        Random rand = new Random();
        switch(rollValue)
        {
            case 1 -> {
                isLoss = true;
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose.");
            }
            case 2 -> {
                score++;
                System.out.println("You rolled a single point gain. You gained 1 point.");
            }
            case 3 -> {
                score = - (rand.nextInt(5) + 1);
                System.out.println("You rolled a tiny loss. That means that you lost " + score + " points.");
            }
            case 4 -> {
                score = - (rand.nextInt(10) + 5);
                System.out.println("You rolled a small loss. That means that you lost " + score + " points.");
            }
            case 5 -> {
                score = rand.nextInt(10) + 5;
                System.out.println("You rolled a small gain. That means that you gained " + score + " points.");
            }
            case 6 -> {
                score = rand.nextInt(15) + 20;
                System.out.println("You rolled a medium gain. That means that you gained " + score + " points.");
            }
            case 7 -> {
                score = 50;
                System.out.println("You got the tier 1 jackpot! You scored 50 points!");
            }
            case 8 -> {
                canAdvance = true;
                System.out.println("You rolled an advance. That means that you can advance to the next die whenever you want.");
            }
        }
        return score;
    }

    private int DieThree(){
        int rollValue = diceSet[1].RollDice();
        int score = 0;
        Random rand = new Random();
        switch(rollValue){
            case 1 -> {
                isLoss = true;
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose.");
            }
            case 2 -> {
                score = rand.nextInt(10) + 5;
                System.out.println("You rolled a small gain. That means that you gained " + score + " points.");
            }
            case 3 -> {
                score = rand.nextInt(15) + 20;
                System.out.println("You rolled a medium gain. That means that you gained " + score + " points.");
            }
            case 4 -> {
                score = 2;
                divider = true;
                System.out.println("Oh no, you rolled a divider! Your score will be cut in half. :(");
            }
            case 5 -> {
                score = 2;
                multiplier = true;
                System.out.println("Nice, you rolled a multiplier! Your score will be doubled! :)");
            }
            case 6 -> {
                score = - (rand.nextInt(10) + 5);
                System.out.println("You rolled a small loss. That means that you lost " + score + " points.");
            }
            case 7 -> {
                score = 100;
                System.out.println("You got the tier 2 jackpot! You scored 100 points!");
            }
            case 8 -> {
                score = - (rand.nextInt(15) + 20);
                System.out.println("You rolled a medium loss. That means that you lost " + score + " points.");
            }
            case 9 -> {
                System.out.println("You rolled a nothing. That means that nothing happens.");
            }
            case 10 -> {
                canAdvance = true;
                System.out.println("You rolled an advance. That means that you can advance to the next die whenever you want.");
            }
        }
        return score;
    }

    private int DieFour(){
        return 0;
    }

    private int DieFive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DieFive'");
    }

    public boolean HasLost() {
        return isLoss;
    }

    public boolean CanAdvance(){
        return canAdvance;
    }

    public void SetAdvance(boolean b) {
        canAdvance = b;
    }

}
