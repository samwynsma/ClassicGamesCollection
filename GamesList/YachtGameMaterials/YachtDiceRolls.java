package GamesList.YachtGameMaterials;

public class YachtDiceRolls {


    public int[][] rollScores;
    public int rounds;

    public YachtDiceRolls(int x) {

        rounds = x;
        rollScores = new int[14][x];
        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < x; j++)
            {
                rollScores[i][j] = -1;
            }
        }
    }

    public int[] GetDiceNums(int[] roll)
    {
        int[] categorize = new int[6];
        for(int i = 0; i < roll.length; i++)
        {
            categorize[roll[i]-1]++;
        }
        return categorize;
    }

    public int ScoreRoll(String category, int[] roll) {
        int[] organizedDice = GetDiceNums(roll);
        switch(category)
        {
            case "ones":
                int index = 0;
                while(rollScores[0][index] == -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[0][index] = organizedDice[0];
                    return rollScores[0][index];
                }
                break;
            case "twos":
                break;
            case "threes":
                break;
            case "fours":
                break;
            case "fives":
                break;
            case "sixes":
                break;
            case "two pair":
                break;
            case "three of a kind":
                break;
            case "four straight":
                break;
            case "five straight":
                break;
            case "full house":
                break;
            case "four of a kind":
                break;
            case "free":
                break;
            case "yacht":
                break;
        }
        return -1;
    }

}
