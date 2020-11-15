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

    /**
     * Checks whether the provided value is valid or not.
     * @param row Represents the row coordinate.
     * @param column Represents the column coordinate.
     * @param value Represents the value of the provided coordinates.
     * @return Returns true or false whether the value is valid or not.
     * @throws Exception
     */
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

    /**
     * Backtracking the board.
     * @return Returns a completed sudoku.
     * @throws Exception
     */
    private boolean solver() throws Exception {
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                if (this.board.get(row, column) == 0) {
                    for (int value = 1; value <= DIMENSION; value++) {
                        if (isSafe(row, column, value)) {
                            this.board.set(row, column, value);
                            if (solver()) {
                                return true;
                            }
                            else {
                                this.board.set(row, column, 0);
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }
}
