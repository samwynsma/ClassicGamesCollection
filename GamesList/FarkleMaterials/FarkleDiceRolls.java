
package GamesList.FarkleMaterials;

import java.util.ArrayList;
import java.util.List;

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
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] != 1)
                return false;
        }
        return true;
    }

    private boolean HasThreePairs(int[] dice) {
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] % 2 != 0)
                return false;
        }
        return true;
    }

    private int[] GetDiceNums(int[] dice) {
        int[] categorize = new int[6];
        for(int i = 0; i < dice.length; i++)
        {
            categorize[dice[i]-1]++;
        }
        return categorize;
    }

    public List<String> GetValidScores(int[] dice) {
        List<String> scores = new ArrayList<>();
        if(HasStraight(dice))
        {
            scores.add("Straight: 1500 pts");
        }
        if(HasThreePairs(dice))
        {
            scores.add("Three pairs: 1500 pts");
        }
        return scores;
    }



}
