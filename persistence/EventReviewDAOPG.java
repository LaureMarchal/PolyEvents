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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * PostgreSQL DAO for the provider review model
 */
public class EventReviewDAOPG extends EventReviewDAO {

    @Override
    public EventReview create(Event event, Consumer consumer, EventReview eventReview) {
        try {
            String query = "INSERT INTO Event_review (consumerID, eventID, content, rate) VALUES (?, ?, ?, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, consumer.getPseudo());
            ps.setInt(2, event.getId());
            ps.setString(3, eventReview.getContent());
            ps.setInt(4, eventReview.getRate());
            ps.execute();
            connection.close();
            return eventReview;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EventReview getReviewByEventID(int eventID, String userID){

        try {
            String query = "SELECT * FROM Event_review WHERE consumerID = ? AND userID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setInt(2, eventID);
            ResultSet rs = ps.executeQuery(query);
            String content = rs.getString("content");
            int rate = rs.getInt("rate");
            EventReview eventReview = new EventReview(content, rate);
            return eventReview;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
