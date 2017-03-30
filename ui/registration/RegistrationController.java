package ui.registration;

import bl.dao.DAOFactory;
import bl.model.Event;
import bl.model.Registration;
import bl.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import ui.OnInit;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the registration interface
 */
public class RegistrationController implements OnInit {

    private ObservableList<User> participants;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> participantColumn;

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        participantColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPseudo()));
    }

    @Override
    public void onInit(Object data) {
        DAOFactory factory = DAOFactory.getInstance();
        Integer eventId = (Integer) data;
        Event event = factory.createEventDAO().getOne(eventId);
        List<User> participants = factory.createRegistrationDAO().findAllForEvent(event)
                .stream()
                .map(Registration::getConsumer)
                .collect(Collectors.toList());
        this.participants = FXCollections.observableArrayList(participants);
        table.setItems(this.participants);
    }
}
