package ui.event;

import bl.facade.EventFacade;
import bl.model.Event;
import bl.model.Role;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Controller;
import ui.View;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import ui.helper.DateHelper;
import ui.helper.PropertyConverter;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
/**
 * Controller for the event's list interface
 */
public class EventListController {

    public static ObservableList<Event> eventsList;
    private Event selectedEvent;
    /**
     * The box to choose if the user wants to search free events or not
     */
    @FXML
    private ComboBox pricingBox;
    /**
     * The box to choose if the user wants to search finished events or not
     */
    @FXML
    private ComboBox finishedEventBox;
    /**
     * The box to choose if the user wants to search cancelled events or not
     */
    @FXML
    private ComboBox CancelledEventBox;
    /**
     * The box to choose if the user wants to search full events or not
     */
    @FXML
    private ComboBox fullEventBox;
    /**
     * The table where are events
     */
    @FXML
    private TableView<Event> eventsTable;
    /**
     * The table column for the event date
     */
    @FXML
    private TableColumn<Event,String> beginnigDateEvent;
    /**
     * The table column for the event title
     */
    @FXML
    private TableColumn<Event,String> titleEvent;
    /**
     * The table column for the event deadline to register
     */
    @FXML
    private TableColumn<Event,String> registrationDateEvent;
    /**
     * The table column for the event number of places
     */
    @FXML
    private TableColumn<Event,Integer> placesEvent;
    /**
     * The table column for the event price
     */
    @FXML
    private TableColumn<Event,Float> priceEvent;
    /**
     * The button to add events can only be seen by providers or administrators
     */
    @FXML
    private Button addEventButton;
    /**
     * The field to write the event's title or tag the user wants to search
     */
    @FXML
    private TextField searchField;

    /**
     * On "Search" button click, search event by title or tag and set the new table
     */
    public void onSearch() {
        //setEvents(EventFacade.getInstance().search(searchField.getText(),searchField.getText()));
    }

    /**
     * On "Add an Event" button click, go to the create event interface
     */
    public void onAdd() {
        Controller.getInstance().goTo(View.CREATE_EVENT);
    }

    /**
     * On "Reset" button click, reset the table to all events
     */
    public void onReset() {
        //setEvents(EventFacade.getInstance().getAllEvent());
    }

    /**
     * On "Apply" button click, apply all the fields and boxs to search
     */
    public void onApply() {
        //TODO
    }

    public void setEvents(List<Event> events) {
        this.eventsList = FXCollections.observableList(events);

    }

    @FXML
    public void initialize(){
        if(Controller.getInstance().getUserLogged().getRole()== Role.CONSUMER){
            addEventButton.setDisable(true);
        } else{
            addEventButton.setDisable(false);
        }
        init();
    }

    public void init() {
        setEvents(EventFacade.getInstance().getAllEvent());
        initializeEventsTableView();
    }

    /**
     * Initialize the events table view
     */
    private void initializeEventsTableView(){
        eventsTable.setItems(eventsList);
        beginnigDateEvent.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(DateHelper.getInstance().formatter(cellData.getValue().getBeginningTime())));
        titleEvent.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getTitle()));
        registrationDateEvent.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(DateHelper.getInstance().formatter(cellData.getValue().getRegistrationDeadline())));
        placesEvent.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getPlacesNumber()).asObject());
        priceEvent.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getPrice()).asObject());
        eventsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedEvent = newValue);
    }
}
