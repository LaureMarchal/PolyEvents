package persistence;

import bl.dao.RegistrationDAO;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.EventReview;
import bl.model.Registration;
import persistence.connector.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class RegistrationDAOPG extends RegistrationDAO {

    @Override
    public Registration create(Registration registration) {
            try {
                String query = "INSERT INTO Registration (consumerID, eventID, creation_time, status) VALUES (?, ?, ?, ?)";
                Connection connection = Connector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, registration.getConsumer().getPseudo());
                ps.setInt(2, registration.getEvent().getId());
                String postTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                ps.setString(3, postTime);
                ps.setString(4, registration.getStatus());
                ps.execute();
                connection.close();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                Date date = formatter.parse(postTime);
                registration.setCreationTime(date);
                return registration;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (ParseException e) {
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
            ps.setInt(2, registration.getEvent().getId());
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
        try {
            String query = "UPDATE Registration SET creation_time = ? , status = ? WHERE consumerID = ? AND eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
            ps.setString(2, registration.getStatus());
            ps.setString(3, registration.getConsumer().getPseudo());
            ps.setInt(4, registration.getEvent().getId());
            ps.execute();
            connection.close();
            return registration;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Registration> findAll(Event event) {

        try {
            List<Registration> registrations = new ArrayList<Registration>();
            String query = "SELECT customerID FROM Registration WHERE eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, event.getId());
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                Consumer consumer =  (Consumer)FactoryDAOPG.getInstance().createUserDAO().read(rs.getString("userID"));
                String status = rs.getString("status");
                java.util.Date creationDate = rs.getTimestamp("creation_time");
                EventReview eventReview = FactoryDAOPG.getInstance().createEventReviewDAO().getReviewByEventID(rs.getInt("eventID"),
                        rs.getString("userID"));
                Registration registration =  new Registration(event,
                        consumer,
                        creationDate,
                        status,
                        eventReview);
                registrations.add(registration);
            }
            return registrations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Registration getOne(String userID, int eventID) {

        try {
            String query = "SELECT * FROM Registration WHERE eventID = ? AND userID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, eventID);
            ps.setString(2, userID);
            ResultSet rs = ps.executeQuery(query);
            // Sets the newly found registration.
            Consumer consumer =  (Consumer)FactoryDAOPG.getInstance().createUserDAO().read(rs.getString("userID"));
            Event event = FactoryDAOPG.getInstance().createEventDAO().getOne(rs.getInt("eventID"));
            String status = rs.getString("status");
            java.util.Date creationDate = rs.getTimestamp("creation_time");
            EventReview eventReview = FactoryDAOPG.getInstance().createEventReviewDAO().getReviewByEventID(rs.getInt("eventID"),
                    rs.getString("userID"));
            Registration registration =  new Registration(event,
                    consumer,
                    creationDate,
                    status,
                    eventReview);
            return registration;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
