package Sudoku;

import Sudoku.models.Board;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import static Sudoku.models.Board.DIMENSION;

public class Sudoku implements ActionListener{
    JButton buttonCheck, buttonSolve, buttonGenerate, buttonClear;
    JTextField[][] fields;

    private Board board;

    public Sudoku(){
        this.board = new Board();
        this.initGui();
    }

    private void initGui() {
        JFrame newJFrame = new JFrame();
        newJFrame.setTitle("Sudoku");
        newJFrame.setLayout(new GridLayout(10,9));
        newJFrame.setSize(400,440);

        JPanel panel = new JPanel();

        fields = new JTextField[9][9];

        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                fields[row][column] = new JTextField();
                newJFrame.add(fields[row][column]);
                fields[row][column].setText(this.board.getBoard().get(row).get(column).toString());
            }
        }

        buttonCheck = new JButton("Check");
        panel.add(buttonCheck);

        buttonSolve = new JButton("Solve");
        panel.add(buttonSolve);

        buttonGenerate = new JButton("Generate");
        panel.add(buttonGenerate);

        buttonClear = new JButton("Clear");
        panel.add(buttonClear);

        newJFrame.add(buttonCheck);
        newJFrame.add(buttonSolve);
        newJFrame.add(buttonClear);
        newJFrame.add(buttonGenerate);
        newJFrame.add(panel);
        newJFrame.setVisible(true);

        buttonSolve.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonGenerate.addActionListener(this);
    }

    private void check() {

    }

    private void solve() throws Exception {
        int amountToRemove = 0;
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                if (fields[row][column].getText().contains("0")) {
                    amountToRemove++;
                }
                this.board.set(row, column, Integer.parseInt(fields[row][column].getText()));
            }
        }
        System.out.println(amountToRemove);

        this.board.solve();
        this.board.remover(amountToRemove);

        for (int row = 0; row < DIMENSION; row++){
            for (int column = 0; column < DIMENSION; column++){
                fields[row][column].setText(this.board.getBoard().get(row).get(column).toString());
            }
        }
    }
    private void clear() {
        this.board.clear();
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                fields[row][column].setText(this.board.getBoard().get(row).get(column).toString());
                fields[row][column].setBackground(Color.white);
            }
        }
    }

    private void generate() {
        this.board.generate(); // Todo: Generate random game
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
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
