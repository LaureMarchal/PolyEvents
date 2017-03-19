package ui.signin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.Controller;

/**
 * Controller for the login interface
 */
public class SigninController {

    /**
     * The label to show a message to the user (like a login error)
     */
    @FXML
    private Label messageLabel;

    /**
     * The field to write the pseudo
     */
    @FXML
    private TextField pseudoField;

    /**
     * The field to write the password
     */
    @FXML
    private TextField passwordField;

    public void onProviderSelected() {
        System.out.println("Provider selected");
    }

    public void onCancel() {
        Controller.getInstance().goTo("login/loginView");
    }

}
