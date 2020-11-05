package Sudoku;

import Sudoku.models.Board;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Sudoku implements ActionListener{
    JButton buttonCheck, buttonSolve, buttonGenerate, buttonClear;
    JTextField tf3;
    JTextField[][] textField;

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

        textField = new JTextField[9][9];

        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y] = new JTextField();
                newJFrame.add(textField[x][y]);
                textField[x][y].setText(this.board.getBoard().get(x).get(y).toString());
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
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                if (textField[x][y].getText() == "0") {
                    amountToRemove++;
                }
            }
        }

        this.board.solve();
        this.board.remover(amountToRemove);

        // Todo: Does not work so far kinda sucks but whatever...
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setText(this.board.getBoard().get(x).get(y).toString());
            }
        }
    }
    private void clear() {
        this.board.clear(this.board.getBoard());
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setText(this.board.getBoard().get(x).get(y).toString());
                textField[x][y].setBackground(Color.white);
            }
        }
    }

    private void generate() {
        this.board.generate(); // Todo: Generate random game
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
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
