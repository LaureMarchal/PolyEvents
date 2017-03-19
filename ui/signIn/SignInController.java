package ui.signIn;

import bl.exception.SignInException;
import bl.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.Controller;
import ui.View;

public class SignInController {

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
            if (!consumerForm.isDisabled()) {
                UserFacade.getInstance().registerConsumer(pseudoField.getText(), passwordField.getText(), emailField.getText(), firstnameField.getText(), lastnameField.getText());
            } else {
                UserFacade.getInstance().registerProvider(pseudoField.getText(), passwordField.getText(), emailField.getText(), nameField.getText(), descriptionField.getText(), phoneField.getText(), websiteField.getText(), officeLocationField.getText());
            }
            messageLabel.setText("You are registered !");
            messageLabel.setTextFill(Color.GREEN);
        } catch (SignInException e) {
            messageLabel.setText(e.getErrorText());
            messageLabel.setTextFill(Color.RED);
        }
    }

}
