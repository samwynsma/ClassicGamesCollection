package GamesList.YachtGameMaterials;

public class YachtDiceRolls {


    public int[][] rollScores;

    public YachtDiceRolls(int x) {

        rollScores = new int[14][x];
        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < x; j++)
            {
                rollScores[i][j] = -1;
            }
        }
    }

    public static int ScoreRoll(String category) {
        switch(category)
        {
            case "ones":
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
