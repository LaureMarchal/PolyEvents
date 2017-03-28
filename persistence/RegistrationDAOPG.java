package persistence;

import bl.dao.RegistrationDAO;
import bl.model.Event;
import bl.model.Registration;
import persistence.connector.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RegistrationDAOPG extends RegistrationDAO {

    @Override
    public Registration create(Registration registration) {
            try {
                String query = "INSERT INTO Registration (consumerID, eventID, creation_time, status) VALUES (?, ?, ?, ?)";
                Connection connection = Connector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, registration.getConsumer().getPseudo());
                ps.setString(2, String.valueOf(registration.getEvent().getId()));
                ps.setString(3, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
                ps.setString(4, registration.getStatus());
                ps.execute();
                connection.close();
                return registration;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public boolean delete(Registration registration) {
        try {
            String query = "DELETE FROM Registration WHERE consumerID = ? AND eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, registration.getConsumer().getPseudo());
            ps.setString(2, String.valueOf(registration.getEvent().getId()));
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Registration update(Registration registration) {
        return null;
    }

    @Override
    public List<Registration> findAll(Event event) {
        return null;
    }
}
