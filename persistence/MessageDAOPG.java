package persistence;

import bl.dao.MessageDAO;
import bl.model.Message;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            ps.setString(4, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
            ps.setInt(5, parent.getID());
            ps.execute();
            connection.close();
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
