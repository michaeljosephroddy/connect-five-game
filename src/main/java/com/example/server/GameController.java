package com.example.server;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private GameService gameService = new GameService();

    @GetMapping("/join-game/{name}")
    public String joinGame(@PathVariable String name) { return gameService.joinGame(name); }

    @GetMapping("/leave-game/{name}")
    public String leaveGame(@PathVariable String name) { return gameService.leaveGame(name); }

    @GetMapping("/make-move/{name}/{col}")
    public String makeMove(@PathVariable String name, @PathVariable int col) {
        return gameService.makeMove(name, col);
    }

    @GetMapping("/check-status")
    public String checkStatus() {
        return gameService.checkStatus();
    }

    @GetMapping("/check-win")
    public String checkWin() { return Boolean.toString(gameService.checkWin()); }

    @GetMapping("/check-number-of-players")
    public int checkNumPlayers() {
        return gameService.checkNumberOfPlayers();
    }

    @GetMapping("/is-valid-input/{userInput}")
    public String isValidInput(@PathVariable String userInput) { return Boolean.toString(gameService.isValidInput(userInput)); }

    @GetMapping("/is-valid-move/{col}")
    public String ifIsValidMove(@PathVariable int col) { return Boolean.toString(gameService.isValidMove(col)); }

    @GetMapping("is-board-full")
    public String checkBoard() { return Boolean.toString(gameService.isBoardFull()); }

}

