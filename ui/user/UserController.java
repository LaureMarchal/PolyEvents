package ui.user;

import bl.Util;
import bl.exception.UserException;
import bl.facade.UserFacade;
import bl.model.Consumer;
import bl.model.Provider;
import bl.model.Role;
import bl.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ui.Controller;
import ui.helper.AlertHelper;

/**
 * Written by Théo Gauchoux
 * Controller for the user interface
 */
public class UserController {

    private User displayedUser;

    public Label roleLabel;

    public TextField pseudoTextField;
    public TextField emailTextField;
    public PasswordField oldPasswordTextField;
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
        User updatedUser = this.displayedUser.clone();

        // Ask old password if a new password has been set
        if (!this.oldPasswordTextField.getText().isEmpty() || !this.newPasswordTextField.getText().isEmpty()) {
            if (!this.oldPasswordTextField.getText().isEmpty() && !this.newPasswordTextField.getText().isEmpty()) {
                if (Util.getInstance().hashString(oldPasswordTextField.getText()).equals(this.displayedUser.getPassword())) {
                    updatedUser.setPassword(Util.getInstance().hashString(this.newPasswordTextField.getText()));
                } else {
                    AlertHelper.getInstance().showInfoAlert("The old password provided is wrong, you can't update it.");
                    return;
                }
            } else {
                AlertHelper.getInstance().showInfoAlert("To update your password, you need to fill both of old and new password fields.");
                return;
            }
        }

        // Update user with new data
        switch (this.displayedUser.getRole()) {
            case CONSUMER:
                Consumer consumer = (Consumer) updatedUser;

                if (this.firstnameTextField.getText().isEmpty()) {
                    AlertHelper.getInstance().showInfoAlert("The new first name can't be empty");
                    return;
                } else {
                    consumer.setFirstName(this.firstnameTextField.getText());
                }

                if (this.lastnameTextField.getText().isEmpty()) {
                    AlertHelper.getInstance().showInfoAlert("The new last name can't be empty");
                    return;
                } else {
                    consumer.setLastName(this.lastnameTextField.getText());
                }

                break;
            case PROVIDER:
                Provider provider = (Provider) updatedUser;

                if (this.nameTextField.getText().isEmpty()) {
                    AlertHelper.getInstance().showInfoAlert("The new name can't be empty");
                    return;
                } else {
                    provider.setName(this.nameTextField.getText());
                }

                if (this.descriptionTextArea.getText().isEmpty()) {
                    AlertHelper.getInstance().showInfoAlert("The new description can't be empty");
                    return;
                } else {
                    provider.setDescription(this.descriptionTextArea.getText());
                }

                provider.setPhone(this.phoneTextField.getText());
                provider.setWebsite(this.websiteTextField.getText());
                provider.setOfficeLocation(this.officeLocationTextArea.getText());

                break;
            case ADMINISTRATOR:

                break;
        }

        try {
            UserFacade.getInstance().updateUser(updatedUser);
            this.displayedUser = updatedUser;
            Controller.getInstance().setUserLogged(updatedUser);
            AlertHelper.getInstance().showInfoAlert("Your profile has been updated successfully !");
        } catch (UserException e) {
            AlertHelper.getInstance().showInfoAlert(e.getErrorText());
        }

    }

    public void onCancel(ActionEvent actionEvent) {
        initialize();
        AlertHelper.getInstance().showInfoAlert("Your updates have been cancelled.");
    }

}
