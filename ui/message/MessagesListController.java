package ui.message;

import bl.facade.EventFacade;
import bl.model.Event;
import bl.model.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.Controller;
import ui.OnLoad;
import ui.View;
import ui.helper.PropertyConverter;

/**
 * Controller for the messages interface
 */
public class MessagesListController implements OnLoad{

    public static ObservableList<Message> messagesList;
    private Message selectedMessage;

    private Event currentEvent;

    /**
     * The table where are notifications
     */
    @FXML
    private TableView<Message> messagesTable;

    /**
     * The table column for the user who send the message
     */
    @FXML
    private TableColumn<Message,String> userColumn;

    /**
     * The table column for the user who send the message
     */
    @FXML
    private TableColumn<Message,String> contentColumn;


    /**
     * On "Return" button click, return to the main view
     */
    public void onReturn() {
        Controller.getInstance().goTo(View.SEE_EVENT,this.currentEvent);
    }

    @FXML
    public void initialize(){
        init();
    }


    public void init() {
        this.messagesList = FXCollections.observableList(EventFacade.getInstance().getAllMessage(this.currentEvent));
        initializeNotificationsTableView();
    }

    /**
     * Initialize the events table view
     */
    private void initializeNotificationsTableView(){
        messagesTable.setItems(messagesList);
        userColumn.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getWriter().getPseudo()));
        contentColumn.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getContent()));
        messagesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedMessage = newValue);
    }

    @Override
    public void onLoad(Object data) {
        this.currentEvent = (Event) data;
    }
}
