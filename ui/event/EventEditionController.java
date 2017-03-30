package ui.event;

import bl.facade.EventFacade;
import bl.model.Event;
import bl.model.Provider;
import bl.model.Tag;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.Controller;
import ui.OnInit;
import ui.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Controller for the event's edition interface
 */
public class EventEditionController implements OnInit{

    /**
     * The event the provider wants to change
     */
    private Event currentEvent;

    //All fields for the creation

    /**
     * The field to write the title
     */
    @FXML
    private TextField titleField;

    /**
     * The field to write the subtitle
     */
    @FXML
    private TextField subtitleField;

    /**
     * The field to write the location
     */
    @FXML
    private TextField locationField;

    /**
     * The field to write the description
     */
    @FXML
    private TextArea descriptionArea;

    /**
     * The field to write the duration
     */
    @FXML
    private TextField durationField;

    /**
     * The picker to write the date
     */
    @FXML
    private DatePicker datePicker;

    /**
     * The field to write the beginning time
     */
    @FXML
    private TextField beginningTimeField;

    /**
     * The picker to write the deadline to register
     */
    @FXML
    private DatePicker registerDeadlinePicker;

    /**
     * The field to write the number of places
     */
    @FXML
    private TextField placesField;

    /**
     * The field to write the price
     */
    @FXML
    private TextField priceField;

    /**
     * The field to write the delay to pay
     */
    @FXML
    private TextField delayPayementField;

    /**
     * The field to write the list of tags
     */
    @FXML
    private TextField tagsField;

    /**
     * The Box to write the restriction on students
     */
    @FXML
    private CheckBox studentsRestrictionBox;

    /**
     * The Box to write the restriction on teachers
     */
    @FXML
    private CheckBox teachersRestrictionBox;

    /**
     * The Box to write the restriction on BDE Members
     */
    @FXML
    private CheckBox bDEMembersRestrictionBox;

    /**
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.MAIN);
    }

    /**
     * On "Submit" button click, update the event
     */
    public void onSubmit() {

    }

    @Override
    public void onInit(Object data) {
        this.currentEvent = (Event) data;
    }
}
