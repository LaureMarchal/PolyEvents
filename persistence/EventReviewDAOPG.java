package persistence;

import bl.dao.EventReviewDAO;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.EventReview;
import bl.model.Registration;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * PostgreSQL DAO for the provider review model
 */
public class EventReviewDAOPG extends EventReviewDAO {

    @Override
    public EventReview create(Event event, Consumer consumer, EventReview eventReview) {
        return null;
    }

    @Override
    public EventReview getReviewByEventID(int eventID, String userID){

        try {
            String query = "SELECT * FROM Event_review WHERE consumerID = ? AND userID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, String.valueOf(eventID));
            ResultSet rs = ps.executeQuery(query);
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
            return null;
            //TODO
            //return registration;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
