package GamesList.BlackJackMaterials;

import GamesList.ToolsForMultipleGames.CardDeck;

public class BlackJackGameController {

    private CardDeck cards;
    private BlackJackHand dealer;
    private BlackJackHand player;
    private boolean isGameOver;
    private boolean playerOver;
    private boolean dealerOver;

    public BlackJackGameController(){
        char[] suits = new char[4];
        suits[0] = '♠';
        suits[1] = '♥';
        suits[2] = '♦';
        suits[3] = '♥';
        isGameOver = false;
        cards = new CardDeck(suits);
        dealer = new BlackJackHand();
        player = new BlackJackHand();
        PlayerDraw();
        PlayerDraw();
        DealerDraw();
        DealerDraw();
    }

    public void PlayerDraw()
    {
        String card = cards.DrawCard();
        player.AddCard(card);
        if(player.hasBusted())
        {
            isGameOver = true;
        }
    }

    public void DealerDraw()
    {
        String card = cards.DrawCard();
        dealer.AddCard(card);
        if(dealer.hasBusted())
        {
            isGameOver = true;
        }
    }

    public void PlayerQuits()
    {
        playerOver = true;
        CheckGame();
    }

    public void DealerQuits()
    {
        dealerOver = true;
        CheckGame();
    }

    public void CheckGame()
    {
        if(dealerOver && playerOver)
        {
            isGameOver = true;
        }
    }

}
