package GamesList.BlackJackMaterials;

import GamesList.ToolsForMultipleGames.CardDeck;

public class BlackJackGameController {

    private CardDeck cards;
    private BlackJackHand dealer;
    private BlackJackHand player;

    public BlackJackGameController(){
        char[] suits = new char[4];
        suits[0] = '♠';
        suits[1] = '♥';
        suits[2] = '♦';
        suits[3] = '♥';
        cards = new CardDeck(suits);
    }

    public void PlayerDraw()
    {
        
    }

    public void DealerDraw()
    {

    }

}
