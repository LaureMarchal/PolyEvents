package ui.user;

import bl.model.Consumer;
import bl.model.Provider;
import bl.model.Role;
import bl.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ui.Controller;

/**
 * Controller for the user interface
 */
public class UserController {

    private User displayedUser;

    public Label roleLabel;

    public TextField pseudoTextField;
    public TextField emailTextField;
    public PasswordField newPasswordTextField;

    public TitledPane consumerInfosPane;
    public TextField firstnameTextField;
    public TextField lastnameTextField;

    public TitledPane providerInfosPane;
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public TextField phoneTextField;
    public TextField websiteTextField;
    public TextArea officeLocationTextArea;

    public void initialize() {
        User user = Controller.getInstance().getUserLogged();

        this.displayedUser = user;

        // Compute visibility for specific panes
        this.consumerInfosPane.setVisible(user.getRole() == Role.CONSUMER);
        this.providerInfosPane.setVisible(user.getRole() == Role.PROVIDER);

        this.pseudoTextField.setText(user.getPseudo());
        this.emailTextField.setText(user.getEmail());
        switch (user.getRole()) {
            case CONSUMER:
                Consumer consumer = (Consumer) user;
                this.roleLabel.setText("You are a consumer");
                this.firstnameTextField.setText(consumer.getFirstName());
                this.lastnameTextField.setText(consumer.getLastName());
                break;
            case PROVIDER:
                Provider provider = (Provider) user;
                this.roleLabel.setText("You are a provider");
                this.nameTextField.setText(provider.getName());
                this.descriptionTextArea.setText(provider.getDescription());
                this.phoneTextField.setText(provider.getPhone());
                this.websiteTextField.setText(provider.getWebsite());
                this.officeLocationTextArea.setText(provider.getOfficeLocation());
                break;
            case ADMINISTRATOR:
                this.roleLabel.setText("You are an administrator");
                break;
        }
    }

    public void onUpdate(ActionEvent actionEvent) {

    }

    public void onDelete(ActionEvent actionEvent) {

    }

    public void onCancel(ActionEvent actionEvent) {
        initialize();
        Controller.getInstance().showInfoAlert("Your updates have been cancelled.");
    }

}
