import GamesList.RiskyDiceMaterials.RiskyDiceGameController;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.*;

public class RiskyDiceGUI extends JFrame {
    private RiskyDiceGameController controller;
    private final JTextArea logArea;
    private final JLabel playerLabel;
    private final JLabel dieLabel;
    private final JLabel rollsLabel;
    private final JLabel scoreLabel;
    private final JLabel advanceLabel;
    private final JButton rollButton;
    private final JButton stopButton;
    private final JButton advanceButton;
    private final JButton scoreButton;
    private final JButton helpButton;
    private final JButton quitButton;

    public RiskyDiceGUI() {
        super("Risky Dice");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        playerLabel = new JLabel("Player: ");
        dieLabel = new JLabel("Current die: ");
        rollsLabel = new JLabel("Rolls this die: ");
        scoreLabel = new JLabel("Current score: ");
        advanceLabel = new JLabel("Advance available: ");

        JPanel statusPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        statusPanel.add(playerLabel);
        statusPanel.add(dieLabel);
        statusPanel.add(rollsLabel);
        statusPanel.add(scoreLabel);
        statusPanel.add(advanceLabel);
        add(statusPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(520, 260));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        rollButton = new JButton("Roll");
        stopButton = new JButton("Stop");
        advanceButton = new JButton("Advance");
        scoreButton = new JButton("Score");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");

        rollButton.addActionListener(e -> executeCommand("roll"));
        stopButton.addActionListener(e -> executeCommand("stop"));
        advanceButton.addActionListener(e -> executeCommand("advance"));
        scoreButton.addActionListener(e -> executeCommand("score"));
        helpButton.addActionListener(e -> executeCommand("help"));
        quitButton.addActionListener(e -> quitGame());

        buttonPanel.add(rollButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(advanceButton);
        buttonPanel.add(scoreButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        int players = askPlayersCount();
        if (players < 0) {
            dispose();
            return;
        }

        controller = new RiskyDiceGameController(players);
        appendLog("Welcome to Risky Dice!\nThis is a GUI copy of the game. Use the buttons to play.\n\n");
        updateStatus();

        pack();
        setMinimumSize(new Dimension(560, 560));
        setLocationRelativeTo(null);
    }

    private int askPlayersCount() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                this,
                "How many players will be playing Risky Dice? (2-10)",
                "Players",
                JOptionPane.QUESTION_MESSAGE
            );
            if (input == null) {
                return -1;
            }
            try {
                int players = Integer.parseInt(input.trim());
                if (players >= 2 && players <= 10) {
                    return players;
                }
            } catch (NumberFormatException ignored) {
            }
            JOptionPane.showMessageDialog(this, "Please enter a number between 2 and 10.", "Invalid input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void executeCommand(String command) {
        if (controller == null || controller.IsGameOver()) {
            return;
        }
        String output = captureOutput(() -> controller.ParseInput(command));
        appendLog(output);
        if (controller.IsGameOver()) {
            appendLog("\nGame over! " + controller.DetermineWinner() + " is/are the winner(s).\n");
            appendLog(captureOutput(controller::DisplayLeaderboard));
            setButtonsEnabled(false);
        }
        updateStatus();
    }

    private void quitGame() {
        if (controller == null) {
            dispose();
            return;
        }
        String output = captureOutput(() -> controller.ParseInput("quit"));
        appendLog(output);
        appendLog("\nGame quit by user.\n");
        appendLog(captureOutput(controller::DisplayLeaderboard));
        setButtonsEnabled(false);
        updateStatus();
    }

    private String captureOutput(Runnable action) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(baos);
        System.setOut(capture);
        try {
            action.run();
        } finally {
            System.out.flush();
            System.setOut(oldOut);
        }
        return baos.toString();
    }

    private void appendLog(String text) {
        logArea.append(text);
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void updateStatus() {
        if (controller == null) {
            playerLabel.setText("Player: -");
            dieLabel.setText("Current die: -");
            rollsLabel.setText("Rolls this die: -");
            scoreLabel.setText("Current score: -");
            advanceLabel.setText("Advance available: -");
            return;
        }

        if (controller.IsGameOver()) {
            playerLabel.setText("Game over");
            dieLabel.setText("Current die: -");
            rollsLabel.setText("Rolls this die: -");
            scoreLabel.setText("Final score available in log");
            advanceLabel.setText("Advance available: -");
            setButtonsEnabled(false);
            return;
        }

        playerLabel.setText("Player: " + controller.GetCurrentPlayer() + " / " + controller.GetPlayers());
        dieLabel.setText("Current die: " + controller.GetCurrentDie());
        rollsLabel.setText("Rolls this die: " + controller.GetCurrentRolls());
        scoreLabel.setText("Current score: " + controller.GetCurrentScore());
        advanceLabel.setText("Advance available: " + (controller.CanAdvance() ? "Yes" : "No"));
    }

    private void setButtonsEnabled(boolean enabled) {
        rollButton.setEnabled(enabled);
        stopButton.setEnabled(enabled);
        advanceButton.setEnabled(enabled);
        scoreButton.setEnabled(enabled);
        helpButton.setEnabled(enabled);
    }
}
