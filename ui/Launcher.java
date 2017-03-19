package ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launcher for the PolyEvents application
 */
public class Launcher extends Application {

    /**
     * Launch the application
     *
     * @param args No arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller.getInstance().setStage(primaryStage);
        Controller.getInstance().goTo("login/loginView");
    }
}
