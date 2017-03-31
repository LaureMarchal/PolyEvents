package persistence;

import bl.dao.DAOFactory;
import bl.dao.MessageDAO;
import bl.model.Event;
import bl.model.Message;
import bl.model.User;
import persistence.connector.Connector;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageDAOPG extends MessageDAO {
    @Override
    public Message create(Message message, Message parent) {
        try {
            String query = "INSERT INTO Message (userid, eventid, content, posttime, parent) VALUES (?, ?, ?, ?, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, message.getWriter().getPseudo());
            ps.setInt(2, message.getEvent().getId());
            ps.setString(3, message.getContent());
            ps.setTimestamp(4, new java.sql.Timestamp(message.getPostTime().getTime()));
            ps.setInt(5, parent.getID());
            ps.execute();
            String queryID = "SELECT MAX(id) AS id FROM message ";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(queryID);
            rs.next();
            message.setID(rs.getInt("id"));
            connection.close();
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Message message) {
        try {
            String query = "DELETE FROM Message WHERE eventID = ? AND userID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, message.getEvent().getId());
            ps.setString(2, message.getWriter().getPseudo());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public  List<Message> findAllMessagesForEvent(Event event){
        try {
            List<Message> messages = new ArrayList<Message>();
            String query = "SELECT * FROM Message WHERE eventID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, event.getId());
            ResultSet rs = ps.executeQuery(query);
            while(rs.next()){
                String content = rs.getString("content");
                java.util.Date postTime = rs.getTimestamp("postTime");
                User writer = DAOFactory.getInstance().createUserDAO().read(rs.getString("userID"));
                int parentID = rs.getInt("parent");
                int messageID = rs.getInt("id");
                Message message = new Message(messageID, content, postTime, writer, event, parentID);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
