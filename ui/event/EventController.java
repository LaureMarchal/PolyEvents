package ui.event;

import bl.facade.EventFacade;
import bl.facade.RegistrationFacade;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.Registration;
import bl.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.Controller;
import ui.OnInit;
import ui.View;
import ui.helper.AlertHelper;

import java.util.Date;


/**
 * Controller for the event interface
 */
public class EventController implements OnInit{

    /**
     * The event to see
     */
    private Event currentEvent;

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

    /**
     * The label for event's duration
     */
    @FXML
    private Label durationLabel;

    /**
     * The label for event's conditions
     */
    @FXML
    private Label conditionsLabel;


    /**
     * The label for event's tags
     */
    @FXML
    private Label tagsLabel;

    /**
     * The label for event's number of Places Left
     */
    @FXML
    private Label numberPlacesLeftLabel;

    /**
     * The label for event's time To Register
     */
    @FXML
    private Label timeToRegisterLabel;


    /**
     * The label for event's time Beginning
     */
    @FXML
    private Label timeBeginningLabel;

    /**
     * The label for event's location
     */
    @FXML
    private Label locationLabel;

    /**
     * The label for event's price
     */
    @FXML
    private Label priceLabel;

    /**
     * The label for event's delay of Payement
     */
    @FXML
    private Label delayPayementLabel;

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

    /**
     * The button to add a review to this event
     */
    @FXML
    private Button addReviewButton;

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
        //assign all the label
        this.titleLabel.setText(this.currentEvent.getTitle());
        this.subtitleLabel.setText(this.currentEvent.getSubTitle());
        this.descriptionLabel.setText(this.currentEvent.getDescription());
        this.durationLabel.setText(String.valueOf(this.currentEvent.getDuration()));
        this.delayPayementLabel.setText(String.valueOf(this.currentEvent.getDelayToPay()));
        this.conditionsLabel.setText(this.currentEvent.getConstraints());
        this.priceLabel.setText(String.valueOf(this.currentEvent.getPrice()));
        int nbRegistration = this.currentEvent.getRegistrations().size();
        this.numberPlacesLeftLabel.setText(String.valueOf(this.currentEvent.getPlacesNumber()- (nbRegistration)));
        this.locationLabel.setText(this.currentEvent.getPlace());
        this.tagsLabel.setText(this.currentEvent.getTags().toString());
        this.timeBeginningLabel.setText(String.valueOf(this.currentEvent.getBeginningTime()));
        this.timeToRegisterLabel.setText( String.valueOf(this.currentEvent.getRegistrationDeadline()));

        if(Controller.getInstance().getUserLogged()==this.currentEvent.getProvider()){
            addReviewButton.setDisable(true);
            manageButton.setDisable(false);
        }
        if(Controller.getInstance().getUserLogged().getRole()== Role.CONSUMER){
            cancelButton.setDisable(true);
            deleteButton.setDisable(true);
            manageButton.setDisable(true);
            updateButton.setDisable(true);
            reportButton.setDisable(false);
            registerButton.setDisable(false);
            manageButton.setDisable(true);
        } else{
            cancelButton.setDisable(false);
            deleteButton.setDisable(false);
            manageButton.setDisable(false);
            updateButton.setDisable(false);
            reportButton.setDisable(true);
            registerButton.setDisable(true);
            manageButton.setDisable(true);
        }
    }


    /**
     * On "Edit" button click, go to the edition interface
     */
    public void onEdit() {
        Controller.getInstance().goTo(View.UPDATE_EVENT,this.currentEvent);
    }

    /**
     * On "Cancel" button click, set the status of the event to cancelled
     */
    public void onCancel() {
        this.currentEvent.setStatus("Cancelled");
        //TODO send notification to all consumers registered
    }
    /**
     * On "Delete" button click, delete the event and go back to main view
     */
    public void onDelete() {
        AlertHelper.getInstance().showConfirmationDeleteAlert("Do you really want to delete this event ?",this.currentEvent);
    }
    /**
     * On "Report" button click, go to the reportation interface
     */
    public void onReport() {

    }
    /**
     * On "new" button click, go to the review interface
     */
    public void onAddReview() {

    }
    /**
     * On "New" button click, go to the add messages interface
     */
    public void onAddMessage() {
        Controller.getInstance().goTo(View.ADD_MESSAGE,this.currentEvent);
    }

    /**
     * On "Manage" button click, go to the list of the event's consumers
     */
    public void onManage() {
        Controller.getInstance().goTo(View.REGISTRATION,this.currentEvent.getId());
    }

    /**
     * On "Register" button click, go to the registration interface
     */
    public void onRegister() {
        RegistrationFacade.getInstance().create(
                new Registration(currentEvent,
                        (Consumer)Controller.getInstance().getUserLogged(),
                        new Date(),
                        "WAITING_PAYMENT",
                        null));

    }

    /**
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.MAIN);
    }

    @Override
    public void onInit(Object data) {
        this.currentEvent = (Event) data;
    }
}
