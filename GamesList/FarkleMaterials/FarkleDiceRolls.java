
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

    public int[] GetDiceNums(int[] dice) {
        int[] categorize = new int[6];
        for(int i = 0; i < dice.length; i++)
        {
            categorize[dice[i]-1]++;
        }
        return categorize;
    }

    public List<String> GetValidScores(int[] diceCounts) {
        List<String> scores = new ArrayList<>();
        if(HasStraight(diceCounts))
        {
            scores.add("Straight: 1500 pts");
        }
        if(HasThreePairs(diceCounts))
        {
            scores.add("Three pairs: 1500 pts");
        }
        if(HasTwoTriplets(diceCounts))
        {
            scores.add("Two threes: 2500 pts");
        }
        List<String> threes = GetThrees(diceCounts);
        if(!threes.isEmpty())
        {
            scores.addAll(threes);
        }
        String fours = GetFours(diceCounts);
        String fives = GetFives(diceCounts);
        String sixes = GetSixes(diceCounts);
        if(fours.length() > 0)
        {
            scores.add(fours);
        }
        if(fives.length() > 0)
        {
            scores.add(fives);
        }
        if(sixes.length() > 0)
        {
            scores.add(sixes);
        }
        return scores;
    }

    private String GetSixes(int[] dice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetSixes'");
    }

    private String GetFives(int[] dice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetFives'");
    }

    private String GetFours(int[] dice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetFours'");
    }

    private List<String> GetThrees(int[] dice) {
        List<String> threes = new ArrayList<String>();
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] >= 3)
            {
                int dScore = GetDieVal(i+1);
                threes.add("Three " + (i+1) + "'s: " + dScore);
            }
        }
        return threes;
    }

    private int GetDieVal(int i) {
        if(i == 1)
        {
            return 1000;
        }
        return (i * 100);
    }

    private boolean HasTwoTriplets(int[] dice) {
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] != 3 && dice[i] != 0)
            {
                return false;
            }
        }
        return true;
    }



}
