import java.awt.*;
import javax.swing.*;

public class MainMenuGUI extends JFrame {
    private final GameSelectorGUI selectorGUI;

    public MainMenuGUI() {
        super("Classic Games Collection");
        selectorGUI = new GameSelectorGUI();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Classic Games Collection", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        String[] buttons = {
            "1. Connect 4",
            "2. Mastermind",
            "3. Blackjack",
            "4. Yacht",
            "5. Risky Dice",
            "6. Farkle",
            "7. Gomoku",
            "Quit"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFocusPainted(false);
            button.addActionListener(e -> handleButton(text));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        JLabel footer = new JLabel(
            "<html><center>Click a button to start the game in the console.<br>Use the terminal to enter game input.</center></html>",
            SwingConstants.CENTER
        );
        add(footer, BorderLayout.SOUTH);

        setSize(520, 320);
        setLocationRelativeTo(null);
    }

    private void handleButton(String label) {
        if (label.equals("Quit")) {
            int choice = JOptionPane.showConfirmDialog(this, "Exit the launcher?", "Quit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            return;
        }

        String menuString = label.substring(0, 1);
        if (menuString.equals("1")) {
            ConnectFourGUI connectFourWindow = new ConnectFourGUI();
            connectFourWindow.setVisible(true);
            return;
        }
        if (menuString.equals("2")) {
            MasterMindGUI masterMindWindow = new MasterMindGUI();
            masterMindWindow.setVisible(true);
            return;
        }
        if (menuString.equals("3")) {
            BlackJackGUI blackjackWindow = new BlackJackGUI();
            blackjackWindow.setVisible(true);
            return;
        }
        if (menuString.equals("7")) {
            GomokuGUI gomokuWindow = new GomokuGUI();
            gomokuWindow.setVisible(true);
            return;
        }
        if (menuString.equals("5")) {
            RiskyDiceGUI riskyDiceWindow = new RiskyDiceGUI();
            riskyDiceWindow.setVisible(true);
            return;
        }

        String gameName = label.substring(3);
        JOptionPane.showMessageDialog(
            this,
            "Launching " + gameName + " in the console.\n" +
            "After the game ends, return to this window to launch another game.",
            "Launch Game",
            JOptionPane.INFORMATION_MESSAGE
        );

        selectorGUI.chooseGame(menuString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuGUI().setVisible(true));
    }
}
