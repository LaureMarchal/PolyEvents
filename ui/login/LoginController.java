package ui.login;

import bl.exception.UserException;
import bl.facade.UserFacade;
import bl.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ui.Controller;
import ui.View;

/**
 * Written by Théo Gauchoux
 * Controller for the login interface
 */
public class LoginController {

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

    /**
     * On "login" button click, try to login the user with the couple of pseudo/password provided
     */
    public void onLogin() {
        try {
            User userLogged = UserFacade.getInstance().login(pseudoField.getText(), passwordField.getText());
            Controller.getInstance().setUserLogged(userLogged);
            Controller.getInstance().goTo(View.MAIN);
        } catch (UserException e) {
            this.messageLabel.setText(e.getErrorText());
            this.messageLabel.setTextFill(Color.RED);
        }
    }

    /**
     * On "sign in" button click, go to the sign up screen
     */
    public void onRegister() {
        Controller.getInstance().goTo(View.SIGN_UP);
    }
}
