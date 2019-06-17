package lesson7.tictactoe;

import javax.swing.*;

class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playersTurn = 0;
    private boolean endGame = false;

    Game() {
        board = new GameBoard(this);
        initGame();
    }

    void initGame() {
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, 'O');
        playersTurn = 0;
        endGame = false;
    }

    void passTurn() {
        if (playersTurn == 0)
            playersTurn = 1;
        else
            playersTurn = 0;
    }

    GamePlayer getCurrentPlayer(){
        return gamePlayers[playersTurn];
    }

    void showMessage(String message) {
        JOptionPane.showMessageDialog(board, message);
    }

    int getPlayersTurn() {
        return playersTurn;
    }

    boolean isEndGame() {
        return endGame;
    }

    void setEndGame() {
        this.endGame = true;
    }
}
