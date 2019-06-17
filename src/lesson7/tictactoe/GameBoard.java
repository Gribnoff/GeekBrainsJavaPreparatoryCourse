package lesson7.tictactoe;

import javax.swing.*;
import java.awt.*;

class GameBoard extends JFrame {
    static int dimension = 3;
    static int winLine = 3;
    static int cellSize = 125;
    private char[][] gameField;
    private GameButton[] gameButtons;

    private Game game;

    private static final char nullSymbol = '\u0000';

    GameBoard(Game game){
        this.game = game;
        initField();
    }

    private void initField() {
        getContentPane().removeAll();
        setResizable(false);
        setBounds(400, 300, cellSize * dimension, cellSize * dimension);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(e -> restartField());

        JButton optionsButton = new JButton("Размер поля");
        JPanel optionsFields = new JPanel(new GridLayout(2, 2));
        JLabel dimensionLabel = new JLabel("Размер поля");
        JComboBox<Integer> dimensionComboBox = new JComboBox<>(new Integer[]{3, 4, 5, 6, 7, 8, 9, 10});
        dimensionComboBox.setSelectedItem(dimension);
        JLabel winLineLabel = new JLabel("Победная линия");
        JComboBox<Integer> winLineComboBox = new JComboBox<>(new Integer[]{3, 4, 5, 6, 7});
        winLineComboBox.setSelectedItem(winLine);

        optionsFields.add(dimensionLabel);
        optionsFields.add(dimensionComboBox);
        optionsFields.add(winLineLabel);
        optionsFields.add(winLineComboBox);

        optionsButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, optionsFields, "Размер поля", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                GameBoard.dimension = (int) dimensionComboBox.getSelectedItem();
                GameBoard.winLine = (int) winLineComboBox.getSelectedItem();
                initField();
                getGame().initGame();
            }
        });

        JButton cellSizeButton = new JButton("Размер клеток");
        JPanel cellSizeFields = new JPanel(new GridLayout(1, 2));
        JLabel cellSizeLabel = new JLabel("Размер поля");
        JComboBox<Integer> cellSizeComboBox = new JComboBox<>(new Integer[]{50, 75, 100, 125, 150, 200});
        cellSizeComboBox.setSelectedItem(cellSize);

        cellSizeFields.add(cellSizeLabel);
        cellSizeFields.add(cellSizeComboBox);

        cellSizeButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, cellSizeFields, "размер клеток", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                GameBoard.cellSize = (int) cellSizeComboBox.getSelectedItem();
                initField();
                getGame().initGame();
            }
        });

        controlPanel.setLayout(new GridLayout(1, 2));
        controlPanel.add(newGameButton);
        controlPanel.add(optionsButton);
        controlPanel.add(cellSizeButton);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            fieldButton.setEnabled(true);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    void restartField() {
        getContentPane().removeAll();
        getGame().initGame();
        initField();
        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }
    }

    Game getGame() {
        return game;
    }

    boolean isValid(int x, int y) {
        boolean result = false;

        if (gameField[y][x] == nullSymbol)
            result = true;

        return result;
    }

    void updateGameField(int x, int y) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
    }

    boolean checkWin() {
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        for (int i = 0; i < dimension - winLine + 1; i++) {
            for (int j = 0; j < dimension - winLine + 1; j++) {
                if (checkDiagonals(playerSymbol, j, i) || checkLanes(playerSymbol, j, i)) {
                    disableGameButtons();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Проверка столбцов и строк на победу
     * @param playerSymbol - текущий игрок
     * @param offsetX - сдвиг по горизонтали
     * @param offsetY - сдвиг по вертикали
     * @return boolean есть ли строка или столбец, полностью заполненная игроком
     */
    boolean checkLanes(char playerSymbol, int offsetX, int offsetY){
        boolean rows, cols;
        for (int i = offsetY; i < winLine + offsetY; i++) {
            rows = true;
            cols = true;
            for (int j = offsetX; j < winLine + offsetX; j++) {
                cols &= (gameField[i][j] == playerSymbol);
                rows &= (gameField[j][i] == playerSymbol);
            }
            if (cols || rows)
                return true;
        }
        return false;
    }
    /**
     * Проверка диагоналей на победу
     * @param playerSymbol - текущий игрок
     * @param offsetX - сдвиг по горизонтали
     * @param offsetY - сдвиг по вертикали
     * @return boolean есть ли строка или столбец, полностью заполненная игроком
     */
    boolean checkDiagonals(char playerSymbol, int offsetX, int offsetY){
        boolean right, left;
        right = true;
        left = true;
        for (int i = 0; i < winLine; i++) {
            left &= (gameField[i + offsetY][i + offsetX] == playerSymbol);
            right &= (gameField[winLine - i - 1 + offsetY][i + offsetX] == playerSymbol);
        }
        return left || right;
    }

    boolean isFull() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (gameField[i][j] == nullSymbol)
                    return false;
            }
        }
        return true;
    }

    GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }

    private void disableGameButtons() {
        for (GameButton gameButton : gameButtons) {
            gameButton.setEnabled(false);
        }
    }
}
