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
    public String joinGame(String name) {
        game.setNumberOfPlayers(game.getNumberOfPlayers() + 1);
        if (game.getNumberOfPlayers() > 2) {
            return "TOO MANY PLAYERS CANNOT JOIN GAME";
        }
        if (game.getPlayer1() == null) {
            Player player1 = new Player(name, 'X');
            game.setPlayer1(player1);
            game.setStatus("WAITING FOR PLAYER 2 TO JOIN");
            game.setPlayerTurn(player1);
            game.setBoard(createBoard());
        } else {
            Player player2 = new Player(name, 'O');
            game.setPlayer2(player2);
            game.setStatus("WAITING FOR PLAYER 1 TO MOVE");
        }
        return game.toString();
    }

    /**
     * @param name the name of the player leaving the game
     * @return the newly updated game status
     */
    public String leaveGame(String name) {
        game.setNumberOfPlayers(game.getNumberOfPlayers() - 1);
        if (game.getPlayer1().getName().equals(name)) {
            game.setStatus("PLAYER 1 HAS LEFT THE GAME");
            game.setPlayer1(null);
        } else {
            game.setStatus("PLAYER 2 HAS LEFT THE GAME");
            game.setPlayer1(null);
        }
        return game.toString();
    }

    /**
     * creates the game board.
     * @return a 2d char array as the game board.
     */
    public static char[][] createBoard() {
        char[][] board = new char[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (col % 2 == 0) {
                    board[row][col] = '|';
                } else {
                    board[row][col] = '_';
                }
            }
        }
        return board;
    }

    /**
     * @param name the players name who is making the move
     * @param col the column to place the players piece
     * @return the current state of the game board
     */
    public String makeMove(String name, int col) {
        if (game.getPlayer2() == null) {
            game.setStatus("WAITING FOR PLAYER 2 TO JOIN");
            return game.toString();
        }
        char[][] board = game.getBoard();
        Player playerTurn = game.getPlayerTurn();
        Player player1 = game.getPlayer1();
        int convertedCol = convertColumnNumber(col);
        for (int row = HEIGHT - 1; row >= 0; row--) {
            if (board[row][convertedCol] == '_' && playerTurn.getName().equals(name)) {
                board[row][convertedCol] = playerTurn.getPiece();
                if (checkWin()) {
                    game.setStatus("WINNER");
                    game.setWinner(playerTurn);
                    return game.toString();
                }
                if (playerTurn.getName().equals(player1.getName())) {
                    game.setStatus("WAITING FOR PLAYER 2 TO MOVE");
                } else {
                    game.setStatus("WAITING FOR PLAYER 1 TO MOVE");
                }
                game.setPlayerTurn(game.getNextPlayer());
                break;
            }
        }
        return game.toString();
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
    public boolean checkWin() {
        char[][] board = game.getBoard();
        //check horizontal
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH - 10; col += 2) {
                if (board[row][col + 1] != '_'
                        && board[row][col + 1] == board[row][col + 3]
                        && board[row][col + 3] == board[row][col + 5]
                        && board[row][col + 5] == board[row][col + 7]
                        && board[row][col + 7] == board[row][col + 9]) {
                    return true;
                }
            }
        }

        // check vertical
        for (int col = 0; col < WIDTH - 2; col += 2) {
            for (int row = 0; row < HEIGHT - 4; row++) {
                if (board[row][col + 1] != '_'
                        && board[row][col + 1] == board[row + 1][col + 1]
                        && board[row + 1][col + 1] == board[row + 2][col + 1]
                        && board[row + 2][col + 1] == board[row + 3][col + 1]
                        && board[row + 3][col + 1] == board[row + 4][col + 1]) {
                    return true;
                }
            }
        }

        // check diagonal top left to bottom right
        for (int row = 0; row < HEIGHT - 4; row++) {
            for (int col = 0; col < WIDTH - 10; col += 2) {
                if (board[row][col + 1] != '_'
                        && board[row][col + 1] == board[row + 1][col + 3]
                        && board[row + 1][col + 3] == board[row + 2][col + 5]
                        && board[row + 2][col + 5] == board[row + 3][col + 7]
                        && board[row + 3][col + 7] == board[row + 4][col + 9]) {
                    return true;
                }
            }
        }

        // check diagonal top right to bottom left
        for (int row = 0; row < HEIGHT - 4; row++) {
            for (int col = WIDTH - 1; col >= WIDTH - 9; col -= 2) {
                if (board[row][col - 1] != '_'
                        && board[row][col - 1] == board[row + 1][col - 3]
                        && board[row + 1][col - 3] == board[row + 2][col - 5]
                        && board[row + 2][col - 5] == board[row + 3][col - 7]
                        && board[row + 3][col - 7] == board[row + 4][col - 9]) {
                    return true;
                }
            }
        }

        return false; // otherwise return false
    }

    /**
     * @return true if the board is full, otherwise, false
     */
    public boolean isBoardFull() {
        char[][] board = game.getBoard();
        int count = 0;
        for (int col = 1; col < WIDTH - 1; col += 2) {
            if (board[0][col] != '_') {
                count++;
            }
        }
        if (count == 9) {
            game.setStatus("BOARD IS FULL");
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param userInput
     * @return true is the user input is valid, otherwise, false
     */
    public boolean isValidInput(String userInput) {
        if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4") || userInput.equals("5") || userInput.equals("6") || userInput.equals("7") || userInput.equals("8") || userInput.equals("9") || userInput.equals("check-status") || userInput.equals("quit")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the current status of the game and game state
     */
    public String checkStatus() {
        return game.toString();
    }

    /**
     * @return the current number of players
     */
    public int checkNumberOfPlayers() {
        return game.getNumberOfPlayers();
    }

}

