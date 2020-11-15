package Sudoku.backend.backtrack;

import Sudoku.models.Board;

import static Sudoku.models.Board.DIMENSION;

public class Solver {
    private Board board;
    private Board copyBoard;

    public Solver(Board board) throws Exception {
        this.board = board;
        this.copyBoard = board.clone();

        if (this.solver()) {
            System.out.println("Solved");
        }
        else {
            System.out.println("No solution");
        }
    }

    private boolean isSafe(int row, int column, int value) throws Exception {
        for (int rowClash = 0; rowClash < DIMENSION; rowClash++) {
            if (this.board.get(row, rowClash) == value) {
                return false;
            }
        }

        for (int columnClash = 0; columnClash < DIMENSION; columnClash++) {
            if (this.board.get(columnClash, column) == value) {
                return false;
            }
        }

        int boxClash = (int)Math.sqrt(DIMENSION);
        int boxRowStart = row - row % boxClash;
        int boxColumnStart = column - column % boxClash;

        for (int rowBox = boxRowStart; rowBox < boxRowStart + boxClash; rowBox++) {
            for (int columnBox = boxColumnStart; columnBox < boxColumnStart + boxClash; columnBox++) {
                if (this.board.get(rowBox, columnBox) == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean solver() throws Exception {
        int row = -1;
        int column = -1;
        boolean isEmpty = true;

        for (int rowCoordinate = 0; rowCoordinate < DIMENSION; rowCoordinate++) {
            for (int columnCoordinate = 0; columnCoordinate < DIMENSION; columnCoordinate++) {
                if (this.board.get(rowCoordinate, columnCoordinate) == 0) {
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
            if (isSafe(row, column, value)) {
                board.set(row, column, value);
                if (solver()) {
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
