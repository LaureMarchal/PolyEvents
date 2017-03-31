package ui.registration;

import bl.dao.DAOFactory;
import bl.dao.RegistrationDAO;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.Registration;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.Controller;
import ui.OnLoad;
import ui.View;

import java.util.List;

/**
 * Controller for the registration interface
 */
public class RegistrationController implements OnLoad {

    /**
     * List of the participants of an event to display
     */
    private ObservableList<Registration> registrations;

    /**
     * The event whose we want to show the participants
     */
    private Event currentevent;

    /**
     * Reference to the table view
     */
    @FXML
    private TableView<Registration> table;

    /**
     * Reference to the column containing the participants to the event
     */
    @FXML
    private TableColumn<Registration, String> participantColumn;

    @FXML
    private TableColumn<Registration, String> statusColumn;

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        participantColumn.setCellValueFactory(cellData -> {
            Registration registration = cellData.getValue();
            Consumer consumer = registration.getConsumer();
            return new SimpleStringProperty(consumer.getFirstName() + " " + consumer.getLastName());
        });

        statusColumn.setCellValueFactory(cellData -> {
            Registration registration = cellData.getValue();
            return new SimpleStringProperty(registration.getStatus());
        });
    }

    @FXML
    private void onBackToList(ActionEvent event) {
        Controller.getInstance().goTo(View.SEE_EVENT, currentevent);
    }

    @FXML
    private void onDelete(ActionEvent event) {
        ObservableList<Registration> selectedRegistration = table.getSelectionModel().getSelectedItems();
        RegistrationDAO dao = DAOFactory.getInstance().createRegistrationDAO();
        selectedRegistration.stream().forEach(dao::delete);
        registrations.removeAll(selectedRegistration);
    }

    @Override
    public void onLoad(Object data) {
        DAOFactory factory = DAOFactory.getInstance();
        Integer eventId = (Integer) data;
        currentevent = factory.createEventDAO().getOne(eventId);
        List<Registration> registrations = factory.createRegistrationDAO().findAllForEvent(currentevent);
        this.registrations = FXCollections.observableArrayList(registrations);
        table.setItems(this.registrations);
    }
}
