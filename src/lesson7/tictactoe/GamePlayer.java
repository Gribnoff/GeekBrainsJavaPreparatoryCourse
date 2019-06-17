package lesson7.tictactoe;

public class GamePlayer {
    private char playerSign;
    private boolean realPlayer = true;

    public GamePlayer(boolean realPlayer, char playerSign) {
        this.playerSign = playerSign;
        this.realPlayer = realPlayer;
    }

    public char getPlayerSign() {
        return playerSign;
    }

    public boolean isRealPlayer() {
        return realPlayer;
    }
}
