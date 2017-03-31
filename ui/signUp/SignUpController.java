package ui.signUp;

import bl.exception.UserException;
import bl.facade.UserFacade;
import bl.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.Controller;
import ui.View;
import ui.helper.AlertHelper;

/**
 * Written by Théo Gauchoux
 * Sign up controller handle the registration to the app
 */
public class SignUpController {

    // Common

    @FXML
    private Label messageLabel;

    @FXML
    private TextField pseudoField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;


    // Consumer specific

    @FXML
    private VBox consumerForm;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;


    // Provider specific

    @FXML
    private VBox providerForm;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField websiteField;

    @FXML
    private TextField officeLocationField;


    public void onConsumerSelected() {
        consumerForm.setDisable(false);
        providerForm.setDisable(true);
    }

    public void onProviderSelected() {
        consumerForm.setDisable(true);
        providerForm.setDisable(false);
    }

    public void onCancel() {
        Controller.getInstance().goTo(View.LOGIN);
    }

    public void onSubmit() {
        try {
            User user;
            if (!consumerForm.isDisabled()) {
                user = UserFacade.getInstance().registerConsumer(pseudoField.getText(), passwordField.getText(), emailField.getText(), firstnameField.getText(), lastnameField.getText());
            } else {
                user = UserFacade.getInstance().registerProvider(pseudoField.getText(), passwordField.getText(), emailField.getText(), nameField.getText(), descriptionField.getText(), phoneField.getText(), websiteField.getText(), officeLocationField.getText());
            }
            messageLabel.setText("");
            messageLabel.setTextFill(Color.BLACK);
            AlertHelper.getInstance().showInfoAlert("You are now registered as " + user.getPseudo() + " ! Please, try to login.");
            Controller.getInstance().goTo(View.LOGIN);
        } catch (UserException e) {
            messageLabel.setText(e.getErrorText());
            messageLabel.setTextFill(Color.RED);
        }
    }

}
