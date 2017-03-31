package ui;

import bl.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private static final String APP_NAME = "PolyEvents";
    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;

    private static Controller instance = new Controller();

    private Stage stage;
    private User userLogged;

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void goTo(View view) {
        if (stage != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../ui/" + view.toString() + ".fxml"));
                stage.setTitle(APP_NAME);
                stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
                stage.show();
            } catch (IOException e) {
                System.err.println("The view " + "../ui/" + view.toString() + ".fxml" + " doesn't exist.");
                e.printStackTrace();
            }
        }
    }

    public void goTo(View view, Object data) {
        if (stage != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/" + view.toString() + ".fxml"));
                Parent root = loader.load();
                OnLoad controller = loader.getController();
                controller.onLoad(data);
                stage.setTitle(APP_NAME);
                stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
                stage.show();
            } catch (IOException e) {
                System.err.println("The view " + "../ui/" + view.toString() + ".fxml" + " doesn't exist.");
                e.printStackTrace();
            }
        }
    }

    public User getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(User userLogged) {
        this.userLogged = userLogged;
    }

}
