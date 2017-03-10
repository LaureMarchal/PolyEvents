package ui.login;

import api.Facade;
import exception.LoginException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController {

    private Facade facade;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField pseudoField;

    @FXML
    private TextField passwordField;

    public LoginController() {
        this.facade = Facade.getInstance();
    }

    public void onLogin() {
        try {
            this.facade.login(pseudoField.getText(), passwordField.getText());
            this.messageLabel.setText("Good password !");
            this.messageLabel.setTextFill(Color.GREEN);
        } catch (LoginException e1) {
            this.messageLabel.setText(e1.getErrorText());
            this.messageLabel.setTextFill(Color.RED);
        }
    }
}
