package Sudoku.models;

import Sudoku.backend.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class Board {
    private List<List<Field>> board;

    public static final int DIMENSION = 9;

    public Board() {
        this.initBoard();
    }

    public void initBoard() {
        this.board = new ArrayList<>();
        for (int i = 0; i < DIMENSION; i++) {
            board.set(i, Arrays.asList(new Field[DIMENSION]));
        }
    }

    public void solveBoard() throws Exception {
        new Solver(this);
    }

    public List<List<Field>> getBoard() {
        return this.clone().board;
    }

    public Field getField(int x, int y) {
        return this.board.get(x).get(y);
    }

    public Sector getSector(int column, int row) throws Exception {
        int sectorIndex = this.getSectorIndex(column, row);
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

    public Column getColumn(int x) throws Exception {
        Field[] fields = new Field[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            fields[i] = this.board.get(x).get(i);
        }

        return new Column(Arrays.asList(fields));
    }

    public Row getRow(int y) throws Exception {
        Field[] fields = new Field[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            fields[i] = this.board.get(i).get(y);
        }

        return new Row(Arrays.asList(fields));
    }

    public int get(int x, int y) throws Exception {
        if (x < 0 || x > DIMENSION) {
            throw new Exception("Invalid x index to get a value.");
        }
        if (y < 0 || y > DIMENSION) {
            throw new Exception("Invalid y index to get a value.");
        }

        return this.board.get(x).get(y).getValue();
    }

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

    public void clear(List<List<Field>> board) {
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                board.get(x).set(y, new Field());
            }
        }
    }

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
