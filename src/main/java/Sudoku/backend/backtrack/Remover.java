package Sudoku.backend.backtrack;

import Sudoku.models.Board;

import java.util.Collections;
import java.util.Dictionary;
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
            int temp = this.boardClone.get(column, row);
            this.boardClone.resetField(column, row);
            this.checkAmountCombinations(this.boardClone);
            if (currentAmountPossibleBoards > 1) {
                this.boardClone.set(column, row, temp);
            }
            else {
                counter++;
            }
        }
    }

    private boolean checkAmountCombinations(Board board) throws Exception {
        for (int column = 0; column < DIMENSION; column++) {
            for (int row = 0; row < DIMENSION; row++) {
                if (board.get(column, row) == 0) {
                    List<Integer> range = IntStream.range(1, 10).boxed().collect(Collectors.toList());
                    Collections.shuffle(range);
                    for (int value : range) {
                        board.set(column, row, value);
                        if (board.get(column, row) == value) {
                            if (this.checkAmountCombinations(board)) {
                                return true;
                            }
                            else {
                                board.set(column, row, 0);
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
        for (int column = 0; column < DIMENSION; column++) {
            for (int row = 0; row < DIMENSION; row++) {
                if (this.boardClone.get(column, row) == 0) {
                    this.board.resetField(column, row);
                }
            }
        }
    }
}
