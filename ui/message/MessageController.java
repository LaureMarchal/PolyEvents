package ui.message;

import bl.facade.EventFacade;
import bl.model.Event;
import bl.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ui.Controller;
import ui.OnInit;
import ui.View;

import java.util.Date;

/**
 * Controller for the event's message interface
 */
public class MessageController implements OnInit{

    private Event currentEvent;

    /**
     * The field to write the content of the message
     */
    @FXML
    private TextArea contentArea;
    /**
     * On "Submit" button click, add message and return to the event view
     */
    public void onSubmit() {
        Date today = new Date();
        Message msg = new Message(-1,contentArea.getText(),today,Controller.getInstance().getUserLogged(),this.currentEvent,-1);
        EventFacade.getInstance().addMessage(msg,null);
        Controller.getInstance().goTo(View.SEE_EVENT);
    }

    /**
     * On "Return" button click, return to the event view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.SEE_EVENT);
    }

    @Override
    public void onInit(Object data) {
        this.currentEvent = (Event) data;
    }
}
