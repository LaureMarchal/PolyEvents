package persistence;

import bl.dao.TagSubscriptionDAO;
import bl.model.TagSubscription;
import bl.model.User;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TagSubscriptionDAOPG extends TagSubscriptionDAO {
    @Override
    public TagSubscription create(TagSubscription tagSubscription) {
        try {
            String query = "INSERT INTO Tag_subscription (consumerID, tagID) VALUES (?, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tagSubscription.getConsumer().getPseudo());
            ps.setString(2, tagSubscription.getTag().getLabel());
            ps.execute();
            connection.close();
            return tagSubscription;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(TagSubscription tagSubscription) {
        try {
            String query = "DELETE FROM Tag_subscription WHERE tagID = ? AND consumerID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tagSubscription.getTag().getLabel());
            ps.setString(1, tagSubscription.getConsumer().getPseudo());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
