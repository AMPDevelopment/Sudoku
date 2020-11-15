package Sudoku;

import javafx.application.Application;
import javafx.stage.Stage;

public class Startup extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            new SudokuFX(stage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
