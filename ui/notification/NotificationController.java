package ui.notification;

import bl.facade.NotificationFacade;
import bl.model.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.helper.PropertyConverter;

/**
 * Controller for the notification's list interface
 */
public class NotificationController {

    public static ObservableList<Notification> notificationsList;
    private Notification selectedNotif;

    /**
     * The table where are notifications
     */
    @FXML
    private TableView<Notification> notificationsTable;
    /**
     * The table column for the event date
     */
    @FXML
    private TableColumn<Notification,String> targetColumn;
    /**
     * The table column for the event title
     */
    @FXML
    private TableColumn<Notification,String> infoColumn;

    public void onReadNotification() {

    }

    @FXML
    public void initialize(){
        init();
    }

    public void init() {
        this.notificationsList = FXCollections.observableList(NotificationFacade.getInstance().getAllNotification());
        initializeNotificationsTableView();
    }

    /**
     * Initialize the events table view
     */
    private void initializeNotificationsTableView(){
        notificationsTable.setItems(notificationsList);
        targetColumn.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getTarget().toString()));
        infoColumn.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getTarget().getNotificationText()));
        notificationsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedNotif = newValue);
    }

}
