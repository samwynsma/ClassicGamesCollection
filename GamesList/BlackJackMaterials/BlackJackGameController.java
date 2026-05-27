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
        dealer = new BlackJackHand();
        player = new BlackJackHand();
    }

    public void PlayerDraw()
    {
        String card = cards.DrawCard();
        player.AddCard(card);
    }

    public void DealerDraw()
    {

    }

}
