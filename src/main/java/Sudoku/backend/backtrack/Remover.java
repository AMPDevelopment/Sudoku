package Sudoku.backend.backtrack;

import Sudoku.models.Board;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Sudoku.models.Board.DIMENSION;

public class Remover {
    private List<Integer> fieldValues;
    private Board board;
    private Board boardClone;
    private int amountFieldsToRemove;
    private int currentAmountPossibleBoards;

    public Remover(Board board, int amountFieldsToRemove) throws Exception {
        this.board = board;
        this.amountFieldsToRemove = amountFieldsToRemove;
        this.boardClone = this.board.clone();
        fieldValues = IntStream.range(0, 80).boxed().collect(Collectors.toList());
        Collections.shuffle(fieldValues);
        remove();
    }

    public void remove() throws Exception {
        int counter = 0;
        for (Integer i : fieldValues) {
            if (counter >= amountFieldsToRemove) {
                this.setBoardToClone();
                return;
            }
            int row = i / 9;
            int column = i % 9;
            currentAmountPossibleBoards = 0;
            int temp = this.boardClone.get(row, column);
            this.boardClone.resetField(row, column);
            this.checkAmountCombinations(this.boardClone);
            if (currentAmountPossibleBoards > 1) {
                this.boardClone.set(row, column, temp);
            }
            else {
                counter++;
            }
        }
    }

    private boolean checkAmountCombinations(Board board) throws Exception {
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                if (board.get(row, column) == 0) {
                    List<Integer> range = IntStream.range(1, 10).boxed().collect(Collectors.toList());
                    Collections.shuffle(range);
                    for (int value : range) {
                        board.set(row, column, value);
                        if (board.get(row, column) == value) {
                            if (this.checkAmountCombinations(board)) {
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

        this.currentAmountPossibleBoards++;
        return false;
    }

    private void setBoardToClone() throws Exception {
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                if (this.boardClone.get(row, column) == 0) {
                    this.board.resetField(row, column);
                }
            }
        }
    }
}
