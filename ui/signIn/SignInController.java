package ui.signIn;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ui.Controller;
import ui.View;

/**
 * Controller for the login interface
 */
public class SignInController {

    /**
     * The field to write the pseudo
     */
    @FXML
    private TextField pseudoField;

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
        Controller.getInstance().goTo(View.LOGIN);
    }

    public void onSubmit() {

    }

}
