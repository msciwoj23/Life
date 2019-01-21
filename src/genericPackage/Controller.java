package genericPackage;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Controller extends AnimationTimer {

    private View view;
    private Logic logic;
    private Memory memory;
    private Stage primaryStage;
    private boolean isPaused = true;

    private int generationCounter = 0;

    private int clock = 1;
    private int cycleTime = 5;
    private int nextCycleTime = cycleTime;

    Controller(Stage primaryStage) {

        this.view = new View(this);
        this.logic = new Logic();
        this.primaryStage = primaryStage;
        this.memory = new Memory(this);

        control();
    }

    @Override
    public void handle(long now) {

        if (!isPaused) {
            if (clock == cycleTime) {
                generationCounter++;
                boolean[][] boardToDisplay = logic.getNextGeneration();
                view.displayBoard(boardToDisplay);
                memory.addToHistory(boardToDisplay);
                clock = 0;
                updateCycleTime();
            } else {
                clock++;
            }
        }
    }

    private void updateCycleTime() {
        cycleTime = nextCycleTime;
    }

    private void control() {

        view.display(primaryStage);
        boolean[][] boardToDisplay = logic.getFirstGneration();
        memory.addToHistory(boardToDisplay);
        view.displayBoard(boardToDisplay);

        this.start();
    }

    void pauseToggle() {
        isPaused = !isPaused;
        view.changePauseButton();
    }

    void reset() {
        boolean[][] boardToDisplay = logic.getNextGeneration(true);
        view.displayBoard(boardToDisplay);
        // memory.resetReadPointer();
    }

    void toggleLifeAt(double x, double y) {
        boolean[][] boardToDisplay = logic.getNextGeneration(x, y);
        view.displayBoard(boardToDisplay);
    }

    void getPrevious() {
        boolean[][] boardToDisplay = memory.getOldBoard(2);
        view.displayBoard(boardToDisplay);
        logic.setMainBoard(boardToDisplay);
    }

    void setNextCycleTime(int time) {
        this.nextCycleTime = time;
    }
}
