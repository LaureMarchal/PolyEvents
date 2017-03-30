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
    public Notification updateRead(Notification notification, Boolean read) {
        try {
            String query = "UPDATE Notification SET isRead = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setBoolean(1, read);
            ps.execute();
            connection.close();
            notification.setRead(read);
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public  Notification getOne(int id) {
//        try {
//            String query = "SELECT * FROM Notification WHERE NotificationID = ?";
//            Connection connection = Connector.getInstance().getConnection();
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, String.valueOf(id));
//            ResultSet rs = ps.executeQuery(query);
//            int NotificationID = id;
//            boolean isRead = isRead;
//            Notifiable target = target;
//            Notification notif = new Notification(NotificationID, isRead, target);
//            connection.close();
//            notif.setId(rs.getInt("id"));
//            return notif;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<Notification> getAllForUser(User user) {
//        List<Notification> notifications = new ArrayList<Notification>();
//        try{
//            String query = "SELECT * FROM Notification WHERE Notification.userid=?";
//            Connection connection = Connector.getInstance().getConnection();
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, String.valueOf(user));
//            ResultSet rs = ps.executeQuery(query);
//            while(rs.next()){
//                int NotificationID = rs.getInt("id");
//                Notification notif = new Notification(NotificationID);
//                notifications.add(notif);
//            }
//            return notifications;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public Notification createEventNotification(Notification notification, User user, String relatedTo, Event event, Message message, User relatesToUser, Consumer relatesToConsumer, Provider relatesToProvider){
            try {
                String queryInsert = "INSERT INTO Notification (isRead, userID, related_to";
                String valuesInsert = " VALUES (?, ?, ?";
                switch (relatedTo){
                    case "EVENT":
                        queryInsert += ", relatedToEventID)";
                        valuesInsert += ", ?)";
                        break;
                    case "MESSAGE":
                        queryInsert += ", relatedToMessageID)";
                        valuesInsert += ", ?)";
                        break;
                    case "REGISTRATION":
                        queryInsert += ", relatedToEventID, relatedToConsumerID, relatedToProviderID)";
                        valuesInsert += ", ?, ?, ?)";
                        break;
                    case "PROVIDER_REVIEW":
                        queryInsert += ", relatedToConsumerID, relatedToProviderID)";
                        valuesInsert += ", ?, ?)";
                        break;
                    case "EVENT_REVIEW":
                        queryInsert += ", relatedToEventID, relatedToConsumerID)";
                        valuesInsert += ", ?, ?)";
                        break;
                    default:
                        queryInsert += ")";
                        valuesInsert += ")";
                        return null;
                }
                String query = queryInsert + valuesInsert;
                Connection connection = Connector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setBoolean(1, false);
                ps.setString(2, user.getPseudo());
                ps.setString(3, relatedTo);
                switch (relatedTo){
                    case "EVENT":
                        ps.setInt(4, event.getId());
                        break;
                    case "MESSAGE":
                        ps.setInt(4, message.getID());
                        break;
                    case "REGISTRATION":
                        ps.setInt(4, event.getId());
                        ps.setString(5, relatesToConsumer.getPseudo());
                        ps.setString(6, relatesToProvider.getPseudo());
                        break;
                    case "PROVIDER_REVIEW":
                        ps.setString(4, relatesToConsumer.getPseudo());
                        ps.setString(5, relatesToProvider.getPseudo());
                        break;
                    case "EVENT_REVIEW":
                        ps.setInt(4, event.getId());
                        ps.setString(5, relatesToConsumer.getPseudo());
                        break;
                    default:
                        return null;
                }
                ps.execute();
                // fetch back the last inserted ID to complete de notification Object.
                String queryID = "SELECT id FROM Message WHERE id=LAST_INSERT_ID()";
                PreparedStatement psID = connection.prepareStatement(queryID);
                ResultSet rs = psID.executeQuery(queryID);
                connection.close();
                notification.setId(rs.getInt("id"));
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public List<Notification> getAllForUser(User user) {
        return null;
    }

    @Override
    public Notification getOne(int id) {
        return null;
    }
}
