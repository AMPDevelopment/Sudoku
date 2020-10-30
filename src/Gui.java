import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Gui implements ActionListener{
    JButton buttonOK, buttonGen, buttonClear;
    JTextField tf3;
    JTextField[][] textField;
    public Gui(){
        JFrame newJFrame = new JFrame();
        newJFrame.setTitle("Sudoku GUI");
        textField = new JTextField[9][9];
        JPanel panel = new JPanel();

        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y] = new JTextField();
                newJFrame.add(textField[x][y]);
            }
        }

        newJFrame.setLayout(new GridLayout(10,9));
        newJFrame.setSize(600,400);

        buttonOK = new JButton("OK");
        panel.add(buttonOK);

        buttonGen = new JButton("Generate");
        panel.add(buttonGen);

        buttonClear = new JButton("Clear");
        panel.add(buttonClear);

        newJFrame.add(buttonGen);
        newJFrame.add(buttonClear);
        buttonOK.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonGen.addActionListener(this);
        newJFrame.add(panel);
        newJFrame.setVisible(true);

    }
    public static void main(String[] args) {
      new Gui();
    }
    private void SolveGame(){
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setBackground(Color.red);
            }
        }
    }
    private void ClearGame() {
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setText("");
                textField[x][y].setBackground(Color.white);
            }
        }
    }
    private void GenerateGame() {
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y].setText("zahl");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonOK){
            SolveGame();
        }
        if(e.getSource()==buttonClear){
            ClearGame();
        }
        if(e.getSource()==buttonGen){
            GenerateGame();
        }

    }
}
