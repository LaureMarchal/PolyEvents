package ui.registration;

import bl.dao.DAOFactory;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.Registration;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.OnLoad;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the registration interface
 */
public class RegistrationController implements OnLoad {

    /**
     * List of the participants of an event to display
     */
    private ObservableList<Consumer> participants;

    /**
     * Reference to the table view
     */
    @FXML
    private TableView<Consumer> table;

    /**
     * Reference to the column containing the participants to the event
     */
    @FXML
    private TableColumn<Consumer, String> participantColumn;

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        participantColumn.setCellValueFactory(cellData -> {
            Consumer consumer = cellData.getValue();
            return new SimpleStringProperty(consumer.getFirstName() + " " + consumer.getLastName());
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @Override
    public void onLoad(Object data) {
        DAOFactory factory = DAOFactory.getInstance();
        Integer eventId = (Integer) data;
        Event event = factory.createEventDAO().getOne(eventId);
        List<Consumer> participants = factory.createRegistrationDAO().findAllForEvent(event)
                .stream()
                .map(Registration::getConsumer)
                .collect(Collectors.toList());
        this.participants = FXCollections.observableArrayList(participants);
        table.setItems(this.participants);
    }
}
