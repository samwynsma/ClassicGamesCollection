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
    }

    public String DrawCard(String[] handCards)
    {
        String cardToRemove = drawDeck.get(drawDeck.size()-1);
        drawDeck.remove(drawDeck.size()-1);
        discardDeck.add(cardToRemove);
        return cardToRemove;
    }
    
}
