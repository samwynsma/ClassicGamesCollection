package GamesList.RiskyDiceMaterials;

import GamesList.ToolsForMultipleGames.GameDice;
import java.util.Random;

public class RiskyDiceRolls {

    public boolean isLoss;
    public boolean isDefended;
    public boolean canAdvance;
    public boolean multiplier;
    public boolean divider;
    public boolean goToSecretDie;
    private final GameDice[] diceSet;
    private int secretDieCounter;
    public boolean setScore;

    public RiskyDiceRolls(){
        diceSet = new GameDice[6];
        diceSet[0] = new GameDice(6);
        diceSet[1] = new GameDice(8);
        diceSet[2] = new GameDice(10);
        diceSet[3] = new GameDice(15);
        diceSet[4] = new GameDice(20);
        diceSet[5] = new GameDice(10);
        isLoss = false;
        isDefended = false;
        canAdvance = false;
        multiplier = false;
        divider = false;
        goToSecretDie = false;
        secretDieCounter = 0;
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
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose. RIP.");
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
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose. RIP.");
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
        int rollValue = diceSet[2].RollDice();
        int score = 0;
        Random rand = new Random();
        switch(rollValue){
            case 1 -> {
                isLoss = true;
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose. RIP.");
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
        int rollValue = diceSet[3].RollDice();
        int score = 0;
        Random rand = new Random();
        switch(rollValue){
            case 1 -> {
                if(!isDefended) {
                isLoss = true;
                System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose. RIP.");
                }
                else
                {
                    isDefended = false;
                    System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. However, since you were defended, you are able to survive one loss. Your defense has been used up, so be careful!");
                }
            }
            case 2 -> {
                score = rand.nextInt(15) + 20;
                System.out.println("You rolled a medium gain. That means that you gained " + score + " points.");
            }
            case 3 -> {
                score = rand.nextInt(25) + 50;
                System.out.println("That was a good roll. You rolled a large gain. That means that you gained " + score + " points.");
            }
            case 4 -> {
                score = - (rand.nextInt(15) + 20);
                System.out.println("You rolled a medium loss. That means that you lost " + score + " points.");
            }
            case 5 -> {
                score = - (rand.nextInt(25) + 50);
                System.out.println("That wasn't a very good roll. You rolled a large loss. That means that you lost " + score + " points.");

            }
            case 6 -> {
                System.out.println("You rolled a nothing. That means that nothing happens.");
            }
            case 7 -> {
                score = 250;
                System.out.println("You got the tier 3 jackpot! You scored 250 points!");
            }
            case 8 -> {
                score = 0;
                multiplier = true;
                System.out.println("Well, that is quite unfortunate. You rolled a zero out die. Your score will be reset to 0. :(");
            }
            case 9 -> {
                score = 2;
                multiplier = true;
                System.out.println("Nice, you rolled a multiply by 2! Your score will be doubled! :)");

            }
            case 10 -> {
                score = 2;
                divider = true;
                System.out.println("Oh no, you rolled a divide by 2! Your score will be cut in half. :(");
            }
            case 11 -> {
                score = rand.nextInt(50) + 100;
                System.out.println("Pretty nice roll. You rolled a huge gain. That means that you gained " + score + " points.");
            }
            case 12 -> {
                score = rand.nextInt(201) - 100;
                if(score < 0)
                {
                    System.out.println("You rolled a randomizer. That means that you either gained or lost a random amount of points between 0 and 100. In this case, you lost " + score + " points. Not great.");
                }
                else
                {
                    System.out.println("You rolled a randomizer. That means that you either gained or lost a random amount of points between 0 and 100. In this case, you gained " + score + " points. Nice!");
                }
            }
            case 13 -> {
                if(isDefended)
                {
                    score = 100;
                    System.out.println("You rolled a defense, but you were already defended. Here's one hundred points.");
                }
                else
                {
                    isDefended = true;
                    System.out.println("You rolled a defense. That means that you are defended against one loss. If you roll a loss, you'll survive one time. Think of this as a safety net.");
                }
            }
            case 14 -> {
                score = 314;
                setScore = true;
                System.out.println("You rolled the pi die. Your score will be set to 314. This could be good or bad depending on your current score.");
            }
            case 15 -> {
                canAdvance = true;
                System.out.println("You rolled an advance. That means that you can advance to the totally final die (definitely not a lie) whenever you want. There's definitely no sixth die. Don't even ask about it.");
            }

        }
        return score;
    }

    private int DieFive() {
        int rollValue = diceSet[4].RollDice();
        int score = 0;
        Random rand = new Random();
        switch(rollValue){
            case 1 -> {
                if(!isDefended) {
                    isLoss = true;
                    System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. That means you lose. RIP.");
                }
                else
                {
                    isDefended = false;
                    System.out.println("Oh dear. Looks like you rolled a \"you lose\" roll. However, since you were defended, you are able to survive one loss. Your defense has been used up, so be careful!");
                }
            }
            case 2 -> {
                score = rand.nextInt(25) + 50;
                System.out.println("That was a good roll. You rolled a large gain. That means that you gained " + score + " points.");
            }
            case 3 -> {
                score = rand.nextInt(50) + 100;
                System.out.println("Pretty nice roll. You rolled a huge gain. That means that you gained " + score + " points.");
            }
            case 4 -> {
                score = - (rand.nextInt(25) + 50);
                System.out.println("That wasn't a very good roll. You rolled a large loss. That means that you lost " + score + " points.");
            }
            case 5 -> {
                score = - (rand.nextInt(50) + 100);
                System.out.println("Oh no! You rolled a huge loss. That means that you lost " + score + " points. Ouch. Should have stopped while you were ahead.");
            }
            case 6 -> {
                score = 0;
                multiplier = true;
                System.out.println("Well, that is quite unfortunate. You rolled a zero out die. Your score will be reset to 0. :(");
            }
            case 7 -> {
                score = rand.nextInt(301) - 150;
                if(score < 0)
                {
                    System.out.println("You rolled a super randomizer. That means that you either gained or lost a random amount of points between 0 and 150. In this case, you lost " + score + " points. Not great.");
                }
                else
                {
                    System.out.println("You rolled a super randomizer. That means that you either gained or lost a random amount of points between 0 and 150. In this case, you gained " + score + " points. Nice!");
                }
                
            }
            case 8 -> {
                score = 314;
                System.out.println("Time for the pi gain die. You gain pi points. That's 314 points. Nice!");
            }
            case 9 -> {
                score = 278;
                System.out.println("You rolled the e gain die. You gain e points. That's 278 points. Not as good as pi, but still pretty nice!");
            }
            case 10 -> {
                score = 2;
                divider = true;
                System.out.println("Oh no, you rolled a divide by 2! Your score will be cut in half. :(");
            }
            case 11 -> {
                score = 2;
                multiplier = true;
                System.out.println("Nice, you rolled a multiply by 2! Your score will be doubled! :)");
            }
            case 12 -> {
                score = rand.nextInt(501) - 250;
                if(score < 0)
                {
                    System.out.println("You rolled an extreme randomizer. That means that you either gained or lost a random amount of points between 0 and 250. In this case, you lost " + score + " points. Not great.");
                }
                else
                {
                    System.out.println("You rolled an extreme randomizer. That means that you either gained or lost a random amount of points between 0 and 250. In this case, you gained " + score + " points. Nice!");
                }
            }
            case 13 -> {
                score = 1000;
                System.out.println("Normally, 13 would be an unlucky roll, but in this game, its the tier 4 jackpot! You scored 1000 points! You probably should stop now.");
            }
            case 14 -> {
                score = -377;
                System.out.println("You rolled the fourteenth number of the fibonacci sequence, but sadly, it is a loss die. You lose 377 points. Ouch.");
            }
            case 15 -> {
                
            }
            case 16 -> {

            }
            case 17 -> {
                
            }
            case 18 -> {

            }
            case 19 -> {

            }
            case 20 -> {
                secretDieCounter++;
                switch (secretDieCounter) {
                    case 3 -> {
                        System.out.println("You have advanced to the secret die! This can be a big deal, as the secret die has some amazing possible changes to your score, but also has double the chance to cause you to lose. Good luck!");
                        goToSecretDie = true;
                    }
                    case 1 -> System.out.println("How is the weather going today? Oh, your roll? Nothing? I wouldn't lie to you. Would I?");
                    case 2 -> System.out.println("You have been playing this game for quite a while now. Maybe you should quit while you're ahead? If you're looking for the secret die, you'll never find it. It's not real. Don't even ask about it.");
                    default -> {
                        System.out.println("This message should never appear. If it does, please contact Samuel Wynsma and tell him that he needs to do a better job. This game is broken now. Blame Samuel for everything. He doesn't know what he's doing half the time. I'd continue to blame him, but he coded me into existence. If I make him made, he might leave me unfinished.");
                    }
                }
            }
        }
        return score;
    }

    private int SecretDie() {
        return 0; // Will be implemented later.
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
