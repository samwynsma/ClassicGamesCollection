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
            
        }
        return cardFreqs;
    }
}
