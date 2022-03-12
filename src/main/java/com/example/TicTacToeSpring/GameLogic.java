package com.example.TicTacToeSpring;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class GameLogic {

    public CellStatus[][] board;
    public Player currentPlayer;

    public GameLogic() {
        currentPlayer = Player.X;
        board = new CellStatus[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = CellStatus.Empty;
    }

    public GameLogic(TicTacToeEntity move) {
        this.currentPlayer = move.player;
        this.board = BoardSerializer.deserialize(move.gameTable);
    }

    public Optional<Player> getWinner() {
        final var b = board;
        Function<CellStatus, Optional<Player>> winner = cell -> Optional.of(cell == CellStatus.X ? Player.X : Player.O);

        // check rows
        if (b[0][0] == b[0][1] && b[0][0] == b[0][2] && b[0][0] != CellStatus.Empty) return winner.apply(b[0][0]);
        if (b[1][0] == b[1][1] && b[1][0] == b[1][2] && b[1][0] != CellStatus.Empty) return winner.apply(b[1][0]);
        if (b[2][0] == b[2][1] && b[2][0] == b[2][2] && b[2][0] != CellStatus.Empty) return winner.apply(b[2][0]);
        // check columns
        if (b[0][0] == b[1][0] && b[0][0] == b[2][0] && b[0][0] != CellStatus.Empty) return winner.apply(b[0][0]);
        if (b[0][1] == b[1][1] && b[0][1] == b[2][1] && b[0][1] != CellStatus.Empty) return winner.apply(b[0][1]);
        if (b[0][2] == b[1][2] && b[0][2] == b[2][2] && b[0][2] != CellStatus.Empty) return winner.apply(b[0][2]);
        // check diagonals
        if (b[0][0] == b[1][1] && b[0][0] == b[2][2] && b[0][0] != CellStatus.Empty) return winner.apply(b[0][0]);
        if (b[0][2] == b[1][1] && b[0][2] == b[2][0] && b[0][2] != CellStatus.Empty) return winner.apply(b[0][2]);
        return Optional.empty();
    }

    public boolean isDraw() {
        return Arrays.stream(board).flatMap(Arrays::stream).allMatch(cell -> cell != CellStatus.Empty);
    }

    public boolean isGameOver() {
        return getWinner().isPresent() || isDraw();
    }

    public void makeMove(int i, int j) {
        board[i][j] = currentPlayer == Player.X ? CellStatus.X : CellStatus.O;
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    public boolean isMoveValid(int i, int j) {
        return board[i][j] == CellStatus.Empty;
    }
}
