package com.example.server;

public class Game {

    private Player player1;
    private Player player2;
    private Player playerTurn;
    private int numberOfPlayers = 0;
    private Player winner;
    private String status;
    private char[][] board;

    public Game() {

    }

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

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Player getNextPlayer() {
        if (playerTurn == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    public boolean isMyTurn() {
        return playerTurn.getName().equals(player1.getName());
    }

    public String toString() {
        char[][] board = this.getBoard();
        String currentState = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                currentState += board[i][j];
                if (j == board[0].length-1) {
                    currentState += "\r\n";
                }
            }
        }
        if (getStatus().equals("WAITING FOR PLAYER 2 TO JOIN")) {
            currentState += "\r\n" + getStatus();
        }
        if (getStatus().equals("WAITING FOR PLAYER 1 TO MOVE")) {
            currentState += "\r\n" + getStatus() + ": " + getPlayer1().getName();
        }
        if (getStatus().equals("WAITING FOR PLAYER 2 TO MOVE")) {
            currentState += "\r\n" + getStatus() + ": " + getPlayer2().getName();
        }
        if (getStatus().equals("INVALID MOVE")) {
            currentState += "\r\n" + getStatus() + ": " + "\n" + "please enter col (1-9) or check-status";
        }
        if (getStatus().equals("INVALID INPUT")) {
            currentState += "\r\n" + getStatus() + ": " + "\n" + "please enter col (1-9) or check-status";
        }
        if (getStatus().equals("PLAYER 1 HAS LEFT THE GAME")) {
            return getStatus() + ": " + player1.getName();
        }
        if (getStatus().equals("PLAYER 2 HAS LEFT THE GAME")) {
            return getStatus() + ": " + player2.getName();
        }
        if (getStatus().equals("BOARD IS FULL")) {
            currentState += "\r\n" + getStatus();
        }
        if (getStatus().equals("WINNER")) {
            currentState += "\r\n" + getStatus() + ": " + getWinner().getName();
        }
        return currentState;
    }

}

