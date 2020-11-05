package Sudoku.models;

import java.util.Arrays;
import java.util.List;

import static Sudoku.models.Board.DIMENSION;

public class Item {
    private List<Field> object;

    public Item(List<Field> table) throws Exception {
        if (table.size() != DIMENSION) {
            throw new Exception("Invalid size of the object");
        }

        this.object = Arrays.asList(new Field[DIMENSION]);
        for (int i = 0; i < DIMENSION; i++) {
            this.object.set(i, table.get(i));
        }
    }
}
