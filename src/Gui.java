import java.awt.*;
import javax.swing.*;

public class Gui {
    public static void main(String[] args) {
        JFrame newJFrame = new JFrame();
        newJFrame.setTitle("Sudoku GUI");
        JTextField[][] textField = new JTextField[9][9];
        newJFrame.setSize(300, 150);
        JPanel panel = new JPanel();

        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                textField[x][y] = new JTextField();
                textField[x][y].setForeground(Color.RED);
                newJFrame.add(textField[x][y]);
            }
        }
        newJFrame.setLayout(new GridLayout(10,9));
        newJFrame.setSize(400,400);
        JButton buttonOK = new JButton("OK");
        panel.add(buttonOK);

        newJFrame.add(panel);
        newJFrame.setVisible(true);

    }
}
