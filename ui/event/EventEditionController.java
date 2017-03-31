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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        float duration=this.currentEvent.getDuration();
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
        //verify fields fill or not
        Date time = this.currentEvent.getBeginningTime();
        Date deadline = this.currentEvent.getRegistrationDeadline();
        Float price;
        if(priceField.getText()!=""){
            price = Float.valueOf(priceField.getText());
        } else{
            price = this.currentEvent.getPrice();
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
        EventFacade.getInstance().update(event);
    }

    @Override
    public void onInit(Object data) {
        this.currentEvent = (Event) data;
    }
}
