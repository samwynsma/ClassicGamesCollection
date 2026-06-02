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

    public int ScoreRoll(String category, int[] roll, int diceTotal) {
        int[] organizedDice = GetDiceNums(roll);
        int index = 0;
        switch(category)
        {
            case "ones":
                index = 0;
                while(index < rounds && rollScores[0][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[0][index] = organizedDice[0];
                    return rollScores[0][index];
                }
                break;
            case "twos":
                index = 0;
                while(index < rounds && rollScores[1][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[1][index] = organizedDice[1] * 2;
                    return rollScores[1][index];
                }
                break;
            case "threes":
                index = 0;
                while(index < rounds && rollScores[2][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[2][index] = organizedDice[2] * 3;
                    return rollScores[2][index];
                }
                break;
            case "fours":
                index = 0;
                while(index < rounds && rollScores[3][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[3][index] = organizedDice[3] * 4;
                    return rollScores[3][index];
                }
                break;
            case "fives":
                index = 0;
                while(index < rounds && rollScores[4][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[4][index] = organizedDice[4] * 5;
                    return rollScores[4][index];
                }
                break;
            case "sixes":
                index = 0;
                while(index < rounds && rollScores[5][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[5][index] = organizedDice[5] * 6;
                    return rollScores[5][index];
                }
                break;
            case "two pair":
                index = 0;
                while(index < rounds && rollScores[6][index] != -1)
                    index++;
                if(index < rounds)
                {
                    int twos = 0;
                    for(int i = 0; i < organizedDice.length; i++)
                    {
                        if(organizedDice[i] >= 2)
                            twos++;
                    }
                    if(twos >= 2)
                    {
                        rollScores[6][index] = diceTotal;
                        return rollScores[6][index];
                    }
                    else
                    {
                        rollScores[6][index] = 0;
                        return 0;
                    }
                }
                break;
            case "three of a kind":
                index = 0;
                while(index < rounds && rollScores[7][index] != -1)
                    index++;
                if(index < rounds)
                {
                    for(int i = 0; i < organizedDice.length; i++)
                    {
                        if(organizedDice[i] >= 3)
                        {
                            rollScores[7][index] = diceTotal + 10;
                            return rollScores[7][index];
                        }
                    }
                    rollScores[7][index] = 0;
                    return 0;
                }
                break;
            case "four straight":
                index = 0;
                while(index < rounds && rollScores[8][index] != -1)
                {
                    index++;
                }
                if(index < rounds)
                {
                    boolean hasStraight = false;
                    if(organizedDice[0] >= 1 && organizedDice[1] >= 1 && organizedDice[2] >= 1 && organizedDice[3] >= 1)
                    {
                        hasStraight = true;
                    }
                    if(organizedDice[1] >= 1 && organizedDice[2] >= 1 && organizedDice[3] >= 1 && organizedDice[4] >= 1)
                    {
                        hasStraight = true;
                    }
                    if(organizedDice[2] >= 1 && organizedDice[3] >= 1 && organizedDice[4] >= 1 && organizedDice[5] >= 1)
                    {
                        hasStraight = true;
                    }
                    if(hasStraight)
                    {
                        rollScores[8][index] = 25;
                        return 25;
                    }
                    rollScores[8][index] = 0;
                    return 0;
                }
                break;
            case "five straight":
                index = 0;
                while(index < rounds && rollScores[9][index] != -1)
                {
                    index++;
                }
                if(index < rounds)
                {
                    boolean hasStraight = false;
                    if(organizedDice[0] == 1 && organizedDice[1] == 1 && organizedDice[2] == 1 && organizedDice[3] == 1 && organizedDice[4] == 1)
                    {
                        hasStraight = true;
                    }
                    if(organizedDice[1] == 1 && organizedDice[2] == 1 && organizedDice[3] == 1 && organizedDice[4] == 1 && organizedDice[5] == 1)
                    {
                        hasStraight = true;
                    }
                    if(hasStraight)
                    {
                        rollScores[9][index] = 35;
                        return 35;
                    }
                    rollScores[9][index] = 0;
                    return 0;
                }
                break;
            case "full house":
                index = 0;
                while(index < rounds && rollScores[10][index] != -1)
                    index++;
                if(index < rounds)
                {
                    boolean hasThree = false;
                    boolean hasTwo = false;
                    for(int i = 0; i < organizedDice.length; i++)
                    {
                        if(organizedDice[i] == 3)
                            hasThree = true;
                        else if(organizedDice[i] == 2)
                            hasTwo = true;
                    }
                    if(hasTwo && hasThree)
                    {
                        rollScores[10][index] = diceTotal + 20;
                        return rollScores[10][index];
                    }
                    rollScores[10][index] = 0;
                    return 0;
                }
                break;
            case "four of a kind":
                index = 0;
                while(index < rounds && rollScores[11][index] != -1)
                    index++;
                if(index < rounds)
                {
                    for(int i = 0; i < organizedDice.length; i++)
                    {
                        if(organizedDice[i] >= 4)
                        {
                            rollScores[11][index] = diceTotal + 30;
                            return rollScores[11][index];
                        }
                    }
                    rollScores[11][index] = 0;
                    return 0;
                }
                break;
            case "free":
                index = 0;
                while(index < rounds && rollScores[12][index] != -1)
                    index++;
                if(index < rounds)
                {
                    rollScores[12][index] = diceTotal;
                    return rollScores[12][index];
                }
                break;
            case "yacht":
                index = 0;
                while(index < rounds && rollScores[13][index] != -1)
                    index++;
                if(index < rounds)
                {
                    for(int i = 0; i < organizedDice.length; i++)
                    {
                        if(organizedDice[i] == 5)
                        {
                            rollScores[13][index] = diceTotal + 50;
                            return rollScores[13][index];
                        }
                    }
                    rollScores[13][index] = 0;
                    return 0;
                }
                break;
        }
        return -1;
    }

}
