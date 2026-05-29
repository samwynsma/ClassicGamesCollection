package GamesList.YachtGameMaterials;

public class YachtDiceRolls {

    public int[] ones;
    public int[] twos;
    public int[] threes;
    public int[] fours;
    public int[] fives;
    public int[] sixes;
    public int[] twoPair;
    public int[] threeKind;
    public int[] fullHouse;
    public int[] fourStraight;
    public int[] fiveStraight;
    public int[] fourKind;
    public int[] free;
    public int[] yacht;
    public YachtDiceRolls(int x) {
        ones = new int[x];
        twos = new int[x];
        threes = new int[x];
        fours = new int[x];
        fives = new int[x];
        sixes = new int[x];
        twoPair = new int[x];
        threeKind = new int[x];
        fullHouse = new int[x];
        fourStraight = new int[x];
        fiveStraight = new int[x];
        fourKind = new int[x];
        free = new int[x];
        yacht = new int[x];
        for(int i = 0; i < x; i++)
        {
            ones[i] = -1;
            twos[i] = -1;
            threes[i] = -1;
            fours[i] = -1;
            fives[i] = -1;
            sixes[i] = -1;
            twoPair[i] = -1;
            threeKind[i] = -1;
            fullHouse[i] = -1;
            fourStraight[i] = -1;
            fiveStraight[i] = -1;
            fourKind[i] = -1;
            free[i] = -1;
            yacht[i] = -1;
        }
    }

    public static int ScoreRoll(String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ScoreRoll'");
    }

}
