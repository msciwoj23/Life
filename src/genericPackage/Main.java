package genericPackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        new Controller(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
