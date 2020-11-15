package Sudoku;

import Sudoku.models.Board;
import Sudoku.models.Field;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

import static Sudoku.models.Board.DIMENSION;

public class SudokuFX {
    private Board board;
    private Stage stage;
    private GridPane gridPane;
    private List<List<TextField>> fields;

    public SudokuFX(Stage stage) {
        this.initGui(stage);
        this.initBackend();
    }

    private void initGui(Stage stage) {
        this.stage = stage;

        MenuBar menuBar = new MenuBar();
        EventHandler<ActionEvent> actionEvents = eventHandler();
        Menu gameMenu = new Menu("Game");
        MenuItem newGameMenuItem = new MenuItem("New game");
        MenuItem solveMenuItem = new MenuItem("Solve");
        MenuItem resetMenuItem = new MenuItem("Reset");
        MenuItem checkMenuItem = new MenuItem("Check");

        newGameMenuItem.setOnAction(actionEvents);
        solveMenuItem.setOnAction(actionEvents);
        resetMenuItem.setOnAction(actionEvents);
        checkMenuItem.setOnAction(actionEvents);
        gameMenu.getItems().addAll(newGameMenuItem, solveMenuItem, resetMenuItem, checkMenuItem);

        menuBar.getMenus().addAll(gameMenu);
        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.BASELINE_CENTER);

        VBox root = new VBox(menuBar, gridPane);
        root.setSpacing(5);
        root.setStyle("-fx-background-color: #1C252E;");
        Scene scene = new Scene(root, 590, 615);
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initBackend() {
        this.board = new Board();

        fields = Arrays.asList(
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION]),
                Arrays.asList(new TextField[DIMENSION])
        );

        this.reset(true);
    }

    private void newGame() {
        System.out.println("New game");
        // Todo: Create a new game
    }

    private void solve() throws Exception {
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                int value = Integer.parseInt(fields.get(row).get(column).getText());
                this.board.set(row, column, value);
            }
        }

        this.board.solve();
        this.reset(false);
    }

    private void reset(boolean clear) {
        if (clear) {
            this.board.clear();
        }

        if (!this.gridPane.getChildren().isEmpty()) {
            this.gridPane.getChildren().removeAll(fields);
        }

        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                Field field = this.board.getBoard().get(row).get(column);
                fields.get(row).set(column, new TextField());
                TextField textField = fields.get(row).get(column);
                textField.setPrefWidth(60);
                textField.setPrefHeight(60);
                textField.setText(field.toString());
                textField.setAlignment(Pos.CENTER);
                textField.setTextFormatter(new TextFormatter<>(change ->
                        (change.getControlNewText().matches("([0-9]$)?")) ? change : null));

                if (this.board.getSectorIndex(row, column) % 2 == 0) {
                    if (clear) {
                        textField.setStyle("-fx-control-inner-background: #1C252E");
                    }
                    else {
                        textField.setStyle("-fx-control-inner-background: #1C252E;-fx-border-color: #4CD964;-fx-border-width: 2px;-fx-border-radius: 5px");

                    }
                }
                else {
                    if (clear) {
                        textField.setStyle("-fx-control-inner-background: #f9f9f9");
                    }
                    else {
                        textField.setStyle("-fx-control-inner-background: #f9f9f9;-fx-border-color: #4CD964;-fx-border-width: 2px;-fx-border-radius: 5px");
                    }
                }

                if (clear) {
                    textField.setEditable(true);
                }
                else {
                    textField.setEditable(false);

                }

                this.gridPane.add(textField, column, row);
            }
        }
    }

    private void check() {
        System.out.println("Check");
    }

    private EventHandler<ActionEvent> eventHandler() {
        return event -> {
            MenuItem menuItem = (MenuItem) event.getSource();
            String side = menuItem.getText();
            try {
                if ("new game".equalsIgnoreCase(side)) {
                    newGame();
                } else if ("solve".equalsIgnoreCase(side)) {
                    solve();
                } else if ("reset".equalsIgnoreCase(side)) {
                    reset(true);
                } else if ("check".equalsIgnoreCase(side)) {
                    check();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        };
    }
}
