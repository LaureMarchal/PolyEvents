package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login/loginView.fxml"));
        primaryStage.setTitle("PolyEvents");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
