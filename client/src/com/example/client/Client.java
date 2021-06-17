package com.example.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        Game joinedGame = joinGame(name);
        if (joinedGame.getTooManyPlayers() || joinedGame.getPlayerHasLeft()) {
            System.exit(0);
        }
        System.out.println(joinedGame);
        System.out.println("to check the game status enter: check-status");
        System.out.println("to leave the game enter: quit");
        System.out.println("to make a move enter: column (1-9)");

        while (input.hasNext()) {
            String userInput = input.nextLine();
            Game game = handleUserInput(name, userInput);
            System.out.println(game);
            if (!game.getWinner().getName().equals("") || game.getPlayerHasLeft() || game.getIsBoardFull()) {
                System.exit(0);
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
    public static Game joinGame(String name) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("accept", "application/json")
                .uri(new URI("http://localhost:8080/join-game/" + name))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Game game = objectMapper.readValue(response.body(), Game.class);
        return game;
    }


    /**
     * @param name the name of the player giving user input
     * @param input the user input. e.g check-status, col (1-9), or quit
     * @return the correct response based on the user input
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static Game handleUserInput(String name, String input) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("accept", "application/json")
                .uri(new URI("http://localhost:8080/handle-user-input/" + name + "/" + input))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Game game = objectMapper.readValue(response.body(), Game.class);
        return game;
    }

}

