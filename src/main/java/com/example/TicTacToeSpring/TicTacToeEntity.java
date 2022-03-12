package com.example.TicTacToeSpring;

import javax.persistence.*;

enum CellStatus {Empty, X, O}

enum Player {X, O}

@Entity
public class TicTacToeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    public Player player;
    public String gameTable;

    public TicTacToeEntity() {
        this(new GameLogic());
    }

    public TicTacToeEntity(GameLogic game) {
        this.gameTable = BoardSerializer.serialize(game.board);
        this.player = game.currentPlayer;
    }

    public Long getId() {
        return id;
    }
}