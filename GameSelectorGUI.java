import java.util.Scanner;

public class GameSelectorGUI {
    private final GameSelector gameSelector;
    private final Scanner consoleScanner;

    public GameSelectorGUI() {
        gameSelector = new GameSelector();
        consoleScanner = new Scanner(System.in);
    }

    public void chooseGame(String menuString) {
        new Thread(() -> gameSelector.ChooseGame(menuString, consoleScanner), "GameLauncherThread").start();
    }
}
