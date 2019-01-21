package genericPackage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;


class View {

    // width 600
    // height 628
    private Controller controller;

    private Pane pane;
    private Scene scene;
    private Stage primaryStage;
    private Pane secondaryPane;
    private Button pauseButton;

    View(Controller controller) {
       this.controller = controller;
    }

    void display(Stage incomingStage) {

        this.pane = new Pane();
        this.scene = new Scene(pane);
        this.primaryStage = incomingStage;

        final String BLUE_PANE_COLOR = "-fx-background-color: blue;";
        final int BLUE_PANE_SIZE = 600;
        final int BLUE_PANE_X_AXIS_TRANSLATION = 100;
        final int BLUE_PANE_Y_AXIS_TRANSLATION = 50;

        this.secondaryPane = new Pane();
        secondaryPane.setStyle(BLUE_PANE_COLOR);
        secondaryPane.setPrefSize(BLUE_PANE_SIZE,BLUE_PANE_SIZE);
        secondaryPane.setTranslateX(BLUE_PANE_X_AXIS_TRANSLATION);
        secondaryPane.setTranslateY(BLUE_PANE_Y_AXIS_TRANSLATION);
        secondaryPane.setOnMousePressed(me -> passMouseCoordinates(me.getX(), me.getY()));

        final String PAUSE_BUTTON_TEXT_VALUE = "Start";

        Button pauseButton = new Button(PAUSE_BUTTON_TEXT_VALUE);
        this.pauseButton = pauseButton;
        pauseButton.setOnMousePressed(me -> controller.pauseToggle());

        final int RESET_BUTTON_X_TRANSLATION = 60;
        final String RESET_BUTTON_TEXT_VALUE = "Reset";

        Button resetButton = new Button(RESET_BUTTON_TEXT_VALUE);
        resetButton.setOnMousePressed(me -> controller.reset());
        resetButton.setTranslateX(RESET_BUTTON_X_TRANSLATION);

        final int SLIDER_MIN_VALUE = 1;
        final int SLIDER_MAX_VALUE = 100;
        final int SLIDER_DEFAULT_VALUE = 5;
        final int SLIDER_X_TRANSLATION = 120;
        final int SLIDER_MAJOR_TICK = 49;
        final int SLIDER_MINOR_TICK = 5;
        final int SLIDER_BLOCK_INCREMENT = 10;

        Slider slider = new Slider();
        slider.setMin(SLIDER_MIN_VALUE);
        slider.setMax(SLIDER_MAX_VALUE);
        slider.setValue(SLIDER_DEFAULT_VALUE);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(SLIDER_MAJOR_TICK);
        slider.setMinorTickCount(SLIDER_MINOR_TICK);
        slider.setBlockIncrement(SLIDER_BLOCK_INCREMENT);
        slider.setTranslateX(SLIDER_X_TRANSLATION);

        slider.valueProperty().addListener((ov, old_val, new_val) -> controller.setNextCycleTime((new_val.intValue())));

        final int PREVIOUS_BUTTON_X_TRANSLATION = 330;

        Button previousStateButton = new Button("Previous");
        previousStateButton.setOnMousePressed(me -> controller.getPrevious());
        previousStateButton.setTranslateX(PREVIOUS_BUTTON_X_TRANSLATION);


        final int PRIMARY_STAGE_WIDTH = 800;
        final int PRIMARY_STAGE_HEIGHT = 700;
        final String PRIMARY_STAGE_TITLE = "Game Of Life";

        primaryStage.setScene(scene);
        primaryStage.setTitle(PRIMARY_STAGE_TITLE);
        primaryStage.setWidth(PRIMARY_STAGE_WIDTH);
        primaryStage.setHeight(PRIMARY_STAGE_HEIGHT);

        pane.getChildren().add(secondaryPane);
        pane.getChildren().addAll(pauseButton, resetButton, slider, previousStateButton);
        primaryStage.show();
    }

    private void decideIfShow(boolean cellValue, int row, int cell) {

        final int CUBE_SIDE = 12;

        if (cellValue) {
            int x = (cell) * CUBE_SIDE;
            int y = (row ) * CUBE_SIDE;
            Rectangle dot = new Rectangle(x, y,CUBE_SIDE,CUBE_SIDE);
            dot.setFill(Color.BLACK);
            secondaryPane.getChildren().add(dot);
        }
    }

    private void passMouseCoordinates(double x, double y) {
        controller.toggleLifeAt(x, y);
    }

    void displayBoard(boolean[][] boardToDisplay) {

        secondaryPane.getChildren().clear();

        for (int row = 0; row < boardToDisplay.length; row++) {
            for (int cell = 0; cell < boardToDisplay[row].length; cell++) {
                decideIfShow(boardToDisplay[row][cell], row, cell);
            }
        }
    }

    void changePauseButton() {
        if (pauseButton.getText().equals("Pause")) {
            pauseButton.setText("Start");
        } else {
            pauseButton.setText("Pause");
        }
    }
}
