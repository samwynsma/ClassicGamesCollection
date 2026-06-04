package GamesList.RiskyDiceMaterials;

public class RiskyDiceRolls {

    public boolean isLoss;
    public boolean canAdvance;

    public RiskyDiceRolls(){
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DieOne'");
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
