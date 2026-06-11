
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
        List<String> oneFiveOne = GetOneFiveOne(diceCounts);
        List<String> twoFiveOne = GetTwoFiveOne(diceCounts);
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
        if(!oneFiveOne.isEmpty())
        {
            scores.addAll(oneFiveOne);
        }
        if(!twoFiveOne.isEmpty())
        {
            scores.addAll(twoFiveOne);
        }
        return scores;
    }

    private String GetSixes(int[] dice) {
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] >= 5)
            {
                int dScore = GetDieVal(i+1) * 4;
                return "Five " + (i+1) + "'s: " + dScore + " pts";
            }
        }
        return "";
    }

    private String GetFives(int[] dice) {
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] == 6)
            {
                int dScore = GetDieVal(i+1) * 8;
                return "Six " + (i+1) + "'s: " + dScore + " pts";
            }
        }
        return "";
    }

    private String GetFours(int[] dice) {
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] >= 4)
            {
                int dScore = GetDieVal(i+1) * 2;
                return "Four " + (i+1) + "'s: " + dScore + " pts";
            }
        }
        return "";
    }

    private List<String> GetThrees(int[] dice) {
        List<String> threes = new ArrayList<>();
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] >= 3)
            {
                int dScore = GetDieVal(i+1);
                threes.add("Three " + (i+1) + "'s: " + dScore + " pts");
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
    
    private List<String> GetOneFiveOne(int[] diceCounts) {
        List<String> oneFive = new ArrayList<>();
        if(diceCounts[0] >= 1)
        {
            oneFive.add("One one: 100 pts");
        }
        if(diceCounts[4] >= 1)
        {
            oneFive.add("One five: 50 pts");
        }
        if(diceCounts[0] >= 1 && diceCounts[4] >= 1)
        {
            oneFive.add("One one and one five: 150 pts");
        }
        return oneFive;
    }

    private List<String> GetTwoFiveOne(int[] diceCounts) {
        List<String> oneFive = new ArrayList<>();
        if(diceCounts[0] >= 2)
        {
            oneFive.add("Two ones: 200 pts");
        }
        if(diceCounts[4] >= 2)
        {
            oneFive.add("Two fives: 100 pts");
        }
        if(diceCounts[0] >= 2 && diceCounts[4] >= 2)
        {
            oneFive.add("Two fives and two ones: 300 pts");
        }
        if(diceCounts[0] >= 1 && diceCounts[4] >= 2)
        {
            oneFive.add("Two fives and one one: 200 pts");
        }
        if(diceCounts[0] >= 2 && diceCounts[4] >= 1)
        {
            oneFive.add("Two ones and one five: 250 pts");
        }
        return oneFive;
    }

    public int[][] ParseScores(List<String> options)
    {
        int[][] scores = new int[options.size()][2];
        for(int i = 0; i < options.size(); i++)
        {
            String[] optionsStr = options.get(i).split(" ");
            int scoreVal = Integer.parseInt(optionsStr[optionsStr.length-2]);
            int diceUsed = 0;
            if(optionsStr[0].equals("Straight") || optionsStr[0].equals("Two"))
            {
                diceUsed = 6;
            }
            if(optionsStr[0].equals("Three") && optionsStr[1].equals("Pair"))
            {
                diceUsed = 6;
            }
            if(optionsStr[0].equals("Six"))
            {
                diceUsed = 6;
            }
            if(optionsStr[0].equals("Five"))
            {
                diceUsed = 5;
            }
            if(optionsStr[0].equals("Four"))
            {
                diceUsed = 4;
            }
            if(optionsStr[0].equals("Three"))
            {
                diceUsed = 3;
            }
            if(optionsStr[0].equals("Two"))
            {
                diceUsed = 2;
            }
            if(optionsStr[0].equals("One"))
            {
                diceUsed = 1;
            }

            if(optionsStr.length == 7)
            {
                if(optionsStr[3].equals("two"))
                {
                    diceUsed += 2;
                }
                if(optionsStr[3].equals("one"))
                {
                    diceUsed++;
                }
            }
            scores[i] = new int[] {scoreVal, diceUsed};
        }
        return scores;
    }
}
