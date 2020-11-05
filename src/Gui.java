import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Gui implements ActionListener{
    JButton buttonOK, buttonGen, buttonClear, buttonCor;
    JTextField[][] textField;
    public Gui(){
        JFrame newJFrame = new JFrame();
        newJFrame.setTitle("Sudoku GUI");
        newJFrame.setSize(400,400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,9));
        textField = new JTextField[9][9];

        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y] = new JTextField();
                panel.add(textField[x][y]);
            }
        }

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonOK = new JButton("OK");
        panel2.add(buttonOK);

        buttonGen = new JButton("Generate");
        panel2.add(buttonGen);

        buttonClear = new JButton("Clear");
        panel2.add(buttonClear);

        buttonCor = new JButton("Correct");
        panel2.add(buttonCor);

        buttonOK.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonGen.addActionListener(this);
        buttonCor.addActionListener(this);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel);
        mainPanel.add(panel2);
        newJFrame.add(mainPanel);
        newJFrame.setVisible(true);

    }

    private void solveGame(){
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setBackground(Color.red);
            }
        }
    }
    private void checkGame(){
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setBackground(Color.red);
            }
        }
    }
    private void clearGame() {
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setText("");
                textField[x][y].setBackground(Color.white);
            }
        }
    }
    private void generateGame() {
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setText("zahl");
            }
        }
    }

    public static void main(String[] args) {
      new Gui();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonOK){
            solveGame();
        }
        if(e.getSource()==buttonClear){
            clearGame();
        }
        if(e.getSource()==buttonGen){
            generateGame();
        }
        if(e.getSource()==buttonCor){
            checkGame();
        }
    }
}
