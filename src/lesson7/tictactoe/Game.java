package lesson7.tictactoe;

import javax.swing.*;

class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playersTurn;
    private boolean endGame = false;
    private boolean difficulty = false; //сложность: false - простой ИИ, true - с расчётом веса клеток

    Game() {
        board = new GameBoard(this);
        initGame();
    }

    void initGame() {
        gamePlayers[0] = new GamePlayer('X');
        gamePlayers[1] = new GamePlayer('O');
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

//    int getPlayersTurn() {
//        return playersTurn;
//    }

    boolean isEndGame() {
        return endGame;
    }

    void setEndGame() {
        this.endGame = true;
    }

    boolean isDifficulty() {
        return difficulty;
    }

    void setDifficulty(boolean difficulty) {
        this.difficulty = difficulty;
    }
}
