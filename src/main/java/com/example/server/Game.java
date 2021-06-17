package com.example.server;

public class Game {

    private static final int HEIGHT = 6;
    private static final int WIDTH = 19;
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

    public Game() {
        player1 = new Player();
        player2 = new Player();
        playerTurn = new Player();
        nextPlayer = new Player();
        numberOfPlayers = 0;
        winner = new Player();
        status = "";
        board = createBoard(HEIGHT, WIDTH);
        playerHasLeft = false;
        tooManyPlayers = false;
        isBoardFull = false;
    }

    public Game(String message) {
        this.status = message;
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

    public boolean getIsBoardFull() {
        return isBoardFull;
    }

    public void setIsBoardFull(boolean boardFull) {
        isBoardFull = boardFull;
    }

    public Player getNextPlayer() {
        if (playerTurn == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    /**
     * creates the game board.
     * @return a 2d char array as the game board.
     */
    public char[][] createBoard(int HEIGHT, int WIDTH) {
        char[][] board = new char[HEIGHT][WIDTH];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (col % 2 == 0) {
                    board[row][col] = '|';
                } else {
                    board[row][col] = '_';
                }
            }
        }
        return board;
    }

//    public String toString() {
//        char[][] board = this.getBoard();
//        String currentState = "";
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                currentState += board[i][j];
//                if (j == board[0].length-1) {
//                    currentState += "\r\n";
//                }
//            }
//        }
//        if (getStatus().equals("WAITING FOR PLAYER 2 TO JOIN")) {
//            currentState += "\r\n" + getStatus();
//        }
//        if (getStatus().equals("WAITING FOR PLAYER 1 TO MOVE")) {
//            currentState += "\r\n" + getStatus() + ": " + getPlayer1().getName();
//        }
//        if (getStatus().equals("WAITING FOR PLAYER 2 TO MOVE")) {
//            currentState += "\r\n" + getStatus() + ": " + getPlayer2().getName();
//        }
//        if (getStatus().equals("INVALID MOVE")) {
//            currentState += "\r\n" + getStatus() + ": " + "\n" + "please enter col (1-9) or check-status";
//        }
//        if (getStatus().equals("INVALID INPUT")) {
//            currentState += "\r\n" + getStatus() + ": " + "\n" + "please enter col (1-9) or check-status";
//        }
//        if (getStatus().equals("PLAYER 1 HAS LEFT THE GAME")) {
//            return getStatus();
//        }
//        if (getStatus().equals("PLAYER 2 HAS LEFT THE GAME")) {
//            return getStatus();
//        }
//        if (getStatus().equals("BOARD IS FULL")) {
//            currentState += "\r\n" + getStatus();
//        }
//        if (getStatus().equals("WINNER")) {
//            currentState += "\r\n" + getStatus() + ": " + getWinner().getName();
//        }
//        return currentState;
//    }

}

