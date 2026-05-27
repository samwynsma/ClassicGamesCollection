package GamesList.BlackJackMaterials;

import GamesList.ToolsForMultipleGames.CardDeck;
import java.util.*;

public class BlackJackHand {
    List<String> cards;
    int totalCardValue;
    int visibleCardValue;

    public BlackJackHand()
    {
        cards = new ArrayList<String>();
        totalCardValue = 0;
        visibleCardValue = 0;
    }
}
