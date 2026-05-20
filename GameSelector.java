import GamesList.ConnectFourGame;

public class GameSelector {

    public GameSelector()
    {

    }

    public void ChooseGame(String menuString) {
        switch(menuString)
        {
            case "1":
                ConnectFourGame connectFour = new ConnectFourGame();
                connectFour.PlayGame();
                break;
            default:
                System.out.println("Invalid game selection. Please select a number on the list. Further games might exist later.");
                break;
        }
    }
}
