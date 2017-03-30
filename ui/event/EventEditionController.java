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
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.SEE_EVENT);
    }

    /**
     * On "Submit" button click, update the event
     */
    public void onSubmit() {
        String title;
        if(titleField.getText()!=""){
            title=titleField.getText();
        } else{
            title=this.currentEvent.getTitle();
        }
        String subtitle;
        if(subtitleField.getText()!=""){
            subtitle=subtitleField.getText();
        } else{
            subtitle=this.currentEvent.getSubTitle();
        }
        String location;
        if(locationField.getText()!=""){
            location=locationField.getText();
        } else{
            location=this.currentEvent.getPlace();
        }
        String descript;
        if(descriptionArea.getText()!=""){
            descript=descriptionArea.getText();
        } else{
            descript=this.currentEvent.getDescription();
        }
        String duration;
        if(durationField.getText()!=""){
            duration=durationField.getText();
        } else{
            duration=this.currentEvent.getDuration();
        }
        int places;
        if(placesField.getText()!=""){
            places=Integer.parseInt(placesField.getText());
        } else{
            places=this.currentEvent.getPlacesNumber();
        }
        int delayPayement;
        if(delayPayementField.getText()!=""){
            delayPayement=Integer.parseInt(delayPayementField.getText());
        } else{
            delayPayement=this.currentEvent.getDelayToPay();
        }
        String date = "";
        Date time;
        //verify fields fill or not
        if(datePicker.toString()!= ""){
            date = datePicker.toString();
        } else{
            date = this.currentEvent.getBeginningTime().toString();
        }
        if(beginningTimeField.getText()!=""){
            date = date + beginningTimeField.getText();
        } else {
            date = date + this.currentEvent.getBeginningTime().toString();
        }
        time = new Date(date);
        Date deadline;
        if(registerDeadlinePicker.toString()!="") {
            deadline = new Date(registerDeadlinePicker.toString());
        } else {
            deadline = this.currentEvent.getRegistrationDeadline();
        }
        Float price;
        if(priceField.getText()!=""){
            price = Float.valueOf(priceField.getText());
        } else{
            price = this.currentEvent.getPrice();
        }
        List<Tag> listTags = new ArrayList<>();
        String[] tags;
        if(tagsField.getText()!=""){
            tags = tagsField.getText().split("#");
            for (String t : tags ){
                Tag tag = new Tag(t);
                listTags.add(tag);
            }
        } else{
            listTags=this.currentEvent.getTags();
        }
        String restriction = this.currentEvent.getConstraints();
        Provider provider = (Provider) Controller.getInstance().getUserLogged();
        //create new an event
        Event event = new Event(this.currentEvent.getId(),
                title,
                subtitle,
                location,
                descript,
                time,
                deadline,
                duration,
                restriction,
                places,
                price,
                delayPayement,
                "AVAILABLE",
                provider);
        event.setTags(listTags);
        EventFacade.getInstance().update(event);
    }

    @Override
    public void onInit(Object data) {
        this.currentEvent = (Event) data;
    }
}
