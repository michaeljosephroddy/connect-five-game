package com.example.client;

public class Game {

    private Player player1;
    private Player player2;
    private Player playerTurn;
    private Player nextPlayer;
    private int numberOfPlayers;
    private Player winner;
    private String status;
    private char[][] board;
    private boolean playerHasLeft;
    private boolean tooManyPlayers;
    private boolean isBoardFull;

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public boolean getPlayerHasLeft() {
        return playerHasLeft;
    }

    public void setPlayerHasLeft(boolean playerHasLeft) {
        this.playerHasLeft = playerHasLeft;
    }

    public boolean getTooManyPlayers() {
        return tooManyPlayers;
    }

    public void setTooManyPlayers(boolean tooManyPlayers) {
        this.tooManyPlayers = tooManyPlayers;
    }

    public boolean getIsBoardFull() { return isBoardFull; }

    public void setIsBoardFull(boolean boardFull) { isBoardFull = boardFull; }

    public String boardToString() {
        char[][] board = this.getBoard();
        String state = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                state += board[i][j];
                if (j == board[0].length-1) {
                    state += "\r\n";
                }
            }
        }
        return state;
    }

    @Override
    public String toString() {
        return "\r\n" + boardToString() + "\r\n" + getStatus();
    }
}
