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
import ui.helper.AlertHelper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
     * The field to write the beginning time hour
     */
    @FXML
    private TextField beginningTimeHourField;

    /**
     * The field to write the beginning time min
     */
    @FXML
    private TextField beginningTimeMinField;
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
        LocalDate localDate = datePicker.getValue();
        LocalDateTime localDateTime = localDate.atTime(Integer.parseInt(beginningTimeHourField.getText()), Integer.parseInt(beginningTimeMinField.getText()));
        Date time = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        //assign deadline registration
        LocalDate localDateDeadline = registerDeadlinePicker.getValue();
        LocalDateTime localDateTimeDeadline = localDateDeadline.atTime(0, 0);
        Date deadline = Date.from(localDateTimeDeadline.atZone(ZoneId.systemDefault()).toInstant());
        //assign the price
        Float price = Float.valueOf(priceField.getText());
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
                Integer.valueOf(placesField.getText()),
                price,
                Integer.valueOf(delayPayementField.getText()),
                "AVAILABLE",
                provider);
        EventFacade.getInstance().create(event);
        AlertHelper.getInstance().showInfoAlert("Your event has been saved");
        Controller.getInstance().goTo(View.MAIN);
    }

    /**
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.MAIN);
    }
}
