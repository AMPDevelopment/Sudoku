package Sudoku.models;

import Sudoku.backend.backtrack.Solver;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Board implements Cloneable, Serializable {
    private List<List<Field>> board;

    public static final int DIMENSION = 9;

    public Board() {
        this.initBoard();
    }

    /**
     * Initializes the board with fields and default value.
     */
    private void initBoard() {
        this.board = Arrays.asList(
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION]),
                Arrays.asList(new Field[DIMENSION])
        );

        this.clear();
        // Todo: Simplify the creation of the arrays of fields
    }

    /**
     * Gets a clone of this board.
     * @return Returns a cloned version of this board.
     */
    public List<List<Field>> getBoard() {
        return this.clone().board;
    }

    /**
     * Gets the field by coordinates.
     * @param row Represents the y coordinate (row).
     * @param column Represents the y coordinate (column).
     * @return Returns a field.
     */
    public Field getField(int row, int column) {
        return this.board.get(row).get(column);
    }

    /**
     * Resets the field to the default value.
     * @param row Represents the y coordinate (row).
     * @param column Represents the x coordinate (column).
     */
    public void resetField(int row, int column) {
        this.board.get(row).get(column).setValue(0);
    }

    /**
     * Gets the sector in which the coordinates points to with all fields.
     * @param row Represents the y coordinate (row).
     * @param column Represents the x coordinate (column).
     * @return Returns the sector with all fields.
     * @throws Exception
     */
    public Sector getSector(int row, int column) throws Exception {
        int sectorIndex = this.getSectorIndex(row, column);
        int boxRow = (sectorIndex / 3) * 3;
        int boxColumn = (sectorIndex % 3) * 3;
        int fieldIndex = 0;

        List<Field> fields = initNineFields();

        for (int rowIndex = boxRow; rowIndex < boxRow + 2; rowIndex++) {
            for (int columnIndex = boxColumn; columnIndex < boxColumn +2; columnIndex++) {
                Field field = this.board.get(rowIndex).get(columnIndex);
                fields.get(fieldIndex).setValue(field.getValue());
                fields.get(fieldIndex).setIsEditable(field.getIsEditable());
                fieldIndex++;
            }
        }

        return new Sector(fields);
    }

    public int getSectorIndex(int row, int column) {
        return (row / 3) * 3 + column / 3;
    }

    /**
     * Gets the entire fields of a column.
     * @param column Represents the x coordinate (column).
     * @return Returns an entire list of fields on a column.
     * @throws Exception
     */
    public Column getColumn(int column) throws Exception {
        List<Field> fields = initNineFields();

        for (int i = 0; i < DIMENSION; i++) {
            fields.get(i).setValue(this.board.get(i).get(column).getValue());
        }

        return new Column(fields);
    }

    /**
     * Gets the entire fields of a row.
     * @param row Represents the x coordinate (row).
     * @return Returns an entire list of fields on a row.
     * @throws Exception
     */
    public Row getRow(int row) throws Exception {
        List<Field> fields = initNineFields();

        for (int i = 0; i < DIMENSION; i++) {
            fields.get(i).setValue(this.board.get(row).get(i).getValue());
        }

        return new Row(fields);
    }

    private List<Field> initNineFields() {
        return Arrays.asList(
                new Field(),
                new Field(),
                new Field(),
                new Field(),
                new Field(),
                new Field(),
                new Field(),
                new Field(),
                new Field()
        );
    }

    /**
     * Gets the value of the field.
     * @param row Represents the y coordinate (row).
     * @param column Represents the x coordinate (column).
     * @return Returns the value of the field.
     * @throws Exception
     */
    public int get(int row, int column) throws Exception {
        if (row < 0 || row >= DIMENSION) {
            throw new Exception("Invalid row index to get a value.");
        }
        if (column < 0 || column >= DIMENSION) {
            throw new Exception("Invalid column index to get a value.");
        }

        return this.board.get(row).get(column).getValue();
    }

    /**
     * Sets the value on a field.
     * @param row Represents the y coordinate (row).
     * @param column Represents the y coordinate (column).
     * @param value The new value that needs to be set.
     * @throws Exception
     */
    public void set(int row, int column, int value) throws Exception {
        if (row < 0 || row >= DIMENSION) {
            throw new Exception("Invalid row index to set a value.");
        }
        if (column < 0 || column >= DIMENSION) {
            throw new Exception("Invalid column index to set a value.");
        }
        if (value < 0 || value > DIMENSION)  {
            throw new Exception("Invalid value.");
        }

        int temp = this.board.get(row).get(column).getValue();
        this.board.get(row).get(column).setValue(value);
        if (checkBoard(row, column)) {
            this.board.get(row).get(column).setValue(temp);
        }
    }

    /**
     * Solves the current board with the current existing values.
     * @throws Exception
     */
    public void solve() throws Exception {
        new Solver(this);
    }

    /**
     * Clears and sets every value to 0 of every field on the current board.
     */
    public void clear() {
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                this.board.get(row).set(column, new Field());
            }
        }
    }

    /**
     * Generates a new board (game).
     */
    public void generate() {
        // Todo: Generate a new board (game).
    }

    /**
     * Checks whether the value of the fields is valid or not.
     * @param row Represents the y coordinate (row).
     * @param column Represents the x coordinate (column).
     * @return Returns whether the value is valid or not.
     * @throws Exception
     */
    private boolean checkBoard(int row, int column) throws Exception {
        return !getRow(row).verify()
                || !getColumn(column).verify()
                || !getSector(row, column).verify();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Board)) {
            return false;
        }

        Board that = (Board) object;
        return new EqualsBuilder().append(that.board, this.board).isEquals();
    }

    public boolean isValid() throws Exception {
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                if (this.checkBoard(row, column)) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Clones this board.
     * @return Returns a cloned version of this board.
     */
    public Board clone() {
        Board clonedBoard = new Board();
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                try {
                    clonedBoard.set(row, column, this.getField(row, column).getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return clonedBoard;
    }
}
