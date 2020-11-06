package Sudoku.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public boolean verify() {
        Set<Integer> setValues = new HashSet<>();
        for (Field field : object) {
            if (field != null && !setValues.add(field.getValue()) && field.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
}
