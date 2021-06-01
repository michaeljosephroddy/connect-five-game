package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.println("enter your name: ");
        String name = input.nextLine();
        System.out.println(joinGame(name));
        if (Integer.parseInt(checkNumberOfPlayers()) > 2) {
            System.exit(0);
        }
        System.out.println("to check game status enter: check-status");
        System.out.println("to place a piece, enter a column (1-9)");

        while (input.hasNext()) {
            String userInput = input.nextLine();
            if (isValidInput(userInput).equals("true") && userInput.equals("check-status")) {
                System.out.println(checkStatus());
                if (Integer.parseInt(checkNumberOfPlayers()) < 2) {
                    System.exit(0);
                }
                if (checkWin().equals("true")) {
                    System.exit(0);
                }
            }
            if (isValidInput(userInput).equals("true") && !userInput.equals("check-status") && !userInput.equals("quit")) {
                int col = Integer.parseInt(userInput);
                if (isValidMove(col).equals("true")) {
                    if (checkStatus().equals("PLAYER 1 HAS LEFT THE GAME") || checkStatus().equals("PLAYER 2 HAS LEFT THE GAME")) {
                        System.out.println(checkStatus());
                        System.exit(0);
                    }
                    System.out.println(makeMove(name, col));
                    if (checkWin().equals("true") || isBoardFull().equals("true")) {
                        System.exit(0);
                    }
                }
            }
            if (isValidInput(userInput).equals("true") && userInput.equals("quit")) {
                System.out.println(leaveGame(name));
                System.exit(0);
            }
            if (isValidInput(userInput).equals("false")) {
                System.out.println("INVALID INPUT" + "\r\n" + "please enter (1-9) or check-status");
            }
        }
    }

    /**
     * @param name the name of the player joining the game
     * @return the current game status and game state
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String joinGame(String name) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/join-game/" + name))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @param name the name of the player leave the game
     * @return the current game state and game status
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String leaveGame(String name) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/leave-game/" + name))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @param name the name of the player making the move
     * @param col the column number to place the players piece
     * @return the newly updated game status and game state
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String makeMove(String name, int col) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/make-move/" + name + "/" + col))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @return the current status of the game and game state
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String checkStatus() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/check-status"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @return true if there is a winner, otherwise, false
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String checkWin() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/check-win"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @return the current number of players in the game
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String checkNumberOfPlayers() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/check-number-of-players"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @param userInput the input from the user
     * @return "true" if the input is valid, otherwise, "false"
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String isValidInput(String userInput) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/is-valid-input/" + userInput))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @param col the column number to check if it is valid
     * @return "true" if it is valid, otherwise, "false"
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String isValidMove(int col) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/is-valid-move/" + col))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * @return true if the board is full, otherwise, false
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static String isBoardFull() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/is-board-full/"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}

