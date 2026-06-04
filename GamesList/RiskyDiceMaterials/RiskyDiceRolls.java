package GamesList.RiskyDiceMaterials;

import GamesList.ToolsForMultipleGames.GameDice;

public class RiskyDiceRolls {

    public boolean isLoss;
    public boolean canAdvance;
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
    }

    public int RollDice(int currentDie) {
        switch(currentDie)
        {
            case 1:
                return DieOne();
            default:
                return DieFive();
        }
    }

    private int DieOne() {
        int rollValue = diceSet[0].RollDice();
        return rollValue;
    }

    private int DieTwo(){
        return 0;
    }

    private int DieThree(){
        return 0;
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

}
