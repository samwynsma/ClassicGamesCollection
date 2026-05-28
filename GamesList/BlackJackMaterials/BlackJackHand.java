package GamesList.BlackJackMaterials;

import java.util.*;

public class BlackJackHand {
    List<String> cards;
    int totalCardValue;
    int visibleCardValue;
    boolean hasAce;
    boolean isBusted;

    public BlackJackHand()
    {
        cards = new ArrayList<String>();
        totalCardValue = 0;
        visibleCardValue = 0;
        hasAce = false;
        isBusted = false;
    }

    public void AddCard(String card) {
        char rank = card.charAt(1);
        int cardVal = 0;
        switch(rank)
        {
            case 'A':
                if(!hasAce && totalCardValue < 11) {
                    hasAce = true;
                    cardVal += 11;
                }
                else {
                    cardVal++;
                }
                break;
            case 'K': case 'Q': case 'J': case 'T':
                cardVal += 10;
                break;
            default:
                cardVal += (int)(rank - '0');
                break;
        }

        if(cards.size() > 0)
        {
            visibleCardValue += cardVal;
        }
        totalCardValue += cardVal;

        if(totalCardValue > 21)
        {
            if(hasAce)
            {
                hasAce = false;
                totalCardValue -= 10;
            }
            else
            {
                isBusted = true;
            }
        }

        cards.add(card);
    }

    public int GetCardValue()
    {
        return totalCardValue;
    }

    public int GetVisibleCardValue()
    {
        return visibleCardValue;
    }

    public boolean hasBusted()
    {
        return isBusted;
    }

    public String DisplayFullHand()
    {
        StringBuilder hand = new StringBuilder();
        for(int i = 0; i < cards.size(); i++)
        {
            hand.append(cards.get(i));
            if(i + 1 < cards.size())
                hand.append(" ");
        }
        return hand.toString();
    }

    public String DisplayVisibleHand()
    {
        StringBuilder hand = new StringBuilder("?? ");
        for(int i = 1; i < cards.size(); i++)
        {
            hand.append(cards.get(i));
            if(i + 1 < cards.size())
                hand.append(" ");
        }
        return hand.toString();
    }

    public boolean HasBlackjack()
    {
        if(totalCardValue == 21 && cards.size() == 2)
        {
            if(cards.get(0).charAt(1) == 'J')
                return true;
            if(cards.get(1).charAt(1) == 'J')
                return true;
        }
        return false;
    }

}
