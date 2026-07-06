package GamesList.PokerMaterials;

import GamesList.ToolsForMultipleGames.CardDeck;

public class PokerGameController {

    private CardDeck cards;
    public int players;
    public int currentPlayer;

    public PokerGameController(int players) {
        char[] suits = new char[4];
        suits[0] = '\u2660';
        suits[1] = '\u2665';
        suits[2] = '\u2666';
        suits[3] = '\u2663';
        this.players = players;
        cards = new CardDeck(suits);
        PokerHand[] hands = new PokerHand[players];
        for(int i = 0; i < hands.length; i++)
        {
            hands[i] = new PokerHand();
        }
        currentPlayer = 0;
    }
    
}
