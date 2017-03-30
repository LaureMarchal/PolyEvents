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
import ui.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller for the event's creation interface
 */
public class EventCreationController {

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
     * On "Submit" button click, try to create the event with the tuple of fields provided
     */
    public void onCreate() {
        //assign event date
        Date hour = new Date(beginningTimeField.getText());
        Date time = new Date(datePicker.getValue().toEpochDay());
        //assign deadline registration
        Date deadline = new Date(registerDeadlinePicker.toString());
        //assign the price
        Float price = Float.valueOf(priceField.getText());
        //assign tag
        List<Tag> listTags = new ArrayList<>();
        //TODO split not ok be careful about spaces
        String[] tags = tagsField.getText().split("#");
        for (String t : tags ){
            Tag tag = new Tag(t);
            listTags.add(tag);
        }
        //assign eventcontraints
        String restriction = "";
        if (studentsRestrictionBox.isSelected()){
            restriction = restriction+studentsRestrictionBox.getText();
        }
        if (teachersRestrictionBox.isSelected()){
            restriction = restriction+teachersRestrictionBox.getText();
        }
        if (bDEMembersRestrictionBox.isSelected()){
            restriction = restriction+bDEMembersRestrictionBox.getText();
        }
        //get the user logged
        Provider provider = (Provider) Controller.getInstance().getUserLogged();
        //create an event
        Event event = new Event(-1,
                titleField.getText(),
                subtitleField.getText(),
                locationField.getText(),
                descriptionArea.getText(),
                time,
                deadline,
                Float.valueOf(durationField.getText()),
                restriction,
                Integer.getInteger(placesField.getText()),
                price,
                Integer.getInteger(delayPayementField.getText()),
                "AVAILABLE",
                provider);
        event.setTags(listTags);
        EventFacade.getInstance().create(event);
    }

    /**
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.MAIN);
    }
}
