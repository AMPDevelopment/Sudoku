package Sudoku.backend.backtrack;

import Sudoku.models.Board;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Sudoku.models.Board.DIMENSION;

public class Solver {
    public Solver(Board board) throws Exception {
        int n = DIMENSION;
        if (this.solver(board)) {
            System.out.println("Solved");
        }
        else {
            System.out.println("No solution");
        }
    }

    private boolean isSafe(Board board, int row, int column, int value) throws Exception {
        for (int rowClash = 0; rowClash < DIMENSION - 1; rowClash++) {
            if (board.get(row, rowClash) == value) {
                return false;
            }
        }

        for (int columnClash = 0; columnClash < DIMENSION - 1; columnClash++) {
            if (board.get(columnClash, column) == value) {
                return false;
            }
        }

        int boxClash = (int)Math.sqrt(DIMENSION);
        int boxRowStart = row - row % boxClash;
        int boxColumnStart = column - column % boxClash;

        for (int rowBox = boxRowStart; rowBox < boxRowStart; rowBox++) {
            for (int columnBox = boxColumnStart; columnBox < boxColumnStart; columnBox++) {
                if (board.get(rowBox, columnBox) == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean solver(Board board, int n) throws Exception {
        int row = -1;
        int column = -1;
        boolean isEmpty = true;

        for (int rowCoordinate = 0; rowCoordinate < n; rowCoordinate++) {
            for (int columnCoordinate = 0; columnCoordinate < n; columnCoordinate++) {
                if (board.get(rowCoordinate, columnCoordinate) == 0) {
                    row = rowCoordinate;
                    column = columnCoordinate;
                    isEmpty = false;
                    break;
                }
                if (!isEmpty) {
                    break;
                }
            }
        }

        if (isEmpty) {
            return true;
        }

        for (int value = 1; value <= n; value++) {
            if (isSafe(board, row, column, value)) {
                board.set(row, column, value);
                if (solver(board, n)) {
                    return true;
                }
                else {
                    board.set(row, column, 0);
                }
            }
        }

        return false;
    }

    // Obsolete solver (ignore)
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
