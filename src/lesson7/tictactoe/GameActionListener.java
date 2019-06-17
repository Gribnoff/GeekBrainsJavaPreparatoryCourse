package lesson7.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int coll;
    private GameButton button;

    public GameActionListener(int row, int coll, GameButton button) {
        this.row = row;
        this.coll = coll;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isTurnable(row, coll)) {
            updateByPlayerData(board);
            if (board.isFull()) {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            } else {
                updateByAIData(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    private void updateByPlayerData(GameBoard board) {
        board.updateGameField(row, coll);
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли");
            board.emptyField();
        } else {
            board.getGame().passTurn();
        }
    }

    private void updateByAIData(GameBoard board) {
        int x, y;
        Random random = new Random();

        do {
            x = random.nextInt(GameBoard.dimension);
            y = random.nextInt(GameBoard.dimension);
        } while (!board.isTurnable(x, y));

        board.updateGameField(x, y);

        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Компьтер выиграл!");
            board.emptyField();
        } else
            board.getGame().passTurn();
    }
}
