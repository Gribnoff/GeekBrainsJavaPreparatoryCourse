package lesson7.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int coll;
    private GameButton button;

    GameActionListener(int row, int coll, GameButton button) {
        this.row = row;
        this.coll = coll;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isValid(row, coll)) {
            updateByPlayerData(board);
            if (board.isFull()) board.getGame().showMessage("Ничья");
            else if (!button.getBoard().getGame().isEndGame())
                updateByAIData(board);
        } else
            board.getGame().showMessage("Некорректный ход!");
    }

    private void updateByPlayerData(GameBoard board) {
        board.updateGameField(row, coll);
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли");
            button.getBoard().getGame().setEndGame();
        } else {
            board.getGame().passTurn();
        }
    }

    private void updateByAIData(GameBoard board) {
        int x = -1;
        int y = -1;
        Random random = new Random();

        if (!board.getGame().isDifficulty()) {
            do {
                x = random.nextInt(GameBoard.dimension);
                y = random.nextInt(GameBoard.dimension);
            } while (!board.isValid(x, y));
        } else {




            int[][] cellRatingMap = createCellRatingMap(board);
            int bestMoveRating = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.dimension; i++) {
                for (int j = 0; j < GameBoard.dimension; j++) {
                    if (bestMoveRating < cellRatingMap[i][j]) {
                        bestMoveRating = cellRatingMap[i][j];
                        x = j;
                        y = i;
                    }
                }
            }






        }
        board.updateGameField(x, y);

        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin())
            button.getBoard().getGame().showMessage("Компьтер выиграл!");
        else
            board.getGame().passTurn();
    }


    /**
     * Создаёт карту с ценностью клеток для выбора хода компьютера
     * @return int[][] карту приоритетности ходов
     */
    private static int[][] createCellRatingMap(GameBoard board) {
        int[][] result = new int[GameBoard.dimension][GameBoard.dimension];
        for (int i = 0; i < GameBoard.dimension; i++) {
            for (int j = 0; j < GameBoard.dimension; j++) {
                result[i][j] = countCellRating(board, i, j);
            }
        }

        return result;
    }

    /**
     * Подсчёт ценности клетки
     * @param x - координата по горизонтали
     * @param y - координата по вертикали
     * @return int вероятность хода компьютера на эту клетку
     */
    private static int countCellRating(GameBoard board, int x, int y) {
        //noinspection SuspiciousNameCombination
        if (!board.isValid(y, x))
            return Integer.MIN_VALUE;

        int result = 0;

        for (int i = -1; i <= 1; i++) {
            if (x + i >= 0 && x + i < GameBoard.dimension && y + i >= 0 && y + i < GameBoard.dimension) {
                if (board.getGameField()[y + i][x + i] == board.getGame().getCurrentPlayer().getPlayerSign())
                    result++;
            }
            if (x + i >= 0 && x + i < GameBoard.dimension) {
                if (board.getGameField()[y][x + i] == board.getGame().getCurrentPlayer().getPlayerSign())
                    result++;
            }
            if (y + i >= 0 && y + i < GameBoard.dimension) {
                if (board.getGameField()[y + i][x] == board.getGame().getCurrentPlayer().getPlayerSign())
                    result++;
            }
        }
        return result;
    }
}
