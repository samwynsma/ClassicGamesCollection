import GamesList.MasterMindMaterials.MasterMindController;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.*;

public class MasterMindGUI extends JFrame {
    private MasterMindController controller;
    private final JPanel guessHistoryPanel;
    private final JTextArea historyArea;
    private final JTextField guessField;
    private final JLabel statusLabel;
    private final JLabel hintLabel;
    private final JButton submitButton;
    private final JButton resetButton;
    private final JButton quitButton;

    public MasterMindGUI() {
        super("Mastermind");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Mastermind", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel pegHistoryContainer = new JPanel(new BorderLayout(6, 6));
        pegHistoryContainer.setBorder(BorderFactory.createTitledBorder("Guess History"));
        guessHistoryPanel = new JPanel();
        guessHistoryPanel.setLayout(new BoxLayout(guessHistoryPanel, BoxLayout.Y_AXIS));
        JScrollPane pegScrollPane = new JScrollPane(guessHistoryPanel);
        pegScrollPane.setPreferredSize(new Dimension(520, 220));
        pegHistoryContainer.add(pegScrollPane, BorderLayout.CENTER);
        centerPanel.add(pegHistoryContainer);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        JScrollPane historyScrollPane = new JScrollPane(historyArea);
        historyScrollPane.setBorder(BorderFactory.createTitledBorder("Hints & Log"));
        centerPanel.add(historyScrollPane);

        add(centerPanel, BorderLayout.CENTER);

        JPanel controls = new JPanel(new BorderLayout(10, 10));
        controls.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        guessField = new JTextField(12);
        guessField.setToolTipText("Enter a four-digit guess like 0123");
        submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(e -> submitGuess());
        inputPanel.add(new JLabel("Guess:"));
        inputPanel.add(guessField);
        inputPanel.add(submitButton);
        controls.add(inputPanel, BorderLayout.NORTH);

        JPanel statusPanel = new JPanel(new GridLayout(2, 1, 4, 4));
        statusLabel = new JLabel("Ready to start.", SwingConstants.CENTER);
        hintLabel = new JLabel("Select a difficulty to begin.", SwingConstants.CENTER);
        statusPanel.add(statusLabel);
        statusPanel.add(hintLabel);
        controls.add(statusPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        resetButton = new JButton("New Game");
        quitButton = new JButton("Quit");
        resetButton.addActionListener(e -> startNewGame());
        quitButton.addActionListener(e -> dispose());
        actionPanel.add(resetButton);
        actionPanel.add(quitButton);
        controls.add(actionPanel, BorderLayout.SOUTH);

        add(controls, BorderLayout.SOUTH);

        pack();
        setSize(640, 560);
        setLocationRelativeTo(null);
        startNewGame();
    }

    private void startNewGame() {
        String difficulty = askDifficulty();
        if (difficulty == null) {
            dispose();
            return;
        }

        controller = new MasterMindController();
        controller.SetDifficulty(difficulty);
        controller.SetUpGame();

        guessHistoryPanel.removeAll();
        historyArea.setText("Welcome to Mastermind!\n");
        historyArea.append("Difficulty: " + difficulty + "\n");
        historyArea.append("Guess a four-digit code using digits 0 through " + getMaxDigitForDifficulty(difficulty) + ".\n");
        historyArea.append("Type 'quit' to end the game.\n\n");

        statusLabel.setText("Game started: " + difficulty);
        hintLabel.setText("Enter a 4-digit guess.");
        guessField.setText("");
        guessField.requestFocusInWindow();
        submitButton.setEnabled(true);
    }

    private String askDifficulty() {
        String[] options = {"easy", "medium", "hard", "brutal", "impossible"};
        return (String) JOptionPane.showInputDialog(
                this,
                "Select difficulty:",
                "Mastermind Difficulty",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );
    }

    private void submitGuess() {
        if (controller == null) {
            return;
        }

        String guess = guessField.getText().trim().toLowerCase();
        if (guess.isEmpty()) {
            return;
        }
        if (guess.equals("quit")) {
            historyArea.append("Game quit. The secret code was " + controller.GetCode() + ".\n");
            endGame("Game over. You quit.");
            return;
        }
        if (!isValidGuessFormat(guess)) {
            JOptionPane.showMessageDialog(this,
                    "Please enter exactly four digits.",
                    "Invalid guess",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maxDigit = getMaxDigitForDifficulty(controller);
        if (!isWithinRange(guess, maxDigit)) {
            JOptionPane.showMessageDialog(this,
                    "Each digit must be between 0 and " + maxDigit + ".",
                    "Invalid guess",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String output = captureOutput(() -> controller.CheckValidity(guess));
        if (!output.isBlank()) {
            historyArea.append(output);
        }

        boolean solved = controller.CheckIfSolved(guess);
        addGuessRow(guess);
        if (solved) {
            historyArea.append("Correct! You solved the code in " + getGuessesMade() + " guesses.\n");
            endGame("Congratulations! You solved the code.");
            return;
        }

        String hintOutput = captureOutput(() -> controller.GiveHints(guess));
        if (!hintOutput.isBlank()) {
            historyArea.append(hintOutput + "\n");
        } else {
            historyArea.append("Hint unavailable.\n");
        }
        guessField.setText("");
        guessField.requestFocusInWindow();
    }

    private boolean isValidGuessFormat(String guess) {
        if (guess.length() != 4) {
            return false;
        }
        for (char ch : guess.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWithinRange(String guess, int maxDigit) {
        for (char ch : guess.toCharArray()) {
            int value = ch - '0';
            if (value < 0 || value > maxDigit) {
                return false;
            }
        }
        return true;
    }

    private int getMaxDigitForDifficulty(MasterMindController controller) {
        return controller == null ? 5 : controller.getMaxElement();
    }

    private int getMaxDigitForDifficulty(String difficulty) {
        return switch (difficulty.toLowerCase()) {
            case "easy" -> 4;
            case "medium" -> 5;
            case "hard" -> 6;
            case "brutal" -> 7;
            case "impossible" -> 9;
            default -> 5;
        };
    }

    private void endGame(String message) {
        hintLabel.setText(message);
        submitButton.setEnabled(false);
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

    private void addGuessRow(String guess) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (char ch : guess.toCharArray()) {
            int peg = ch - '0';
            JPanel pegPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getPegColor(peg));
                    g2.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
                    g2.dispose();
                }
            };
            pegPanel.setPreferredSize(new Dimension(32, 32));
            pegPanel.setBackground(Color.WHITE);
            pegPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            row.add(pegPanel);
        }
        guessHistoryPanel.add(row);
        guessHistoryPanel.revalidate();
        guessHistoryPanel.repaint();
    }

    private Color getPegColor(int digit) {
        return switch (digit) {
            case 0 -> Color.GRAY;
            case 1 -> Color.BLUE;
            case 2 -> Color.RED;
            case 3 -> Color.GREEN;
            case 4 -> Color.ORANGE;
            case 5 -> new Color(128, 0, 128); // purple
            case 6 -> Color.CYAN;
            case 7 -> Color.MAGENTA;
            case 8 -> Color.PINK;
            case 9 -> Color.BLACK;
            default -> Color.LIGHT_GRAY;
        };
    }

    private int getGuessesMade() {
        return controller == null ? 0 : controller.getGuesses();
    }
}
