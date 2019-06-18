package lesson7.tictactoe;

import javax.swing.*;

class GameButton extends JButton {
    private GameBoard board;
//    private int buttonIndex;

    GameButton(int buttonIndex, GameBoard board) {
//        this.buttonIndex = buttonIndex;
        this.board = board;

        int rowNum = buttonIndex / GameBoard.dimension;
        int collNum = buttonIndex % GameBoard.dimension;

        setSize(GameBoard.cellSize - 5, GameBoard.cellSize - 5);
        addActionListener(new GameActionListener(rowNum, collNum, this));
    }

    GameBoard getBoard() {
        return board;
    }
}
