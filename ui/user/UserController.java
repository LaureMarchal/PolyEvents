package ui.user;

import bl.model.User;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for the user interface
 */
public class UserController {

    public TextField pseudoTextField;
    public TextField emailTextField;
    public PasswordField newPasswordTextField;
    public TextField firstnameTextField;
    public TextField lastnameTextField;
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public TextField phoneTextField;
    public TextField websiteTextField;
    public TextArea officeLocationTextArea;

    public void onUpdate(User user){
    }

    public void onDelete(User user){
    }

}
