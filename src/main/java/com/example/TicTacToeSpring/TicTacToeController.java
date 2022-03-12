package com.example.TicTacToeSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeController {

    TicTacToeRepository repository;

    public TicTacToeController(TicTacToeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/startGame")
    public GameLogic getGames() {
        return new GameLogic(repository.save(new TicTacToeEntity()));
    }

    @PostMapping("/move/{i}/{j}")
    public GameLogic move(@PathVariable Integer i, @PathVariable Integer j) {

        var previous = repository.findTopByOrderByIdDesc();

        if (previous.isEmpty()) throw new IllegalArgumentException("Game not found");

        var newGame = new GameLogic(previous.get());

        if (!newGame.isMoveValid(i, j)) throw new IllegalArgumentException("Move is not valid");
        if (newGame.isGameOver()) throw new IllegalArgumentException("Game is over");

        newGame.makeMove(i, j);
        repository.save(new TicTacToeEntity(newGame));
        return newGame;
    }
}
