package GamesList.BlackJackMaterials;

import GamesList.ToolsForMultipleGames.CardDeck;

public class BlackJackGameController {

    private CardDeck cards;
    private boolean playerOver;
    private boolean dealerOver;

    public boolean isGameOver;
    public BlackJackHand dealer;
    public BlackJackHand player;

    public BlackJackGameController(){
        char[] suits = new char[4];
        suits[0] = '\u2660';
        suits[1] = '\u2665';
        suits[2] = '\u2666';
        suits[3] = '\u2663';
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

    public void CleanupGame()
    {
        cards.DiscardAllCards(player.cards);
        cards.DiscardAllCards(dealer.cards);
    }

    public int DetermineWinner() {
        System.out.println("Your score is " + player.GetCardValue() + " and the dealer's score is " + dealer.GetCardValue());
        if(player.hasBusted())
        {
            System.out.println("Oh dear, you appear to have busted. The dealer has won.");
            return 2;
        }
        else if(dealer.hasBusted())
        {
            System.out.println("The dealer has busted, and you're still standing. You have won.");
            return 1;
        }
        else if(player.HasBlackjack() && !dealer.HasBlackjack())
        {
            System.out.println("You have blackjack, and thus have beaten the dealer.");
        }
        else if(player.HasBlackjack() && dealer.HasBlackjack())
        {
            System.out.println("You both have blackjack, so its a tie.");
        }
        else if(dealer.HasBlackjack())
        {
            System.out.println("The dealer has blackjack, so you lose.");
        }
        else if(player.GetCardValue() > dealer.GetCardValue())
        {
            System.out.println("You beat the dealer.");
            return 1;
        }
        else if(player.GetCardValue() < dealer.GetCardValue())
        {
            System.out.println("You lost to the dealer.");
            return 2;
        }
        System.out.println("Nobody wins! Yay!");
        return 0;
    }

}
