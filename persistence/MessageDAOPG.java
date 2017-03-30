package persistence;

import bl.dao.DAOFactory;
import bl.dao.MessageDAO;
import bl.model.Event;
import bl.model.Message;
import bl.model.User;
import persistence.connector.Connector;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageDAOPG extends MessageDAO {
    @Override
    public Message create(Message message, Message parent) {
        try {
            String query = "INSERT INTO Message (userID, eventID, content, postTime, parent) VALUES (?, ?, ?, ?, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, message.getWriter().getPseudo());
            ps.setInt(2, message.getEvent().getId());
            ps.setString(3, message.getContent());
            String postTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            ps.setString(4, postTime);
            ps.setInt(5, parent.getID());
            ps.execute();
            //TODO
            message.setPostTime(null);
            String queryID = "SELECT id FROM Message WHERE id=LAST_INSERT_ID()";
            PreparedStatement psID = connection.prepareStatement(queryID);
            ResultSet rs = psID.executeQuery(queryID);
            connection.close();
            message.setID(rs.getInt("id"));
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
