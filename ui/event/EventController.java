package ui.event;

import bl.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Controller;
import ui.View;


/**
 * Controller for the event interface
 */
public class EventController {

    /**
     * The label for event's title
     */
    @FXML
    private Label titleLabel;

    /**
     * The label for event's subtitle
     */
    @FXML
    private Label subtitleLabel;

    /**
     * The label for event's decsription
     */
    @FXML
    private Label descriptionLabel;

    //For consumers

    /**
     * The button to register to this event
     */
    @FXML
    private Button registerButton;

    /**
     * The button to report this event
     */
    @FXML
    private Button reportButton;

    //For provider

    /**
     * The button to update the event
     */
    @FXML
    private Button updateButton;

    /**
     * The button to manage the event
     */
    @FXML
    private Button manageButton;

    /**
     * The button to delete the event
     */
    @FXML
    private Button deleteButton;

    /**
     * The button to cancel the event
     */
    @FXML
    private Button cancelButton;

    /**
     * The method initialize allows to hide some buttons in function of the user logged
     */
    @FXML
    public void initialize(){

        if(Controller.getInstance().getUserLogged().getRole()== Role.CONSUMER){
            cancelButton.setDisable(true);
            deleteButton.setDisable(true);
            manageButton.setDisable(true);
            updateButton.setDisable(true);
            reportButton.setDisable(false);
            registerButton.setDisable(false);
        } else{
            cancelButton.setDisable(false);
            deleteButton.setDisable(false);
            manageButton.setDisable(false);
            updateButton.setDisable(false);
            reportButton.setDisable(true);
            registerButton.setDisable(true);
        }
    }


    public void onEdit() {

    }

    public void onDelete() {

    }

    public void onReport() {

    }

    public void onAddReview() {

    }

    public void onAddMessage() {

    }

    /**
     * On "Register" button click, go to the registration interface
     */
    public void onRegister() {

    }

    /**
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.MAIN);
    }

}
