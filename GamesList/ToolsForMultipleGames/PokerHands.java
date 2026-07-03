package GamesList.ToolsForMultipleGames;

public class PokerHands {

    public static boolean HasFlush(String[] cards)
    {
        char suit = cards[0].charAt(0);
        for(int i = 0; i < cards.length; i++)
        {
            if(cards[i].charAt(0) != suit)
            {
                return false;
            }
        }
        return true;
    }

    public static int[] GetCardFrequencies(int maxCard, String[] cards)
    {
        int[] cardFreqs = new int[maxCard];
        for(int i = 0; i < maxCard; i++)
        {
            for(int j = 0; j < cards.length; j++)
            {
                String cardRank = cards[j].substring(1);
                if(Integer.parseInt(cardRank) == i+1)
                {
                    cardFreqs[i]++;
                }
            }
        }
        return cardFreqs;
    }

    public static int[] GetCardFrequencies(String cardTypes, String[] cards)
    {
        int[] cardFreqs = new int[cardTypes.length()];
        for(int i = 0; i < cardTypes.length(); i++)
        {
            for(int j = 0; j < cards.length; j++)
            {
                char cardRank = cards[j].charAt(1);
                if(cardRank == cardTypes.charAt(i))
                {
                    cardFreqs[i]++;
                }
            }
        }
        return cardFreqs;
    }

    public static boolean HasFiveOfAKind(int[] cardFreqs)
    {
        for(int i = 0; i < cardFreqs.length; i++)
        {
            if(cardFreqs[i] >= 5)
                return true;
        }
        return false;
    }

    public static boolean HasFourOfAKind(int[] cardFreqs)
    {
        for(int i = 0; i < cardFreqs.length; i++)
        {
            if(cardFreqs[i] >= 4)
                return true;
        }
        return false;
    }

    public static boolean HasThreeOfAKind(int[] cardFreqs)
    {
        for(int i = 0; i < cardFreqs.length; i++)
        {
            if(cardFreqs[i] >= 3)
                return true;
        }
        return false;
    }

    public static boolean HasPair(int[] cardFreqs)
    {
        for(int i = 0; i < cardFreqs.length; i++)
        {
            if(cardFreqs[i] >= 2)
                return true;
        }
        return false;
    }
}
