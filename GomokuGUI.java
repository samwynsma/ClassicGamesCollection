import GamesList.GomokuMaterials.GomokuBoard;
import GamesList.GomokuMaterials.GomokuGameController;
import java.awt.*;
import javax.swing.*;

public class GomokuGUI extends JFrame {
    private static final int BOARD_SIZE = 15;

    private GomokuBoard board;
    private GomokuGameController controller;
    private boolean isPlayerOne;
    private int winCondition;
    private final JButton[][] cellButtons;
    private final JLabel statusLabel;

    public GomokuGUI() {
        super("Gomoku");
        cellButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
        statusLabel = new JLabel("Choose a win length to begin.", SwingConstants.CENTER);
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Gomoku", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        add(title, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE, 2, 2));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boardPanel.setBackground(Color.DARK_GRAY);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton cell = new JButton();
                cell.setFocusPainted(false);
                cell.setFont(cell.getFont().deriveFont(Font.BOLD, 12f));
                cell.setPreferredSize(new Dimension(34, 34));
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                final int finalRow = row;
                final int finalCol = col;
                cell.addActionListener(e -> handleMove(finalRow, finalCol));
                cellButtons[row][col] = cell;
                boardPanel.add(cell);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JPanel controls = new JPanel(new BorderLayout(10, 10));
        controls.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 16f));
        controls.add(statusLabel, BorderLayout.NORTH);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton newGameButton = new JButton("New Game");
        JButton quitButton = new JButton("Quit");
        newGameButton.addActionListener(e -> startNewGame());
        quitButton.addActionListener(e -> dispose());
        actionPanel.add(newGameButton);
        actionPanel.add(quitButton);
        controls.add(actionPanel, BorderLayout.CENTER);

        add(controls, BorderLayout.SOUTH);

        setSize(820, 900);
        setLocationRelativeTo(null);
        startNewGame();
    }

    private void startNewGame() {
        Integer chosenWinCondition = askWinCondition();
        if (chosenWinCondition == null) {
            dispose();
            return;
        }

        winCondition = chosenWinCondition;
        board = new GomokuBoard();
        controller = new GomokuGameController();
        controller.SetWinCondition(winCondition);
        isPlayerOne = true;
        setBoardEnabled(true);
        updateBoardDisplay();
        statusLabel.setText("Player 1's turn (X) — first to " + winCondition + " in a row");
    }

    private Integer askWinCondition() {
        String[] options = {"3", "4", "5", "6", "7"};
        String selected = (String) JOptionPane.showInputDialog(
                this,
                "How many stones in a row should win?",
                "Gomoku Setup",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "5"
        );
        if (selected == null) {
            return null;
        }
        try {
            int value = Integer.parseInt(selected);
            if (value >= 3 && value <= 7) {
                return value;
            }
        } catch (NumberFormatException ignored) {
        }
        return 5;
    }

    private void handleMove(int row, int col) {
        if (board == null || controller == null) {
            return;
        }

        char[][] boardState = board.GetBoard();
        if (boardState[row][col] != '.') {
            JOptionPane.showMessageDialog(this, "That spot is already occupied.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean previousPlayer = isPlayerOne;
        boolean nextPlayer = board.PlaceTile(String.valueOf(row + 1), String.valueOf(col + 1), isPlayerOne);
        if (nextPlayer == previousPlayer) {
            return;
        }

        isPlayerOne = nextPlayer;
        updateBoardDisplay();

        boolean gameOver = controller.CheckVictory(board);
        if (gameOver) {
            String winner = controller.getWinner();
            if ("Nobody".equals(winner)) {
                statusLabel.setText("Draw! No winner.");
                JOptionPane.showMessageDialog(this, "The board is full. It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else {
                statusLabel.setText(winner + " wins!");
                JOptionPane.showMessageDialog(this, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
            setBoardEnabled(false);
        } else {
            statusLabel.setText(isPlayerOne ? "Player 1's turn (X)" : "Player 2's turn (O)");
        }
    }

    private void updateBoardDisplay() {
        char[][] boardState = board.GetBoard();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton cell = cellButtons[row][col];
                char token = boardState[row][col];
                if (token == 'X') {
                    cell.setText("X");
                    cell.setForeground(Color.RED);
                    cell.setBackground(new Color(240, 240, 240));
                } else if (token == 'O') {
                    cell.setText("O");
                    cell.setForeground(Color.BLUE);
                    cell.setBackground(new Color(240, 240, 240));
                } else {
                    cell.setText("");
                    cell.setForeground(Color.BLACK);
                    cell.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void setBoardEnabled(boolean enabled) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cellButtons[row][col].setEnabled(enabled);
            }
        }
    }
}
