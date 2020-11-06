package Sudoku.backend.backtrack;

import Sudoku.models.Board;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {
    public Solver(Board board) throws Exception {
        this.solver(board);
    }

    private boolean solver(Board board) throws Exception {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board.get(row, column) == 0) {
                    List<Integer> range = IntStream.range(1, 10).boxed().collect(Collectors.toList());
                    Collections.shuffle(range);
                    for (int value : range) {
                        board.set(row, column, value);
                        if (board.get(row, column) == value) {
                            if (this.solver(board)) {
                                return true;
                            }
                            else {
                                board.set(row, column, 0);
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return false;
    }
}
