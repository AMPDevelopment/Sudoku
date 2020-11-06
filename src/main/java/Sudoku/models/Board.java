package Sudoku.models;

import Sudoku.backend.backtrack.Remover;
import Sudoku.backend.backtrack.Solver;

import java.util.Arrays;
import java.util.List;

public class Board {
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
     * Gets a Sudoku board.
     * @return Returns this board.
     */
    public List<List<Field>> getBoard() {
        return this.clone().board;
    }

    /**
     * Gets the field by coordinates.
     * @param x Represents the x coordinate (column).
     * @param y Represents the y coordinate (row).
     * @return Returns a field.
     */
    public Field getField(int x, int y) {
        return this.board.get(x).get(y);
    }

    /**
     * Resets the field to the default value.
     * @param x Represents the x coordinate (column).
     * @param y Represents the y coordinate (row).
     */
    public void resetField(int x, int y) {
        this.board.get(x).get(y).setValue(0);
    }

    /**
     * Gets the sector in which the coordinates points to with all fields.
     * @param x Represents the x coordinate (column).
     * @param y Represents the y coordinate (row).
     * @return Returns the sector with all fields.
     * @throws Exception
     */
    public Sector getSector(int x, int y) throws Exception {
        int sectorIndex = this.getSectorIndex(x, y);
        int boxColumn = (sectorIndex / 3) * 3;
        int boxRow = (sectorIndex % 3) * 3;
        int fieldIndex = 0;

        Field[] fields = new Field[DIMENSION];
        for (int i = boxColumn; i <= boxColumn + 2; i++) {
            for (int j = boxRow; j <= boxRow +2; j++) {
                fields[fieldIndex] = this.board.get(i).get(j);
                fieldIndex++;
            }
        }

        return new Sector(Arrays.asList(fields));
    }

    private int getSectorIndex(int x, int y) {
        return x / 3 + (y / 3) * 3;
    }

    /**
     * Gets the entire fields of a column.
     * @param x Represents the x coordinate (column).
     * @return Returns an entire list of fields on a column.
     * @throws Exception
     */
    public Column getColumn(int x) throws Exception {
        Field[] fields = new Field[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            fields[i] = this.board.get(x).get(i);
        }

        return new Column(Arrays.asList(fields));
    }

    /**
     * Gets the entire fields of a row.
     * @param y Represents the y coordinate (row).
     * @return Returns an entire list of fields on a row.
     * @throws Exception
     */
    public Row getRow(int y) throws Exception {
        Field[] fields = new Field[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            fields[i] = this.board.get(i).get(y);
        }

        return new Row(Arrays.asList(fields));
    }

    /**
     * Gets the value of the field.
     * @param x Represents the x coordinate (column).
     * @param y Represents the y coordinate (row).
     * @return Returns the value of the field.
     * @throws Exception
     */
    public int get(int x, int y) throws Exception {
        if (x < 0 || x > DIMENSION) {
            throw new Exception("Invalid x index to get a value.");
        }
        if (y < 0 || y > DIMENSION) {
            throw new Exception("Invalid y index to get a value.");
        }

        return this.board.get(x).get(y).getValue();
    }

    /**
     * Sets the value on a field.
     * @param x Represents the x coordinate (column).
     * @param y Represents the y coordinate (row).
     * @param value The new value that needs to be set.
     * @throws Exception
     */
    public void set(int x, int y, int value) throws Exception {
        if (x < 0 || x > DIMENSION) {
            throw new Exception("Invalid x index to set a value.");
        }
        if (y < 0 || y > DIMENSION) {
            throw new Exception("Invalid y index");
        }
        if (value < 0 || value > DIMENSION)  {
            throw new Exception("Invalid value");
        }

        this.board.get(x).get(y).setValue(value);
    }

    /**
     * Solves the current board with the current existing values.
     * @throws Exception
     */
    public void solve() throws Exception {
        new Solver(this);
    }

    public void remover(int amountToRemove) throws Exception {
        new Remover(this, amountToRemove);
    }

    /**
     * Clears and sets every value to 0 of every field on the current board.
     */
    public void clear() {
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                this.board.get(x).set(y, new Field());
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
     * Clones this board.
     * @return Returns a cloned version of this board.
     */
    public Board clone() {
        Board board = new Board();
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                try {
                    board.set(x, y, this.getField(x, y).getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return board;
    }
}
