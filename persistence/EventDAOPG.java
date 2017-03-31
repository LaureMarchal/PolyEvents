package persistence;

import bl.dao.DAOFactory;
import bl.dao.EventDAO;
import bl.model.Event;
import bl.model.Provider;
import bl.model.Tag;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PostgreSQL DAO for the event model
 */
public class EventDAOPG extends EventDAO {
    @Override
    public Event create(Event event) {
        try {
            String query = "INSERT INTO Event (title,subtitle, location, description, begining_time, registration_deadline, \"duration\", event_constraints, max_number_of_places, price, delay_to_pay, status, providerID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?::Event_status,?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getSubTitle());
            ps.setString(3, event.getPlace());
            ps.setString(4, event.getDescription());
            ps.setTimestamp(5, new java.sql.Timestamp(event.getBeginningTime().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(event.getRegistrationDeadline().getTime()));
            ps.setFloat(7, event.getDuration());
            ps.setString(8, event.getConstraints());
            ps.setInt(9, event.getPlacesNumber());
            ps.setFloat(10, event.getPrice());
            ps.setInt(11, event.getDelayToPay());
            ps.setString(12, event.getStatus());
            ps.setString(13, event.getProvider().getPseudo());
            ps.execute();

            String queryID = "SELECT MAX(id) AS id FROM event ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            event.setId(rs.getInt("id"));
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
        try {
            String query = "UPDATE Event SET title = ? ," +
                    "subTitle = ? ," +
                    "place = ? ," +
                    "description = ? ," +
                    "beginningTime = ? ," +
                    "registrationDeadline = ? ," +
                    "duration = ? ," +
                    "\"constraints\" = ? ," +
                    "placesNumber = ? ," +
                    "price = ? ," +
                    "delayToPay = ? ," +
                    "status = ? ," +
                    "provider = ? " +
                    "WHERE  eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getSubTitle());
            ps.setString(3, event.getPlace());
            ps.setString(4, event.getDescription());
            ps.setTimestamp(5, new java.sql.Timestamp(event.getBeginningTime().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(event.getRegistrationDeadline().getTime()));
            ps.setFloat(7, event.getDuration());
            ps.setString(8, event.getConstraints());
            ps.setInt(9, event.getPlacesNumber());
            ps.setFloat(10, event.getPrice());
            ps.setInt(11, event.getDelayToPay());
            ps.setString(12, event.getStatus());
            ps.setString(13, event.getProvider().getPseudo());
            ps.setInt(14, event.getId());
            ps.execute();
            connection.close();
            return event;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Event> search(String title, String tag) {
        List<Event> eventSearchResult = new ArrayList<Event>();
        try{
            String query = "SELECT id FROM event WHERE title LIKE ? ";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            String titleRegex =  "%"+title+"%";
            ps.setString(1, titleRegex);
            //ps.setString(2, tag);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                // Not sure, could be : this.getOne(rs.getInt("Event.id"));
                Event event = this.getOne(rs.getInt("id"));
                eventSearchResult.add(event);
            }
            return eventSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event getOne(int id) {
        try {
            String query = "SELECT * FROM Event WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                int eventID = id;
                String title = rs.getString("title");
                String subTitle = rs.getString("subtitle");
                String place = rs.getString("location");
                String description = rs.getString("description");
                java.util.Date beginningTime = rs.getTimestamp("begining_time");
                java.util.Date registrationDeadline = rs.getTimestamp("registration_deadline");
                Float duration = rs.getFloat("duration");
                String constraints = rs.getString("event_constraints");
                String status = rs.getString("status");
                int placesNumber = rs.getInt("max_number_of_places");
                float price = rs.getFloat("price");
                int delayToPay = rs.getInt("delay_to_pay");
                Provider provider = (Provider) DAOFactory.getInstance().createUserDAO().read(rs.getString("providerID"));
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
                        provider);
                connection.close();
                return event;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Event> getAllEventForTag(Tag tag) {
        List<Event> eventSearchResult = new ArrayList<Event>();
        try{
            String query = "SELECT Event.id FROM Event, Event_tags WHERE Event.id = Event_tags.eventID AND Event_tags.name = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tag.getLabel());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                // Not sure, could be : this.getOne(rs.getInt("Event.id"));
                Event event = this.getOne(rs.getInt("id"));
                eventSearchResult.add(event);
            }
            return eventSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Event> getAllEvent(){
        List<Event> eventSearchResult = new ArrayList<Event>();
        try{
            String query = "SELECT id FROM Event";
            Connection connection = Connector.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                // Not sure, could be : this.getOne(rs.getInt("Event.id"));
                Event event = this.getOne(rs.getInt("id"));
                eventSearchResult.add(event);
            }
            return eventSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
