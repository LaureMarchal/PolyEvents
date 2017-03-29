package ui.login;

import bl.exception.LoginException;
import bl.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import bl.model.User;
import ui.Controller;
import ui.View;

/**
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
            Controller.getInstance().userLogged = userLogged;
            Controller.getInstance().goTo(View.MAIN);
        } catch (LoginException e) {
            this.messageLabel.setText(e.getErrorText());
            this.messageLabel.setTextFill(Color.RED);
        }
    }

    public void onRegister() {
        Controller.getInstance().goTo(View.SIGN_IN);
    }
}
