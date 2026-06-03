package GamesList.RiskyDiceMaterials;

public class RiskyDiceGameController {

    private int[] playerScores;
    private int players;
    private int currentPlayer;

    public RiskyDiceGameController(int players) {
        this.players = players;
        this.playerScores = new int[players];
        this.currentPlayer = 1;
    }

    public boolean IsGameOver()
    {
        return currentPlayer > players;
    }

    public int GetCurrentPlayer() {
        return currentPlayer;
    }
    
}
