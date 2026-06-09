package GamesList.ToolsForMultipleGames;

import java.util.*;

public class CardDeck {

    String[] cards;
    List<String> drawDeck;
    List<String> discardDeck;
    public CardDeck(char[] suits) {
        String cardVals = "23456789TJQKA";
        cards = new String[suits.length * cardVals.length()];
        for(int i = 0; i < suits.length; i++)
        {
            for(int j = 0; j < 13; j++)
            {
                StringBuilder card = new StringBuilder();
                card.append(suits[i]);
                card.append(cardVals.charAt(j));
                cards[i * 13 + j] = card.toString();
            }
        }
        InitialShuffle();
    }

    public CardDeck(char[] suits, int cardCount) {
        cards = new String[suits.length * cardCount];
        for(int i = 0; i < suits.length; i++)
        {
            for(int j = 0; j < cardCount; j++)
            {
                StringBuilder card = new StringBuilder();
                card.append(suits[i]);
                card.append(j+1);
                cards[i * cardCount + j] = card.toString();
            }
        }
        InitialShuffle();
    }

    public String DrawCard()
    {
        String cardToRemove = drawDeck.get(drawDeck.size()-1);
        drawDeck.remove(drawDeck.size()-1);
        if(drawDeck.size() == 0)
        {
            ShuffleDeck();
        }
        return cardToRemove;
    }

    public void DiscardCard(List<String> cardsInUse, String card)
    {
        cardsInUse.remove(card);
        discardDeck.add(card);
    }

    public void DiscardAllCards(List<String> cardsInUse)
    {
        for(int i = 0; i < cardsInUse.size(); i++)
        {
            discardDeck.add(cardsInUse.get(i));
        }
    }

    public void InitialShuffle()
    {
        Random cardRandomizer = new Random();
        drawDeck = new ArrayList<String>();
        discardDeck = new ArrayList<String>();
        List<String> allCards = new ArrayList<String>();
        for(int i = 0; i < cards.length; i++)
        {
            allCards.add(cards[i]);
        }
        while(!allCards.isEmpty())
        {
            int cardLoc = cardRandomizer.nextInt(allCards.size());
            String card = allCards.get(cardLoc);
            drawDeck.add(card);
            allCards.remove(cardLoc);
        }
    }

    public void ShuffleDeck()
    {
        Random cardRandomizer = new Random();
        while(discardDeck.size() > 0)
        {
            int cardLoc = cardRandomizer.nextInt(discardDeck.size());
            String card = discardDeck.get(cardLoc);
            drawDeck.add(card);
            discardDeck.remove(cardLoc);
        }
    }
    
}
