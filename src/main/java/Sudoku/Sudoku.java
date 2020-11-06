package Sudoku;

import Sudoku.models.Board;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import static Sudoku.models.Board.DIMENSION;

public class Sudoku implements ActionListener{
    JMenuItem buttonCheck, buttonSolve, buttonGenerate, buttonClear;
    JTextField[][] fields;

    private Board board;

    public Sudoku(){
        this.board = new Board();
        this.initGui();
    }

    private void initGui() {
        JFrame frame = new JFrame();
        frame.setTitle("Sudoku");
        frame.setLayout(new GridLayout(9,9));
        frame.setSize(540,540);

        fields = new JTextField[DIMENSION - 1][DIMENSION - 1];

        for (int row = 0; row < DIMENSION - 1; row++){
            for (int column = 0; column < DIMENSION - 1; column++){
                fields[row][column] = new JTextField();
                frame.add(fields[row][column]);
                fields[row][column].setText(this.board.getBoard().get(row).get(column).toString());
            }
        }

        JMenuBar menuBar = new JMenuBar();

        buttonCheck = new JMenuItem("Check");
        buttonSolve = new JMenuItem("Solve");
        buttonGenerate = new JMenuItem("Generate");
        buttonClear = new JMenuItem("Clear");

        menuBar.add(buttonCheck);
        menuBar.add(buttonSolve);
        menuBar.add(buttonGenerate);
        menuBar.add(buttonClear);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        buttonSolve.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonGenerate.addActionListener(this);
    }

    private void check() {
        // Todo: Check the board whether it's correct or not.
    }

    private void solve() throws Exception {
        for (int row = 0; row < DIMENSION - 1; row++){
            for (int column = 0; column < DIMENSION - 1; column++){
                this.board.set(row, column, Integer.parseInt(fields[row][column].getText()));
            }
        }

        this.board.solve();

        for (int row = 0; row < DIMENSION - 1; row++){
            for (int column = 0; column < DIMENSION - 1; column++){
                fields[row][column].setText(this.board.getBoard().get(row).get(column).toString());
                fields[row][column].setBackground(Color.orange);
            }
        }
    }
    private void clear() {
        this.board.clear();
        for (int row = 0; row < DIMENSION - 1; row++){
            for (int column = 0; column <  DIMENSION - 1; column++){
                fields[row][column].setText(this.board.getBoard().get(row).get(column).toString());
                fields[row][column].setBackground(Color.white);
            }
        }
    }

    private void generate() {
        this.board.generate();
        for (int row = 0; row < DIMENSION - 1; row++){
            for (int column = 0; column < DIMENSION - 1; column++){
                // Todo: Generate random game
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCheck) {
            this.check();
        }
        if (e.getSource() == buttonSolve) {
            try {
                this.solve();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == buttonClear) {
            this.clear();
        }
        if (e.getSource() == buttonGenerate) {
            this.generate();
        }
    }
}
