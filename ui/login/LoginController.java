package ui.login;

import api.LoginFacade;
import exception.LoginException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
            LoginFacade.getInstance().login(pseudoField.getText(), passwordField.getText());
            this.messageLabel.setText("Good password !");
            this.messageLabel.setTextFill(Color.GREEN);
        } catch (LoginException e1) {
            this.messageLabel.setText(e1.getErrorText());
            this.messageLabel.setTextFill(Color.RED);
        }
    }
}
