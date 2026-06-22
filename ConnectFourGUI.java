import GamesList.ConnectFourMaterials.ConnectFourBoard;
import GamesList.ConnectFourMaterials.ConnectFourGameController;
import java.awt.*;
import javax.swing.*;

public class ConnectFourGUI extends JFrame {
    private ConnectFourBoard board;
    private ConnectFourGameController controller;
    private boolean isPlayerOne;
    private final JLabel statusLabel;
    private final JLabel[][] cellLabels;
    private final JButton[] columnButtons;

    public ConnectFourGUI() {
        super("Connect Four");
        board = new ConnectFourBoard();
        controller = new ConnectFourGameController();
        isPlayerOne = true;

        statusLabel = new JLabel("Player 1's turn (X)", SwingConstants.CENTER);
        cellLabels = new JLabel[6][7];
        columnButtons = new JButton[7];

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Connect Four", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        add(title, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(6, 7, 4, 4));
        boardPanel.setBackground(new Color(25, 25, 112));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                JLabel cell = new JLabel("", SwingConstants.CENTER);
                cell.setOpaque(true);
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setFont(cell.getFont().deriveFont(Font.BOLD, 20f));
                cellLabels[row][col] = cell;
                boardPanel.add(cell);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 16f));
        bottomPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 4, 4));
        for (int col = 0; col < 7; col++) {
            JButton dropButton = new JButton("Drop " + (col + 1));
            final int column = col;
            dropButton.addActionListener(e -> dropInColumn(column));
            columnButtons[col] = dropButton;
            buttonPanel.add(dropButton);
        }
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton resetButton = new JButton("Reset");
        JButton quitButton = new JButton("Quit");

        resetButton.addActionListener(e -> resetGame());
        quitButton.addActionListener(e -> dispose());

        actionPanel.add(resetButton);
        actionPanel.add(quitButton);
        bottomPanel.add(actionPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        updateBoardDisplay();
        setSize(760, 620);
        setLocationRelativeTo(null);
    }

    private void dropInColumn(int column) {
        int[] dropLocations = board.GetDropLocations();
        if (dropLocations[column] < 0) {
            JOptionPane.showMessageDialog(this,
                    "Column " + (column + 1) + " is full. Select a different column.",
                    "Column Full",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        isPlayerOne = board.DropIntoColumn(String.valueOf(column + 1), isPlayerOne);
        updateBoardDisplay();

        boolean finished = controller.CheckVictory(board);
        if (finished) {
            String winner = controller.getWinner();
            if ("Nobody".equals(winner)) {
                statusLabel.setText("Draw! No winner.");
                JOptionPane.showMessageDialog(this, "The board is full. It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else {
                statusLabel.setText(winner + " wins!");
                JOptionPane.showMessageDialog(this, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
            setColumnButtonsEnabled(false);
            return;
        }

        statusLabel.setText(isPlayerOne ? "Player 1's turn (X)" : "Player 2's turn (O)");
    }

    private void updateBoardDisplay() {
        char[][] columns = board.GetColumns();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                JLabel cell = cellLabels[row][col];
                char token = columns[col][row];
                if (token == 'X') {
                    cell.setText("X");
                    cell.setBackground(Color.RED);
                    cell.setForeground(Color.WHITE);
                } else if (token == 'O') {
                    cell.setText("O");
                    cell.setBackground(Color.YELLOW);
                    cell.setForeground(Color.BLACK);
                } else {
                    cell.setText("");
                    cell.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void setColumnButtonsEnabled(boolean enabled) {
        for (JButton button : columnButtons) {
            button.setEnabled(enabled);
        }
    }

    private void resetGame() {
        board = new ConnectFourBoard();
        controller = new ConnectFourGameController();
        isPlayerOne = true;
        statusLabel.setText("Player 1's turn (X)");
        setColumnButtonsEnabled(true);
        updateBoardDisplay();
    }
}
