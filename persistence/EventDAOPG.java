package persistence;

import bl.dao.DAOFactory;
import bl.dao.EventDAO;
import bl.model.Event;
import bl.model.Provider;
import bl.model.Registration;
import bl.model.Tag;
import bl.model.Message;
import persistence.connector.Connector;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            ps.setInt(1, event.getId());
            ps.setString(2, event.getTitle());
            ps.setString(3, event.getSubTitle());
            ps.setString(4, event.getPlace());
            ps.setString(5, event.getDescription());
            ps.setString(6, String.valueOf(event.getBeginningTime()));
            ps.setString(7, String.valueOf(event.getRegistrationDeadline()));
            ps.setString(8, event.getDuration());
            ps.setString(9, event.getConstraints());
            ps.setInt(10, event.getPlacesNumber());
            ps.setFloat(11, event.getPrice());
            ps.setInt(12, event.getDelayToPay());
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
        try {
            String query = "DELETE FROM Event WHERE eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, event.getId());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public List<Event> search(String title, String tag) {
        List<Event> eventSearchResult = new ArrayList<Event>();
        try{
            String query = "SELECT Event.id FROM Event, Event_tags WHERE Event.id = Event_tags.eventID AND Event.title LIKE ? AND Event_tags.name = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, ("?"+title+"?"));
            ps.setString(1, tag);
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                // Not sure, could be this.getOne(rs.getInt("Event.id"));
                Event event = this.getOne(rs.getInt("id"));
                eventSearchResult.add(event);
            }
            return eventSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  Event getOne(int id){
        try {
            String query = "SELECT * FROM Event WHERE eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery(query);
            int eventID = id;
            String title = rs.getString("title");
            String subTitle = rs.getString("subtitle");
            String place = rs.getString("location");
            String description = rs.getString("descriotion");
            java.util.Date beginningTime = rs.getTimestamp("begining_time");
            java.util.Date registrationDeadline =  rs.getTimestamp("registration_deadline");
            String duration = String.valueOf(rs.getFloat("duration"));
            String constraints = rs.getString("event_constraints");
            String status = rs.getString("status");
            int placesNumber = rs.getInt("max_number_of_places");
            float price =  rs.getFloat("price");
            int delayToPay = rs.getInt("delay_to_pay");
            Provider provider = (Provider)DAOFactory.getInstance().createUserDAO().read(rs.getString("providerID"));
            List<Registration> registrations = null;
            List<Tag> tags = null;
            List<Message> messages = null;
            Event event = new Event(eventID,
                    title,
                    subTitle,
                    place,
                    description,
                    beginningTime,
                    registrationDeadline,
                    duration,
                    constraints,
                    placesNumber,
                    price,
                    delayToPay,
                    status,
                    provider,
                    registrations,
                    tags,
                    messages);
            String queryID = "SELECT id FROM Message WHERE id=LAST_INSERT_ID()";
            PreparedStatement psID = connection.prepareStatement(queryID);
            ResultSet rsID = ps.executeQuery(queryID);
            connection.close();
            event.setId(rs.getInt("id"));
            return event;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
