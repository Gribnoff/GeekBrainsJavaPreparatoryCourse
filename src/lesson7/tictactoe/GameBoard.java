package lesson7.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    static int dimension = 3;
    static int winLine = 3;
    static int cellSize = 150;
    private char[][] gameField;
    private GameButton[] gameButtons;

    private Game game;

    static char nullSymbol = '\u0000';

    public GameBoard(Game game){
        this.game = game;
        initField();
    }

    private void initField() {
        setBounds(cellSize * dimension, cellSize * dimension, 400, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    void emptyField() {
        getGame().initGame();
        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }
    }

    public Game getGame() {
        return game;
    }

    boolean isTurnable(int x, int y) {
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
                if (checkDiagonals(playerSymbol, j, i) || checkLanes(playerSymbol, j, i))
                    return true;
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

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }
}
