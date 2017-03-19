package ui.signin;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ui.Controller;

/**
 * Controller for the login interface
 */
public class SigninController {

    @FXML
    private VBox consumerForm;

    @FXML
    private VBox providerForm;

    public void onConsumerSelected() {
        consumerForm.setDisable(false);
        providerForm.setDisable(true);
    }

    public void onProviderSelected() {
        consumerForm.setDisable(true);
        providerForm.setDisable(false);
    }

    public void onCancel() {
        Controller.getInstance().goTo("login/loginView");
    }

}
