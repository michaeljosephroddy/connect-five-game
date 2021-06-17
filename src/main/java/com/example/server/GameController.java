package com.example.server;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private GameService gameService = new GameService();

    @PostMapping("/join-game/{name}")
    public Game joinGame(@PathVariable String name) {
        return gameService.joinGame(name);
    }

    @PostMapping("/handle-user-input/{name}/{input}")
    public Game handleUserInput(@PathVariable String name, @PathVariable String input) {
        return gameService.handleUserInput(name, input);
    }

    @GetMapping("/get-game")
    public Game getGame() {
        return gameService.getGame();
    }

}

