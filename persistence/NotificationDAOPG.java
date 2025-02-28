package persistence;

import bl.dao.DAOFactory;
import bl.dao.NotificationDAO;
import bl.model.*;
import persistence.connector.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOPG extends NotificationDAO {

    @Override
    public Notification createSuspectEventNotification(Notification notification) {
        try {
            String query = "INSERT INTO Notification (isRead, userId, message, relatedTo, relatedToEventId) VALUES (?, ?, ?, ?::Related_To, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBoolean(1, false);
            ps.setString(2, notification.getTarget().getPseudo());
            ps.setString(3, notification.getContent().getNotificationText());
            ps.setString(4, RelatedTo.EVENT.name());
            ps.setInt(5, notification.getRelatedToId());
            ps.execute();
            connection.close();
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Notification notification) {
        try {
            String query = "DELETE FROM notification WHERE userid = ? AND relatedtoeventid = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, notification.getTarget().getPseudo());
            Event event = (Event) notification.getContent();
            ps.setInt(2, event.getId());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Notification updateRead(Notification notification, Boolean read) {
        try {
            String query = "UPDATE Notification SET isRead = ? WHERE id =?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBoolean(1, read);
            ps.setInt(2,notification.getId());
            ps.execute();
            connection.close();
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Notification getOne(int id) {
        try {
            String query = "SELECT * FROM Notification WHERE id = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                int notifID = id;
                Boolean isRead = rs.getBoolean("isread");
                User user = DAOFactory.getInstance().createUserDAO().read(rs.getString("userid"));
                Event event = DAOFactory.getInstance().createEventDAO().getOne(rs.getInt("relatedtoeventid"));
                Notification notif = new Notification(notifID, isRead, user, RelatedTo.EVENT, event.getId(), event);
                connection.close();
                return notif;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Notification> getAllForUser(User user) {
        List<Notification> notificationSearchResult = new ArrayList<Notification>();
        try{
            String query = "SELECT id FROM Notification WHERE userid = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getPseudo());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Notification notif = this.getOne(rs.getInt("id"));
                notificationSearchResult.add(notif);
            }
            return notificationSearchResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notification createEventNotification(Notification notification){
        try {
            String query = "INSERT INTO Notification (isRead, userId, message, relatedTo, relatedToEventId) VALUES (?, ?, ?, ?::Related_To, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBoolean(1, false);
            ps.setString(2, notification.getTarget().getPseudo());
            ps.setString(3, notification.getContent().getNotificationText());
            ps.setString(4, RelatedTo.EVENT.name());
            ps.setInt(5, notification.getRelatedToId());
            ps.execute();
            connection.close();
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
