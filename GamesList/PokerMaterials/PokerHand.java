package GamesList.PokerMaterials;

import java.util.*;

import GamesList.ToolsForMultipleGames.PokerHands;

public class PokerHand {
    private List<String> cards;
    private String defaultCards = "23456789TJQKA";
    private int handValue;

    public PokerHand()
    {
        cards = new ArrayList<String>();
        handValue = 0;
    }

    public List<String> Discard(List<Integer> discardLocs)
    {
        List<String> newCards = new ArrayList<String>();
        List<String> discarded = new ArrayList<String>();
        for(int i = 0; i < cards.size(); i++)
        {
            if(!discardLocs.contains(i))
            {
                newCards.add(cards.get(i));
            }
            else
            {
                discarded.add(cards.get(i));
            }
        }
        cards = newCards;
        return discarded;
    }

    public void AddCard(String card)
    {
        cards.add(card);
    }

    public int GetHandValue()
    {
        return handValue;
    }

    public void SetHandValue()
    {
        String[] cardSet = new String[cards.size()];
        for(int i = 0; i < cardSet.length; i++)
        {
            cardSet[i] = cards.get(i);
        }
        int[] freq = PokerHands.GetCardFrequencies(defaultCards, cardSet);
        handValue = 0;
    }
}
