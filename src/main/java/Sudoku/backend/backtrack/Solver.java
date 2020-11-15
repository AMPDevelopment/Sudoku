package Sudoku.backend.backtrack;

import Sudoku.models.Board;

import static Sudoku.models.Board.DIMENSION;

public class Solver {
    public Solver(Board board) throws Exception {
        if (this.solver(board)) {
            System.out.println("Solved");
        }
        else {
            System.out.println("No solution");
        }
    }

    private boolean isSafe(Board board, int row, int column, int value) throws Exception {
        for (int rowClash = 0; rowClash < DIMENSION; rowClash++) {
            if (board.get(row, rowClash) == value) {
                return false;
            }
        }

        for (int columnClash = 0; columnClash < DIMENSION; columnClash++) {
            if (board.get(columnClash, column) == value) {
                return false;
            }
        }

        int boxClash = (int)Math.sqrt(DIMENSION);
        int boxRowStart = row - row % boxClash;
        int boxColumnStart = column - column % boxClash;

        for (int rowBox = boxRowStart; rowBox < boxRowStart + boxClash; rowBox++) {
            for (int columnBox = boxColumnStart; columnBox < boxColumnStart + boxClash; columnBox++) {
                if (board.get(rowBox, columnBox) == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean solver(Board board) throws Exception {
        int row = -1;
        int column = -1;
        boolean isEmpty = true;

        for (int rowCoordinate = 0; rowCoordinate < DIMENSION; rowCoordinate++) {
            for (int columnCoordinate = 0; columnCoordinate < DIMENSION; columnCoordinate++) {
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

        for (int value = 1; value <= DIMENSION; value++) {
            if (isSafe(board, row, column, value)) {
                board.set(row, column, value);
                if (solver(board)) {
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
