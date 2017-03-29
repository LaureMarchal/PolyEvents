package persistence;

import bl.dao.MessageDAO;
import bl.model.Message;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String postTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            ps.setString(4, postTime);
            ps.setInt(5, parent.getID());
            ps.execute();
            //TODO
            message.setPostTime(null);
            String queryID = "SELECT id FROM Message WHERE id=LAST_INSERT_ID()";
            PreparedStatement psID = connection.prepareStatement(queryID);
            ResultSet rs = ps.executeQuery(queryID);
            connection.close();
            message.setID(rs.getInt("id"));
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
