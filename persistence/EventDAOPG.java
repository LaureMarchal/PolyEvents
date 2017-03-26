package persistence;

import bl.dao.EventDAO;
import bl.model.Event;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * PostgreSQL DAO for the event model
 */
public class EventDAOPG extends EventDAO {
    @Override
    public Event create(Event event) {
        try {
            String query = "INSERT INTO \"Event\" (id,title,subTitle, place, description, beginningTime, registrationDeadline, duration, \"constraints\", placesNumber, price, delayToPay, status, provider) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?,?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(event.getId()));
            ps.setString(2, event.getTitle());
            ps.setString(3, event.getSubTitle());
            ps.setString(4, event.getPlace());
            ps.setString(5, event.getDescription());
            ps.setString(6, String.valueOf(event.getBeginningTime()));
            ps.setString(7, String.valueOf(event.getRegistrationDeadline()));
            ps.setString(8, event.getDuration());
            ps.setString(9, event.getConstraints());
            ps.setString(10, String.valueOf(event.getPlacesNumber()));
            ps.setString(11, String.valueOf(event.getPrice()));
            ps.setString(12, String.valueOf(event.getDelayToPay()));
            ps.setString(13, event.getStatus());
            ps.setString(14, event.getProvider().getPseudo());
            ps.execute();
            connection.close();
            return event;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Event event) {
        return false;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public List<Event> search(String title, String tag) {
        return null;
    }
}
