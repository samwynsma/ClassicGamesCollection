import java.util.Scanner;

public class MainMenu {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args)
    {
        System.out.println("Welcome to the classic games collection!");
        Scanner menuPrompt = new Scanner(System.in);
        String menuString = "";
        GameSelector games = new GameSelector();

        while(!menuString.equals("quit"))
        {
            System.out.println("Select a game from our menu by typing in the number of the game. Type in \"Quit\" to quit \n 1. Connect 4 \n 2. Mastermind \n 3. Blackjack \n 4. Yacht \n 5. Risky Dice");
            menuString = menuPrompt.nextLine().toLowerCase();
            games.ChooseGame(menuString, menuPrompt);
        }

        System.out.println("Thank you for playing the classic games collection. \n Credits: \n Samuel Wynsma, programmer.");
        menuPrompt.close();
    }
}
