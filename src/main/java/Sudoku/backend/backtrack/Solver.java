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
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board.get(x, y) == 0) {
                    List<Integer> range = IntStream.range(1, 10).boxed().collect(Collectors.toList());
                    Collections.shuffle(range);
                    for (int value : range) {
                        board.set(x, y, value);
                        if (board.get(x, y) == value) {
                            if (this.solver(board)) {
                                return true;
                            }
                            else {
                                board.set(x, y, 0);
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
