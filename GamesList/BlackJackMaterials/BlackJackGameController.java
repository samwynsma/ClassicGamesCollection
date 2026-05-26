package GamesList.BlackJackMaterials;

import GamesList.ToolsForMultipleGames.CardDeck;

public class BlackJackGameController {

    private CardDeck cards;

    public BlackJackGameController(){
        char[] suits = new char[4];
        suits[0] = '♠';
        suits[1] = '♥';
        suits[2] = '♦';
        suits[3] = '♥';
        cards = new CardDeck(suits);
    }
}
