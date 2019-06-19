package lesson7.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class GameBoard extends JFrame {
    static int dimension = 3;
    private static int winLine = 3;
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
        setBounds(0, 0, cellSize * dimension, cellSize * dimension);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(e -> restartField());

        JButton optionsButton = new JButton("Опции");
        JPanel optionsFields = new JPanel(new GridLayout(3, 2));

        JLabel dimensionLabel = new JLabel("Размер поля");
        JComboBox<Integer> dimensionComboBox = new JComboBox<>(new Integer[]{3, 4, 5, 6, 7, 8, 9, 10});
        dimensionComboBox.setSelectedItem(dimension);

        JLabel winLineLabel = new JLabel("Победная линия");
        JComboBox<Integer> winLineComboBox = new JComboBox<>(new Integer[]{3});
        winLineComboBox.setSelectedItem(winLine);

        dimensionComboBox.addActionListener(e -> {
            ArrayList<Integer> winLines = new ArrayList<>();
            int maxWinLine = (5 < (int)dimensionComboBox.getSelectedItem()) ? 5 : (int)dimensionComboBox.getSelectedItem();
            for (int i = 3; i < maxWinLine + 1; i++) {
                winLines.add(i);
            }
            winLineComboBox.setModel(new DefaultComboBoxModel(winLines.toArray()));
        });

        JLabel cellSizeLabel = new JLabel("Размер клеток");
        JSlider cellSizeSlider = new JSlider(50, 200, 100);
        cellSizeSlider.setMajorTickSpacing(25);
        cellSizeSlider.setValue(cellSize);
        cellSizeSlider.setPaintLabels(true);

        optionsFields.add(dimensionLabel);
        optionsFields.add(dimensionComboBox);
        optionsFields.add(winLineLabel);
        optionsFields.add(winLineComboBox);
        optionsFields.add(cellSizeLabel);
        optionsFields.add(cellSizeSlider);

        JLabel sizeLabel = new JLabel(((Integer)cellSize).toString());
        optionsButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, optionsFields, "Настройки игры", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                GameBoard.dimension = (int) dimensionComboBox.getSelectedItem();
                GameBoard.winLine = (int) winLineComboBox.getSelectedItem();

                GameBoard.cellSize = cellSizeSlider.getValue();
                sizeLabel.setText(((Integer)cellSize).toString());

                initField();
                getGame().initGame();
            }
        });

        JCheckBox difficultyCheckBox = new JCheckBox("Hard mode");
        difficultyCheckBox.addItemListener(e -> getGame().setDifficulty(difficultyCheckBox.isSelected()));

        controlPanel.setLayout(new GridLayout(1, 3));
        controlPanel.add(newGameButton);
        controlPanel.add(optionsButton);
        controlPanel.add(difficultyCheckBox);
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

    private void restartField() {
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
    private boolean checkLanes(char playerSymbol, int offsetX, int offsetY){
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
    private boolean checkDiagonals(char playerSymbol, int offsetX, int offsetY){
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

    char[][] getGameField() {
        return gameField;
    }
}
