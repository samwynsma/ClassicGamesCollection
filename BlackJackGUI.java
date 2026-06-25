import GamesList.BlackJackMaterials.BlackJackGameController;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;
import javax.swing.*;

public class BlackJackGUI extends JFrame {
    private BlackJackGameController controller;
    private JLabel dealerHandLabel;
    private JLabel dealerScoreLabel;
    private JLabel playerHandLabel;
    private JLabel playerScoreLabel;
    private JLabel statusLabel;
    private JLabel recordLabel;
    private JTextArea logArea;
    private JButton drawButton;
    private JButton standButton;
    private JButton newGameButton;
    private JButton quitButton;
    private int wins;
    private int losses;

    public BlackJackGUI() {
        super("Blackjack");
        initComponents();
        startNewGame();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Blackjack", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        recordLabel = new JLabel("Wins: 0  Losses: 0", SwingConstants.CENTER);
        recordLabel.setFont(recordLabel.getFont().deriveFont(Font.PLAIN, 14f));
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(recordLabel, BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        mainPanel.add(createHandPanel("Dealer"), BorderLayout.NORTH);
        mainPanel.add(createHandPanel("Player"), BorderLayout.CENTER);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("Game Log"));
        logScrollPane.setPreferredSize(new Dimension(520, 180));
        mainPanel.add(logScrollPane, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Ready to play.", SwingConstants.CENTER);
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 14f));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        drawButton = new JButton("Draw");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");
        quitButton = new JButton("Quit");

        drawButton.addActionListener(e -> drawCard());
        standButton.addActionListener(e -> stand());
        newGameButton.addActionListener(e -> startNewGame());
        quitButton.addActionListener(e -> dispose());

        buttonPanel.add(drawButton);
        buttonPanel.add(standButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(quitButton);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(640, 560);
        setLocationRelativeTo(null);
    }

    private JPanel createHandPanel(String title) {
        JPanel handPanel = new JPanel(new BorderLayout(6, 6));
        handPanel.setBorder(BorderFactory.createTitledBorder(title + " Hand"));

        JPanel header = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title + ":");
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        header.add(label, BorderLayout.WEST);

        JLabel scoreLabel = new JLabel("", SwingConstants.RIGHT);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.PLAIN, 14f));
        if (title.equals("Dealer")) {
            dealerScoreLabel = scoreLabel;
        } else {
            playerScoreLabel = scoreLabel;
        }
        header.add(scoreLabel, BorderLayout.EAST);
        handPanel.add(header, BorderLayout.NORTH);

        JLabel handLabel = new JLabel("", SwingConstants.CENTER);
        handLabel.setOpaque(true);
        handLabel.setBackground(Color.WHITE);
        handLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        handLabel.setFont(handLabel.getFont().deriveFont(Font.PLAIN, 16f));

        if (title.equals("Dealer")) {
            dealerHandLabel = handLabel;
        } else {
            playerHandLabel = handLabel;
        }

        handPanel.add(handLabel, BorderLayout.CENTER);
        return handPanel;
    }

    private void startNewGame() {
        controller = new BlackJackGameController();
        drawButton.setEnabled(true);
        standButton.setEnabled(true);
        statusLabel.setText("Draw or Stand to play.");
        logArea.setText("New round started.\n");
        updateRecordLabel();
        updateView();
    }

    private void drawCard() {
        if (controller == null || controller.isGameOver) {
            return;
        }

        controller.PlayerDraw();
        appendLog("You drew a card.");

        if (controller.player.hasBusted()) {
            appendLog("You busted.");
            updateView();
            endRound();
            return;
        }

        if (controller.dealer.GetCardValue() < 17) {
            controller.DealerDraw();
            appendLog("Dealer draws a card.");
            if (controller.dealer.hasBusted()) {
                appendLog("Dealer busted.");
            }
        } else {
            controller.DealerQuits();
            appendLog("Dealer stands.");
        }

        updateView();
        if (controller.isGameOver) {
            endRound();
        } else {
            statusLabel.setText("Your turn: draw or stand.");
        }
    }

    private void stand() {
        if (controller == null || controller.isGameOver) {
            return;
        }

        controller.PlayerQuits();
        appendLog("You stand.");

        while (controller.dealer.GetCardValue() < 17 && !controller.dealer.hasBusted()) {
            controller.DealerDraw();
            appendLog("Dealer draws a card.");
        }
        controller.DealerQuits();

        updateView();
        endRound();
    }

    private void endRound() {
        drawButton.setEnabled(false);
        standButton.setEnabled(false);

        ResultOutput resultOutput = captureOutputWithResult(controller::DetermineWinner);
        appendLog(resultOutput.output.trim());

        if (resultOutput.result == 1) {
            wins++;
            statusLabel.setText("You win! Click New Game to play again.");
        } else if (resultOutput.result == 2) {
            losses++;
            statusLabel.setText("Dealer wins. Click New Game to play again.");
        } else {
            statusLabel.setText("Tie game. Click New Game to play again.");
        }

        updateRecordLabel();
        updateView();
    }

    private void updateView() {
        if (controller == null) {
            return;
        }

        if (controller.isGameOver) {
            dealerHandLabel.setText(controller.dealer.DisplayFullHand());
            dealerScoreLabel.setText("Dealer: " + controller.dealer.GetCardValue());
        } else {
            dealerHandLabel.setText(controller.dealer.DisplayVisibleHand());
            dealerScoreLabel.setText("Dealer visible: " + controller.dealer.GetVisibleCardValue());
        }

        playerHandLabel.setText(controller.player.DisplayFullHand());
        playerScoreLabel.setText("Player: " + controller.player.GetCardValue());
    }

    private void appendLog(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void updateRecordLabel() {
        recordLabel.setText("Wins: " + wins + "   Losses: " + losses);
    }

    private ResultOutput captureOutputWithResult(Supplier<Integer> action) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(baos);
        System.setOut(capture);
        try {
            int result = action.get();
            capture.flush();
            return new ResultOutput(result, baos.toString());
        } finally {
            System.out.flush();
            System.setOut(oldOut);
        }
    }

    private static class ResultOutput {
        final int result;
        final String output;

        ResultOutput(int result, String output) {
            this.result = result;
            this.output = output;
        }
    }
}
