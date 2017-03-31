package ui;

import bl.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Written by Théo Gauchoux
 * Controller providing helpful methods to sub controllers
 */
public class Controller {

    private static final String APP_NAME = "PolyEvents";
    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 650;

    private static Controller instance = new Controller();

    /**
     * The current stage of javafx
     */
    private Stage stage;

    /**
     * The user currently logged
     */
    private User userLogged;

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    /**
     * Set a new stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Go to a specific view
     * @param view
     */
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

    /**
     * Go to a specific view and passing data
     * @param view
     * @param data
     */
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

    /**
     * Retrieve the user logged
     * @return User
     */
    public User getUserLogged() {
        return userLogged;
    }

    /**
     * Set a logged user
     * @param userLogged
     */
    public void setUserLogged(User userLogged) {
        this.userLogged = userLogged;
    }

}
