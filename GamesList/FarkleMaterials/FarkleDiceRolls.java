
package GamesList.FarkleMaterials;


public class FarkleDiceRolls {

    public FarkleDiceRolls() {

    }

    public boolean CheckZonk(int[] dice) 
    {
        int[] diceFreqs = GetDiceNums(dice);
        for(int i = 0; i < diceFreqs.length; i++)
        {
            if(diceFreqs[i] >= 3)
                return false;
            if(i == 0 || i == 4)
            {
                if(diceFreqs[i] >= 1)
                    return false;
            }
        }
        if(dice.length == 6)
        {
            if(HasThreePairs(dice) || HasStraight(dice))
            {
                return false;
            }
        }
        return true;
    }

    private boolean HasStraight(int[] dice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HasStraight'");
    }

    private boolean HasThreePairs(int[] dice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'HasThreePairs'");
    }

    private int[] GetDiceNums(int[] dice) {
        int[] categorize = new int[6];
        for(int i = 0; i < dice.length; i++)
        {
            categorize[dice[i]-1]++;
        }
        return categorize;
    }

    public String[] GetValidScores() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
