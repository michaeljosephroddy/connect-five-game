package com.example.server;

public class GameService {

    private static final int HEIGHT = 6;
    private static final int WIDTH = 19;
    private Game game;

    public GameService() {
        game = new Game();
    }

    /**
     * @param name the players name who is joining the game
     * @return the current game state
     */
    public Game joinGame(String name) {
        // if players join after leaving, reset game or..
        if (game.getNumberOfPlayers() == 0 || game.getIsBoardFull() || !game.getWinner().getName().equals("") || game.getPlayerHasLeft()) {
            game = new Game();
        }
        if (game.getNumberOfPlayers() == 2) { // if more than 2 players try to join
            game.setTooManyPlayers(true);
            return game;
        }
        if (game.getNumberOfPlayers() == 0) { // assign player 1
            Player player1 = new Player(name, 'X');
            game.setPlayer1(player1);
            game.setPlayerTurn(player1);
            game.setStatus("WAITING FOR PLAYER 2 TO JOIN");
        }
        if (game.getNumberOfPlayers() == 1) { // assign player 2
            Player player2 = new Player(name, 'O');
            game.setPlayer2(player2);
            game.setStatus("WAITING FOR PLAYER 1 TO MOVE: " + game.getPlayer1().getName());
        }
        game.setNumberOfPlayers(game.getNumberOfPlayers() + 1);
        return game;
    }

    /**
     * @param name the name of the player leaving the game
     * @return the newly updated game status
     */
    public Game leaveGame(String name) {
        if (game.getPlayer1().getName().equals(name)) {
            game.setPlayer1(new Player());
            game.setPlayer2(new Player());
            game.setStatus("PLAYER 1 HAS LEFT THE GAME: " + name);
        } else {
            game.setPlayer2(new Player());
            game.setPlayer1(new Player());
            game.setStatus("PLAYER 2 HAS LEFT THE GAME: " + name);
        }
        // if a player leaves then next time when we try to join the number of players needs to be 0
        game.setNumberOfPlayers(0);
        game.setPlayerHasLeft(true);
        game.setTooManyPlayers(false);
        return game;
    }

    /**
     * @param name the name of the player
     * @param input  the user input
     * @return the correct response based on the user input e.g check status, quit, or make move
     */
    public Game handleUserInput(String name, String input) {
        if (isValidInput(input)) {
            if (input.equals("check-status")) {
                return checkStatus();
            }
            if (input.equals("quit")) {
                return leaveGame(name);
            }
            if (game.getPlayer2().getName().equals("")) {
                return game;
            }
            int col = Integer.parseInt(input);
            return makeMove(name, col);
        }
        return game;
    }

    /**
     * @param name the players name who is making the move
     * @param col the column to place the players piece
     * @return the current state of the game board
     */
    public Game makeMove(String name, int col) {
        char[][] board = game.getBoard();
        int convertedCol = convertColumnNumber(col);
        if (isValidMove(col) && !isBoardFull(board)) {
            for (int row = HEIGHT - 1; row >= 0; row--) {
                if (board[row][convertedCol] == '_' && game.getPlayerTurn().getName().equals(name)) {
                    board[row][convertedCol] = game.getPlayerTurn().getPiece();
                    if (checkWin(board)) {
                        game.setStatus("WINNER: " + game.getPlayerTurn().getName());
                        game.setWinner(game.getPlayerTurn());
                        return game;
                    }
                    if (isBoardFull(board)) {
                        game.setIsBoardFull(true);
                        game.setStatus("BOARD IS FULL");
                        return game;
                    }
                    if (game.getPlayerTurn().getName().equals(game.getPlayer1().getName())) {
                        game.setStatus("WAITING FOR PLAYER 2 TO MOVE: " + game.getPlayer2().getName());
                    } else {
                        game.setStatus("WAITING FOR PLAYER 1 TO MOVE: " + game.getPlayer1().getName());
                    }
                    game.setPlayerTurn(game.getNextPlayer());
                    break;
                }
            }
        }
        return game;
    }

    /**
     * @param col the column to check if it is a valid move
     * @return true if it is a valid move, otherwise false
     */
    public boolean isValidMove(int col) {
        char[][] board = game.getBoard();
        int convertedCol = convertColumnNumber(col);
        return board[0][convertedCol] == '_';
    }


    /**
     * @param col the column number to convert to corresponding column number on the board
     * @return the converted column number
     */
    public int convertColumnNumber(int col) {
        return col + (col - 1);
    }

    /**
     * checks for a win on the horizontal, vertical, and diagonals.
     * @return true if there is a win (5 in a row), otherwise, false.
     */
    public boolean checkWin(char[][] board) {
        //check horizontal
        for (int row = 0; row <= HEIGHT - 1; row++) {
            for (int col = 1; col <= WIDTH - 10; col += 2) {
                if (board[row][col] != '_'
                        && board[row][col] == board[row][col + 2]
                        && board[row][col + 2] == board[row][col + 4]
                        && board[row][col + 4] == board[row][col + 6]
                        && board[row][col + 6] == board[row][col + 8]) {
                    return true;
                }
            }
        }
        // check vertical
        for (int col = 1; col <= WIDTH - 2; col += 2) {
            for (int row = 0; row <= HEIGHT - 5; row++) {
                if (board[row][col] != '_'
                        && board[row][col] == board[row + 1][col]
                        && board[row + 1][col] == board[row + 2][col]
                        && board[row + 2][col] == board[row + 3][col]
                        && board[row + 3][col] == board[row + 4][col]) {
                    return true;
                }
            }
        }
        // check diagonal top left to bottom right
        for (int row = 0; row <= HEIGHT - 5; row++) {
            for (int col = 1; col <= WIDTH - 10; col += 2) {
                if (board[row][col] != '_'
                        && board[row][col] == board[row + 1][col + 2]
                        && board[row + 1][col + 2] == board[row + 2][col + 4]
                        && board[row + 2][col + 4] == board[row + 3][col + 6]
                        && board[row + 3][col + 6] == board[row + 4][col + 8]) {
                    return true;
                }
            }
        }
        // check diagonal top right to bottom left
        for (int row = 0; row <= HEIGHT - 5; row++) {
            for (int col = WIDTH - 2; col >= WIDTH - 10; col -= 2) {
                if (board[row][col] != '_'
                        && board[row][col] == board[row + 1][col - 2]
                        && board[row + 1][col - 2] == board[row + 2][col - 4]
                        && board[row + 2][col - 4] == board[row + 3][col - 6]
                        && board[row + 3][col - 6] == board[row + 4][col - 8]) {
                    return true;
                }
            }
        }
        return false; // otherwise return false
    }

    /**
     * @return true if the board is full, otherwise, false
     */
    public boolean isBoardFull(char[][] board) {
        int count = 0;
        for (int col = 1; col <= WIDTH - 2; col += 2) {
            if (board[0][col] != '_') {
                count++;
            }
        }
        return count == 9;
    }

    /**
     * @param input
     * @return true is the user input is valid, otherwise, false
     */
    public boolean isValidInput(String input) {
        if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9") || input.equals("check-status") || input.equals("quit")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the current status of the game and game state
     */
    public Game checkStatus() {
        return game;
    }

    /**
     * @return the current game
     */
    public Game getGame() {
        return game;
    }

}

